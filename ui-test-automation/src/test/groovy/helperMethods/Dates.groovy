package helperMethods

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class Dates {

    static LocalDate getNextMonday() {
        def dateNow = LocalDate.now()
        return dateNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
    }
}