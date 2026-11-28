package com.eternia.ai.rpg.database

import androidx.room.TypeConverter
import java.util.Date

object Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time
}
