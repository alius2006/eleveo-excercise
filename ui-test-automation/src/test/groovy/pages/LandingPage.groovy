package pages

import components.ReactDatePicker
import dto.ConnectionDto
import helperMethods.PredefinedActions

import static com.codeborne.selenide.Condition.appear
import static com.codeborne.selenide.Selectors.byAttribute
import static com.codeborne.selenide.Selectors.byId
import static com.codeborne.selenide.Selenide.$

class LandingPage extends Pages {

    LandingPage() {
        elementToWaitFor = routeFromInput
    }

    private static def routeFromInput = $(byId("route-from"))
    private static def routeToInput = $(byId("route-to"))
    private static def routeThereInput = $(byId("route-there-input"))
    private static def reactDatePickerDiv = $(byAttribute("class", "react-datepicker"))
    private static def searchButton = $(byId("search-button"))

    static void findAConnection(ConnectionDto connection) {
        PredefinedActions.editInputAutocomplete(routeFromInput, connection.From)
        PredefinedActions.editInputAutocomplete(routeToInput, connection.To)
        selectFutureDateByDay(connection.Day)
        searchButton.click()
        new FoundConnectionsPage().waitForPageLoad()
    }

    private static void selectFutureDateByDay(int day) {
        routeThereInput.click()
        reactDatePickerDiv.should(appear)
        ReactDatePicker.selectDay(day)
    }
}
