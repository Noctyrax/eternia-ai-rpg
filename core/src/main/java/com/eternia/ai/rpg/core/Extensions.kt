package com.eternia.ai.rpg.core

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawContext
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import com.eternia.ai.rpg.core.Result.Failure
import com.eternia.ai.rpg.core.Result.Success
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.text.Normalizer
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

// ============================================================================
// Context Extensions
// ============================================================================

fun Context.isDarkTheme: Boolean =
    (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun Context.getColorCompat(@androidx.annotation.ColorRes colorRes: Int): Int =
    ContextCompat.getColor(this, colorRes)

fun Context.getDrawableCompat(@androidx.annotation.DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableRes)

fun Context.getStringSafe(@androidx.annotation.StringRes stringRes: Int, vararg args: Any): String =
    getString(stringRes, *args)

fun Context.dpToPx(dp: Float): Int = (dp * resources.displayMetrics.density).roundToInt()

fun Context.pxToDp(px: Int): Float = px / resources.displayMetrics.density

fun Context.getCacheDirPath(): String = cacheDir.absolutePath

fun Context.getFilesDirPath(): String = filesDir.absolutePath

// ============================================================================
// String Extensions
// ============================================================================

fun String.isNullOrBlank(): Boolean = this.isNullOrEmpty() || this.trim().isEmpty()

fun String.orEmpty(): String = this ?: ""

fun String.toBase64(): String = Base64.encodeToString(this.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP)

fun String.fromBase64(): String = String(Base64.decode(this, Base64.NO_WRAP), StandardCharsets.UTF_8)

fun String.toSha256(): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val hash = digest.digest(this.toByteArray(StandardCharsets.UTF_8))
    return hash.joinToString("") { "%02x".format(it) }
}

fun String.toMd5(): String {
    val digest = MessageDigest.getInstance("MD5")
    val hash = digest.digest(this.toByteArray(StandardCharsets.UTF_8))
    return hash.joinToString("") { "%02x".format(it) }
}

fun String.normalize(): String = Normalizer.normalize(this, Normalizer.Form.NFC)

fun String.truncate(maxLength: Int, suffix: String = "..."): String {
    return if (this.length <= maxLength) this else this.substring(0, maxLength - suffix.length) + suffix
}

fun String.capitalizeFirst(): String = if (this.isEmpty()) this else this[0].uppercase() + this.substring(1)

fun String.toTitleCase(): String = this.split(" ").joinToString(" ") { it.capitalizeFirst() }

fun String.removeHtmlTags(): String = this.replace("<[^>]*>".toRegex(), "")

fun String.extractUrls(): List<String> {
    val urlRegex = Regex("""(https?|ftp)://[^\s/$.?#].[^\s]*""")
    return urlRegex.findAll(this).map { it.value }.distinct()
}

fun String.maskApiKey(visibleChars: Int = 4): String {
    if (this.length <= visibleChars * 2) return "*".repeat(this.length)
    return this.take(visibleChars) + "*".repeat(this.length - visibleChars * 2) + this.takeLast(visibleChars)
}

// ============================================================================
// File Extensions
// ============================================================================

fun File.readBytesSafe(): Result<ByteArray> = Result.runCatching { readBytes() }

fun File.readTextSafe(charset: java.nio.charset.Charset = StandardCharsets.UTF_8): Result<String> =
    Result.runCatching { readText(charset) }

fun File.writeBytesSafe(bytes: ByteArray): Result<Unit> = Result.runCatching { writeBytes(bytes) }

fun File.writeTextSafe(text: String, charset: java.nio.charset.Charset = StandardCharsets.UTF_8): Result<Unit> =
    Result.runCatching { writeText(text, charset) }

fun File.copyToSafe(target: File): Result<Unit> = Result.runCatching {
    target.parentFile?.mkdirs()
    copyTo(target)
}

fun File.deleteRecursivelySafe(): Result<Boolean> = Result.runCatching { deleteRecursively() }

fun File.sizeInMb(): Double = length() / (1024.0 * 1024.0)

fun File.getMimeType(): String = when (extension.lowercase()) {
    "jpg", "jpeg" -> "image/jpeg"
    "png" -> "image/png"
    "webp" -> "image/webp"
    "gif" -> "image/gif"
    "mp3" -> "audio/mpeg"
    "ogg" -> "audio/ogg"
    "wav" -> "audio/wav"
    "m4a", "mp4" -> "audio/mp4"
    "json" -> "application/json"
    "txt", "md" -> "text/plain"
    "zip" -> "application/zip"
    "epub" -> "application/epub+zip"
    else -> "application/octet-stream"
}

fun File.sanitizeFileName(): String = name
    .replace("[<>:\"/\\\\|?*]".toRegex(), "_")
    .replace("\\s+".toRegex(), " ")
    .trim()

