package com.eternia.ai.rpg.core

import kotlin.Result as KotlinResult

/**
 * A sealed class representing the result of an operation that can either succeed or fail.
 * This is a more functional approach to error handling than exceptions.
 */
sealed interface Result<out T> {
    /**
     * Returns true if this result is a success.
     */
    val isSuccess: Boolean
        get() = this is Success

    /**
     * Returns true if this result is a failure.
     */
    val isFailure: Boolean
        get() = this is Failure

    /**
     * Returns the value if successful, otherwise throws the exception.
     */
    fun getOrThrow(): T = when (this) {
        is Success -> value
        is Failure -> throw exception
    }

    /**
     * Returns the value if successful, otherwise returns the default value.
     */
    fun getOrElse(defaultValue: T): T = when (this) {
        is Success -> value
        is Failure -> defaultValue
    }

    /**
     * Returns the exception if failed, otherwise null.
     */
    val exceptionOrNull: Throwable?
        get() = when (this) {
            is Failure -> exception
            is Success -> null
        }

    /**
     * Maps the success value using the provided function.
     */
    fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(value))
        is Failure -> this
    }

    /**
     * Flat maps the success value using the provided function.
     */
    fun <R> flatMap(transform: (T) -> Result<R>): Result<R> = when (this) {
        is Success -> transform(value)
        is Failure -> this
    }

    /**
     * Recovers from failure using the provided function.
     */
    fun recover(recover: (Throwable) -> T): Result<T> = when (this) {
        is Success -> this
        is Failure -> Success(recover(exception))
    }

    /**
     * Recovers from failure using the provided function that returns a Result.
     */
    fun recoverWith(recover: (Throwable) -> Result<T>): Result<T> = when (this) {
        is Success -> this
        is Failure -> recover(exception)
    }

    /**
     * Executes the given action on the success value.
     */
    fun onSuccess(action: (T) -> Unit): Result<T> = when (this) {
        is Success -> {
            action(value)
            this
        }
        is Failure -> this
    }

    /**
     * Executes the given action on the failure exception.
     */
    fun onFailure(action: (Throwable) -> Unit): Result<T> = when (this) {
        is Failure -> {
            action(exception)
            this
        }
        is Success -> this
    }

    /**
     * Converts to a Kotlin stdlib Result.
     */
    fun toKotlinResult(): KotlinResult<T> = when (this) {
        is Success -> KotlinResult.success(value)
        is Failure -> KotlinResult.failure(exception)
    }

    /**
     * Folds the result into a single value using the provided functions.
     */
    fun <R> fold(onSuccess: (T) -> R, onFailure: (Throwable) -> R): R = when (this) {
        is Success -> onSuccess(value)
        is Failure -> onFailure(exception)
    }
}

/**
 * Represents a successful result with a value.
 */
data class Success<out T>(val value: T) : Result<T>

/**
 * Represents a failed result with an exception.
 */
data class Failure(val exception: Throwable) : Result<Nothing>

/**
 * Companion object with factory methods.
 */
object Result {
    /**
     * Creates a success result.
     */
    fun <T> success(value: T): Result<T> = Success(value)

    /**
     * Creates a failure result.
     */
    fun <T> failure(exception: Throwable): Result<T> = Failure(exception)

    /**
     * Creates a failure result from an error message.
     */
    fun <T> failure(message: String): Result<T> = Failure(IllegalStateException(message))

    /**
     * Executes a block and returns a Result.
     */
    inline fun <T> runCatching(block: () -> T): Result<T> {
        return try {
            Success(block())
        } catch (e: Throwable) {
            Failure(e)
        }
    }

    /**
     * Executes a suspending block and returns a Result.
     */
    suspend fun <T> runCatching(block: suspend () -> T): Result<T> {
        return try {
            Success(block())
        } catch (e: Throwable) {
            Failure(e)
        }
    }

    /**
     * Combines multiple results into a single result with a list of values.
     * If any result is a failure, returns the first failure.
     */
    fun <T> combine(vararg results: Result<T>): Result<List<T>> {
        val values = mutableListOf<T>()
        for (result in results) {
            when (result) {
                is Success -> values.add(result.value)
                is Failure -> return result
            }
        }
        return Success(values)
    }

    /**
     * Combines multiple results into a single result with a list of values.
     * If any result is a failure, returns the first failure.
     */
    fun <T> combine(results: Iterable<Result<T>>): Result<List<T>> {
        val values = mutableListOf<T>()
        for (result in results) {
            when (result) {
                is Success -> values.add(result.value)
                is Failure -> return result
            }
        }
        return Success(values)
    }

    /**
     * Creates a Result from a nullable value, using the provided exception if null.
     */
    fun <T> fromNullable(value: T?, exception: => Throwable): Result<T> {
        return if (value != null) Success(value) else Failure(exception)
    }

    /**
     * Creates a Result from a Boolean condition.
     */
    fun fromBoolean(condition: Boolean, exception: => Throwable): Result<Unit> {
        return if (condition) Success(Unit) else Failure(exception)
    }
}

/**
 * Extension functions for Result.
 */
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