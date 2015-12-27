package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun MyDate.compareTo(otherDate: MyDate): Int {
    return if (year > otherDate.year) 1
    else if (year < otherDate.year) -1
    else {
        if (month > otherDate.month) 1
        else if (month < otherDate.month) -1
        else {
            if (dayOfMonth > otherDate.dayOfMonth) 1
            else if (dayOfMonth < otherDate.dayOfMonth) -1
            else 0
        }
    }
}

operator fun DateRange.contains(d: MyDate): Boolean {
    return if(d >= start && d <= endInclusive) true
    else false
}

operator fun DateRange.iterator(): Iterator<MyDate> {
    var next = this.start
    val last = this.endInclusive

    return object: Iterator<MyDate> {
        override fun next(): MyDate {
            val value = next
            next = next.nextDay()
            return value
        }

        override fun hasNext(): Boolean {
            return if(next <= last) true
            else false
        }
    }
}