// ============================================================================
// Bitmap Extensions
// ============================================================================

fun Bitmap.toByteArray(format: CompressFormat = CompressFormat.PNG, quality: Int = 100): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(format, quality, stream)
    return stream.toByteArray()
}

fun Bitmap.toBase64(format: CompressFormat = CompressFormat.PNG, quality: Int = 100): String =
    toByteArray(format, quality).let { Base64.encodeToString(it, Base64.NO_WRAP) }

fun Bitmap.resize(maxWidth: Int, maxHeight: Int): Bitmap {
    val width = this.width
    val height = this.height
    val scale = min(maxWidth.toDouble() / width, maxHeight.toDouble() / height, 1.0)
    return if (scale < 1.0) {
        Bitmap.createScaledBitmap(this, (width * scale).toInt(), (height * scale).toInt(), true)
    } else this
}

fun Drawable.toBitmap(): Bitmap = when {
    this is android.graphics.drawable.BitmapDrawable -> this.bitmap
    else -> {
        val bitmap = Bitmap.createBitmap(
            intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        bitmap
    }
}

// ============================================================================
// Uri Extensions
// ============================================================================

fun String.toUriSafe(): Result<Uri> = Result.runCatching { toUri() }

fun Uri.getFileName(context: Context): String? {
    return context.contentResolver.query(this, null, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndexOrThrow(android.provider.OpenableColumns.DISPLAY_NAME)
            cursor.getString(nameIndex)
        } else null
    }
}

// ============================================================================
// View Extensions
// ============================================================================

fun View.gone(): View = apply { visibility = View.GONE }
fun View.visible(): View = apply { visibility = View.VISIBLE }
fun View.invisible(): View = apply { visibility = View.INVISIBLE }
fun View.toggleVisibility(): View = apply {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

// ============================================================================
// Coroutine Extensions
// ============================================================================

fun CoroutineScope.launchSupervisor(block: suspend CoroutineScope.() -> Unit): Job =
    launch(SupervisorJob() + Dispatchers.Default) { block() }

// ============================================================================
// Compose Extensions
// ============================================================================

@Composable
fun Modifier.paddingSafe(dp: Float): Modifier = padding(dp.dp)

@Composable
fun Modifier.safeAreaPadding(): Modifier = {
    val context = LocalContext.current
    val insets = androidx.compose.ui.platform.LocalWindowInsets.current
    val systemBars = insets.systemBars
    padding(
        left = systemBars.left.dp,
        top = systemBars.top.dp,
        right = systemBars.right.dp,
        bottom = systemBars.bottom.dp
    )
}

@Composable
fun AnnotatedString.withColor(color: Color): AnnotatedString =
    buildAnnotatedString {
        append(this@withColor)
        withStyle(style = SpanStyle(color = color))
    }

// ============================================================================
// Logging Extensions
// ============================================================================

const val LOG_TAG_PREFIX = "ETERNIA_"

fun String.logD(tag: String = "DEFAULT") = Log.d("$LOG_TAG_PREFIX$tag", this)
fun String.logI(tag: String = "DEFAULT") = Log.i("$LOG_TAG_PREFIX$tag", this)
fun String.logW(tag: String = "DEFAULT") = Log.w("$LOG_TAG_PREFIX$tag", this)
fun String.logE(tag: String = "DEFAULT") = Log.e("$LOG_TAG_PREFIX$tag", this)
fun String.logV(tag: String = "DEFAULT") = Log.v("$LOG_TAG_PREFIX$tag", this)

fun Throwable.logE(tag: String = "DEFAULT") = Log.e("$LOG_TAG_PREFIX$tag", this.message ?: "", this)

// ============================================================================
// Collection Extensions
// ============================================================================

fun <T> List<T>.randomOrNull(): T? = if (isEmpty()) null else this[Random.nextInt(size)]

fun <T> Iterable<T>.toMapByKey<K>(keySelector: (T) -> K): Map<K, T> =
    associateBy(keySelector)

fun <T, K> List<T>.groupByKey(keySelector: (T) -> K): Map<K, List<T>> =
    groupBy(keySelector)

fun <T> List<T>.chunked(size: Int): List<List<T>> =
    windowed(size, size, partialWindows = true)

// ============================================================================
// Number Extensions
// ============================================================================

fun Int.toDurationString(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return when {
        hours > 0 -> "%dh %dm %ds".format(hours, minutes, seconds)
        minutes > 0 -> "%dm %ds".format(minutes, seconds)
        else -> "%ds".format(seconds)
    }
}

fun Long.toFileSizeString(): String {
    return when {
        this >= 1_000_000_000_000L -> "%.2f TB".format(this / 1_000_000_000_000.0)
        this >= 1_000_000_000L -> "%.2f GB".format(this / 1_000_000_000.0)
        this >= 1_000_000L -> "%.2f MB".format(this / 1_000_000.0)
        this >= 1_000L -> "%.2f KB".format(this / 1_000.0)
        else -> "%d B".format(this)
    }
}

// ============================================================================
// Date/Time Extensions
// ============================================================================

fun Date.toIsoString(): String = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
    timeZone = java.util.TimeZone.getTimeZone("UTC")
}.format(this)

