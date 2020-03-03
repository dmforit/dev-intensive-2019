package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dataFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dataFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String{
    val time = (date.time - this.time) / 1000

    return if (time >= 0){
        when (time) {
            in 0..1 -> "только что"
            in 1..45 -> "несколько секунд назад"
            in 45..75 -> "минуту назад"
            in 75..45 * 60 -> when {
                (time / 60) % 100 in 11..14 -> "${(time / 60)} минут назад"
                (time / 60) % 10 == 1L -> "${(time / 60)} минуту назад"
                (time / 60) % 10 in 2..4 -> "${(time / 60)} минуты назад"
                else -> "${(time / 60)} минут назад"
            }
            in 45 * 60..75 * 60 -> "час назад"
            in 75 * 60..22 * 3600 -> when {
                (time / 3600) % 100 in 11..14 -> "${(time / 3600)} часов назад"
                (time / 3600) % 10 == 1L -> "${(time / 3600)} час назад"
                (time / 3600) % 10 in 2..4 -> "${(time / 3600)} часа назад"
                else -> "${(time / 3600)} часов назад"
            }
            in 22 * 3600..47 * 3600 -> "день назад"
            in 48 * 3600..360 * 86400 -> when {
                (time / 86400) % 100 in 11..14 -> "${(time / 86400)} дней назад"
                (time / 86400) % 10 == 1L -> "${(time / 86400)} день назад"
                (time / 86400) % 10 in 2..4 -> "${(time / 86400)} дня назад"
                else -> "${(time / 86400)} дней назад"
            }

            else -> "более года назад"
        }
    } else {
        when(abs(time)) {
            in 0..1 -> "только что"
            in 1..45 -> "через несколько секунд"
            in 45..75 -> "через минуту"
            in 75..45 * 60 -> when {
                abs((time / 60) % 100) in 11..14 -> "через ${abs((time / 60) % 10)} минут"
                abs((time / 60) % 10) == 1L -> "через ${abs((time / 60))} минуту"
                abs((time / 60) % 10) in 2..4 -> "через ${abs((time / 60))} минуты"
                else -> "через ${abs((time / 60) % 10)} минут"
            }
            in 45 * 60..75 * 60 -> "час назад"
            in 75 * 60..22 * 3600 -> when {
                abs((time / 3600) % 100) in 11..14 -> "через ${abs((time / 3600))} часов"
                abs((time / 3600) % 10) == 1L -> "через ${abs((time / 3600))} час"
                abs((time / 3600) % 10) in 2..4 -> "через ${abs((time / 3600))} часа"
                else -> "через ${abs((time / 3600))} часов"
            }
            in 22 * 3600..47 * 3600 -> "день назад"
            in 48 * 3600..360 * 86400 -> when {
                abs((time / 86400) % 100) in 11..14 -> "через ${abs((time / 86400))} дней"
                abs((time / 86400) % 10) == 1L -> "${abs((time / 86400))} день назад"
                abs((time / 86400) % 10) in 2..4 -> "через ${abs((time / 86400))} дня"
                else -> "через ${abs((time / 86400))} дней"
            }

            else -> "более чем через год"
        }
    }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val v = abs(value)

        return when(this) {
            SECOND -> {
                when {
                    (v % 100) in 11..14 -> "$v секунд"
                    (v % 10) == 1 -> "$v секунду"
                    (v % 10) in 2..4 -> "$v секунды"
                    else -> "$v секунд"
                }
            }
            MINUTE -> {
                when {
                    (v % 100) in 11..14 -> "$v минут"
                    (v % 10) == 1 -> "$v минуту"
                    (v % 10) in 2..4 -> "$v минуты"
                    else -> "$v минут"
                }
            }
            HOUR -> {
                when {
                    (v % 100) in 11..14 -> "$v часов"
                    (v % 10) == 1 -> "$v час"
                    (v % 10) in 2..4 -> "$v часа"
                    else -> "$v часов"
                }
            }
            DAY -> {
                when {
                    (v % 100) in 11..14 -> "$v дней"
                    (v % 10) == 1 -> "$v день"
                    (v % 10) in 2..4 -> "$v дня"
                    else -> "$v дней"
                }
            }
        }
    }
}