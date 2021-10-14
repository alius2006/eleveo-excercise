package helperMethods

import org.apache.commons.lang3.StringUtils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class Dates {

    static String getNextMonday() {
        def dateNow = LocalDate.now()
        def nextMonday =  dateNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
        return nextMonday.toString()
    }

    static String getTimeSubstring(String date) {
        date = StringUtils.substringAfter(date, "T")
        date = StringUtils.substringBefore(date, ".000")
        return date
    }
}
