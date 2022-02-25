package com.github.akrucan.encepence.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.github.akrucan.encepence.data.entity.Priority
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@TypeConverters
object BoardTypeConverter{

    @TypeConverter
    @JvmStatic
    fun zonedDateTimeFromTimestamp(timestamp: Long): ZonedDateTime {
        val instant = Instant.ofEpochSecond(timestamp)
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    @TypeConverter
    @JvmStatic
    fun zonedDateTimeToTimestamp(zdt: ZonedDateTime): Long{
        return zdt.toEpochSecond()
    }

    @TypeConverter
    @JvmStatic
    fun priorityToString(priority: Priority): String{
        return priority.name
    }

    @TypeConverter
    @JvmStatic
    fun priorityFromString(string: String): Priority{
        return Priority.valueOf(string)
    }
}