fun String.parseIsoDate(): Date? = Result.runCatching {
    java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
        timeZone = java.util.TimeZone.getTimeZone("UTC")
    }.parse(this)
}.getOrNull()

// ============================================================================
// Result Extensions
// ============================================================================

inline fun <T> Result<T>.getOrNull(): T? = when (this) {
    is Success -> value
    is Failure -> null
}

inline fun <T> Result<T>.getOrDefault(defaultValue: T): T = when (this) {
    is Success -> value
    is Failure -> defaultValue
}

inline fun <T> Result<T>.orElse(other: => Result<T>): Result<T> = when (this) {
    is Success -> this
    is Failure -> other
}

inline fun <T, R> Result<T>.mapNotNull(transform: (T) -> R?): Result<R> = when (this) {
    is Success -> transform(value)?.let { Success(it) } ?: Failure(IllegalStateException("Transformation returned null"))
    is Failure -> this
}

inline fun <T> Result<T>.filter(predicate: (T) -> Boolean): Result<T> = when (this) {
    is Success -> if (predicate(value)) this else Failure(IllegalStateException("Predicate returned false"))
    is Failure -> this
}

inline fun <T> Result<T>.tap(onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit): Result<T> = when (this) {
    is Success -> {
        onSuccess(value)
        this
    }
    is Failure -> {
        onFailure(exception)
        this
    }
}

// ============================================================================
// Encryption Extensions
// ============================================================================

const val ENCRYPTION_ALGORITHM = "AES/GCM/NoPadding"
const val KEY_SIZE = 256
const val IV_SIZE = 12
const val TAG_SIZE = 16

fun generateSecretKey(): SecretKey {
    val keyGenerator = javax.crypto.KeyGenerator.getInstance("AES")
    keyGenerator.init(KEY_SIZE)
    return keyGenerator.generateKey()
}

fun SecretKey.toBase64(): String = Base64.encodeToString(encoded, Base64.NO_WRAP)

fun String.fromBase64ToSecretKey(): SecretKey {
    val decoded = Base64.decode(this, Base64.NO_WRAP)
    return SecretKeySpec(decoded, "AES")
}

fun encrypt(data: ByteArray, key: SecretKey): Result<ByteArray> = Result.runCatching {
    val cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM)
    val iv = ByteArray(IV_SIZE)
    java.security.SecureRandom().nextBytes(iv)
    val spec = GCMParameterSpec(TAG_SIZE * 8, iv)
    cipher.init(Cipher.ENCRYPT_MODE, key, spec)
    val encrypted = cipher.doFinal(data)
    ByteArrayOutputStream().apply {
        write(iv)
        write(encrypted)
    }.toByteArray()
}

fun decrypt(encryptedData: ByteArray, key: SecretKey): Result<ByteArray> = Result.runCatching {
    val cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM)
    val iv = encryptedData.copyOfRange(0, IV_SIZE)
    val data = encryptedData.copyOfRange(IV_SIZE, encryptedData.size)
    val spec = GCMParameterSpec(TAG_SIZE * 8, iv)
    cipher.init(Cipher.DECRYPT_MODE, key, spec)
    cipher.doFinal(data)
}

fun String.encrypt(key: SecretKey): Result<String> = encrypt(this.toByteArray(StandardCharsets.UTF_8), key)
    .map { Base64.encodeToString(it, Base64.NO_WRAP) }

fun String.decrypt(key: SecretKey): Result<String> = Result.runCatching {
    val decoded = Base64.decode(this, Base64.NO_WRAP)
    decrypt(decoded, key).getOrThrow().decodeToString(StandardCharsets.UTF_8)
}

private fun ByteArray.decodeToString(charset: java.nio.charset.Charset): String = String(this, charset)

// ============================================================================
// Compose Color Extensions
// ============================================================================

fun Color.toArgb(): Int = (alpha * 255).toInt() shl 24 or (red * 255).toInt() shl 16 or (green * 255).toInt() shl 8 or (blue * 255).toInt()

fun Int.toComposeColor(): Color = Color(
    red = (this shr 16 and 0xFF) / 255f,
    green = (this shr 8 and 0xFF) / 255f,
    blue = (this and 0xFF) / 255f,
    alpha = (this shr 24 and 0xFF) / 255f
)
