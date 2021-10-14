package helperMethods

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class Dates {

    static String getNextMonday() {
        def dateNow = LocalDate.now()
        def nextMonday =  dateNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
        return nextMonday.toString()
    }
}
