package components

import java.time.LocalDate

import static com.codeborne.selenide.Selenide.*
import static com.codeborne.selenide.Selectors.*

class ReactDatePicker {

    private static def nextMonthButton = $(byAttribute("class", "react-datepicker__navigation react-datepicker__navigation--next"))

    /** Selects a day in the future by a day number */
    static void selectDay(int day) {
        def dayNow = LocalDate.now().dayOfMonth
        if (day <= dayNow) nextMonthButton.click()
        getDayDivByDay(day)
    }

    /** Returns a day picker Selenide Element by a day number */
    static def getDayDivByDay(int day) {
        String dayString = String.format("%03d", day)
        $(byAttribute("class", "react-datepicker__day react-datepicker__day--${dayString}"))
    }
}
