package com.jerryyin.kotlindemo.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by cc on 17-7-19.
 */
object DateUtil {
    val date: String
        get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

    val shortDate: String
        get() = SimpleDateFormat("yyyy-MM-dd").format(Date())


    /**
     * 获取当前日期＋时间
     *
     * @return
     */
    val curDateTime: String
        get() {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    val curDateTimeNoSpace: String
        get() {
            val formatter = SimpleDateFormat("yyyyMMdd-HH:mm:ss")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    /**
     * 自动补0
     *
     * @return
     */
    val curDate: String
        get() {
            val formatter = SimpleDateFormat("yyyy-MM")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    val lastDate: String
        get() {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val c = Calendar.getInstance()
            c.time = Date(System.currentTimeMillis())
            c.add(Calendar.DATE, -1)
            val d = c.time
            val day = format.format(d)
            c.clear()
            return day
        }

    /**
     * 获取当天开始时间
     * @return
     */
    val startTime: Date
        get() {
            val todayStart = Calendar.getInstance()
            todayStart.set(Calendar.HOUR, 0)
            todayStart.set(Calendar.MINUTE, 0)
            todayStart.set(Calendar.SECOND, 0)
            todayStart.set(Calendar.MILLISECOND, 0)
            return todayStart.time
        }

    /**
     * 获取当天结束时间
     * @return
     */
    val endTime: Date
        get() {
            val todayEnd = Calendar.getInstance()
            todayEnd.set(Calendar.HOUR, 23)
            todayEnd.set(Calendar.MINUTE, 59)
            todayEnd.set(Calendar.SECOND, 59)
            todayEnd.set(Calendar.MILLISECOND, 999)
            return todayEnd.time
        }
    val nextDate: String
        get() {
            val format = SimpleDateFormat("yyyy-MM")
            val c = Calendar.getInstance()
            c.time = Date(System.currentTimeMillis())
            c.add(Calendar.MONTH, +1)
            val d = c.time
            val day = format.format(d)
            c.clear()
            return day
        }

    /**
     * 月份日期前面不带0
     *
     * @return
     */
    val curDateNoZero: String
        get() {
            val formatter = SimpleDateFormat("y-M-d")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    val curTime: String
        get() {
            val formatter = SimpleDateFormat("HH:mm")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }


    /**
     * 获取今天之前一周的日期（7天）
     *
     * @return
     */
    //过去七天
    val lastWeekDate: List<String>
        get() {
            val lastWeek = ArrayList<String>()
            val format = SimpleDateFormat("MM.dd")
            val c = Calendar.getInstance()
            for (i in -7..-1) {
                c.time = Date(System.currentTimeMillis())
                c.add(Calendar.DATE, i)
                val d = c.time
                val day = format.format(d)
                lastWeek.add(day)
                c.clear()
            }
            return lastWeek
        }

    /**
     * 获取今天之前一个月的日期（30天）
     *
     * @return
     */
    //过去七天
    val lastMonthDate: List<String>
        get() {
            val lastWeek = ArrayList<String>()
            val format = SimpleDateFormat("MM.dd")
            val c = Calendar.getInstance()
            for (i in 30 downTo 1) {
                c.time = Date(System.currentTimeMillis())
                println("i = $i")
                c.add(Calendar.DATE, -i)
                val d = c.time
                val day = format.format(d)
                lastWeek.add(day)
                println("day = $day")
                c.clear()
            }
            return lastWeek
        }

    fun TimeStampToDate(timestampString: String): String {
        val timestamp = java.lang.Long.parseLong(timestampString) * 1000
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(timestamp))
    }

    fun convertStringToLong(dateTime: String, format: String): Long {

        var result: Long = 0
        try {
            val formatter = SimpleDateFormat(format)
            val dateAndTime = formatter.parse(dateTime)
            result = dateAndTime.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun getCurrentDateTime(format: String): Long {
        var result: Long = 0
        try {
            val current = Date()
            val formatter = SimpleDateFormat(format)
            val tmpFormat = formatter.format(current)
            val formatted = formatter.parse(tmpFormat)
            result = formatted.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun getHourFromLong(millisecond: Long): String {
        val hour = millisecond / 1000 / 60 / 60 % 24
        var result = "00"
        if (hour < 10) {
            result = "0$hour"
        } else {
            result = hour.toString() + ""
        }
        return result

    }

    fun getMinuteFromLong(millisecond: Long): String {
        val minute = millisecond / 1000 / 60 % 60
        var result = "00"
        if (minute < 10) {
            result = "0$minute"
        } else {
            result = minute.toString() + ""
        }
        return result

    }

    fun getSecoundFromLong(mullisecond: Long): String {
        val second = mullisecond / 1000 % 60
        var result = "00"

        if (second < 10) {
            result = "0$second"
        } else {
            result = second.toString() + ""
        }
        return result
    }

    fun getDayListOfMonth(month: Int, year: Int): LinkedList<String> {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        c.set(Calendar.MONTH, month - 1)
        c.set(Calendar.YEAR, year)
        val ls = LinkedList<String>()
        val totalDays = c.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..totalDays) {
            c.set(Calendar.DAY_OF_MONTH, i)
            val date = c.time
            ls.add(sdf.format(date))
        }
        return ls
    }

    /**
     * 获取前一天时间
     * @param cl
     * @return
     */
    fun getBeforeDay(cl: Calendar): Date {
        //使用roll方法进行向前回滚
        //cl.roll(Calendar.DATE, -1);
        //使用set方法直接进行设置
        val day = cl.get(Calendar.DATE)
        cl.set(Calendar.DATE, day - 1)
        return cl.time
    }

    /**
     * 获取当前时间的后一天时间
     * @param cl
     * @return
     */
    fun getAfterDay(cl: Calendar): Date {
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        val day = cl.get(Calendar.DATE)
        cl.set(Calendar.DATE, day + 1)
        return cl.time
    }

    /**
     * 判断是不是刚刚 ，间隔10m 以内算是刚刚
     *
     * @param now  [hh, mm]
     * @param time [hh, mm, ss]
     * @return
     */
    fun isJustNow(now: Array<String>, time: Array<String>): Boolean {
        if (now[0] != time[0]) {
            return false
        } else {
            val n = Integer.parseInt(now[1])
            val t = Integer.parseInt(time[1])
            return if (n > t && n - t <= 10 || n < t && t - n <= 10)
                true
            else {
                false
            }
        }
    }

    /**
     * number --> week
     *
     * @param day(1--7)
     * @return
     */
    fun getCurWeek(day: Int): String {
        var week = ""
        if (day < 1 || day > 7)
            return "0"
        when (day) {
            1 -> week = "星期一"
            2 -> week = "星期二"
            3 -> week = "星期三"
            4 -> week = "星期四"
            5 -> week = "星期五"
            6 -> week = "星期六"
            7 -> week = "星期天"
        }
        return week
    }


    /**
     * @param time_now 目标时间  hh:mm
     * @param time     对比时间  hh:mm
     * 判断time_now 是不是比time 晚 ？
     * @return
     */
    fun isTimeLater(time_now: String, time: String): Boolean {
        val times_now = time_now.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val times = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val h1 = Integer.valueOf(times_now[0])
        val m1 = Integer.valueOf(times_now[1])
        val h = Integer.valueOf(times[0])
        val m = Integer.valueOf(times[1])

        if (h1 > h) {
            return true
        } else if (h1 == h) {
            if (m1 > m)
                return true
        }
        return false
    }


    /**
     * 打个时间戳啊
     *
     * @return
     */
    fun currentTimeSeconds(): String {
        return System.currentTimeMillis().toString()
    }

    /**
     * 将时间转换为时间戳
     */
    @Throws(ParseException::class)
    fun dateToStamp(s: String): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = simpleDateFormat.parse(s)
        val ts = date.time / 1000
        res = ts.toString()
        return res
    }

    /**
     * 将时间戳转换为时间
     */
    fun stampToDate(s: String): String {
        val timestamp = java.lang.Long.parseLong(s) * 1000
        return SimpleDateFormat("MM-dd HH:mm:ss").format(Date(timestamp))
    }

    //月份加减
    @Throws(ParseException::class)
    fun subMonth(date: String, month: Int): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dt = sdf.parse(date)
        val rightNow = Calendar.getInstance()
        rightNow.time = dt
        rightNow.add(Calendar.MONTH, +month)
        val dt1 = rightNow.time

        return sdf.format(dt1)
    }

    fun strToDate(s: String?): Date? {
        if (s == null) {
            return null
        }
        val fmt = SimpleDateFormat("yyyy-MM-dd mm:hh:ss")
        val fmt2 = SimpleDateFormat("yyyy-MM-dd")

        var date: Date? = null
        try {
            date = fmt.parse(s)
        } catch (e: ParseException) {
            try {
                date = fmt2.parse(s)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }

        }

        return date
    }

    /**
     * 计算两个时间戳的时差
     *
     * @param dateTime1 "yyyy-MM-dd HH:mm:ss"
     * @param dateTime2 "yyyy-MM-dd HH:mm:ss"
     * @return 10-2-3          时差： 天-时-分
     */
    fun calculateDateDiff(dateTime1: String, dateTime2: String): String {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val d1 = df.parse(dateTime1).time
            val d2 = df.parse(dateTime2).time
            val diff = if (d1 > d2) d1 - d2 else d2 - d1   //微妙级别的时差

            val days = diff / (1000 * 60 * 60 * 24)
            val hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
            val minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60)

            return days.toString() + "-" + hours + "-" + minutes
        } catch (e: Exception) {
            return "0-0-0"
        }

    }


    /**
     * 计算两个时间戳的时差
     *
     * @param dateTime1 "yyyy-MM-dd HH:mm:ss"
     * @param dateTime2 "yyyy-MM-dd HH:mm:ss"
     * @return 10-2-3          时差： 天-时-分
     */
    fun calculateDateDiff(dateTime1: Date, dateTime2: Date): String {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val d1 = dateTime1.time
            val d2 = dateTime2.time
            val diff = if (d1 > d2) d1 - d2 else d2 - d1   //微妙级别的时差

            val days = diff / (1000 * 60 * 60 * 24)
            val hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
            val minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60)

            return days.toString() + "-" + hours + "-" + minutes
        } catch (e: Exception) {
            return "0-0-0"
        }

    }

    fun dateToString(date: Date): String {
        //        yyyy-MM-dd HH:mm:ss
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(date)
    }
}
