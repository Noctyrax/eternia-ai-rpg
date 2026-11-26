package com.eternia.ai.rpg

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import com.eternia.ai.rpg.database.EterniaDatabase
import com.eternia.ai.rpg.settings.KeyManager

@HiltAndroidApp
class EterniaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize database
        EterniaDatabase.getInstance(this)
    }

    companion object {
        @Suppress("UNUSED_PARAMETER")
        fun getDatabase(context: Context): EterniaDatabase {
            return EterniaDatabase.getInstance(context)
        }
    }
}