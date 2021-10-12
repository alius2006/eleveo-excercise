package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.SelenideElement
import dto.ConnectionDto
import helperMethods.Elements
import helperMethods.Prices

import static com.codeborne.selenide.CollectionCondition.size
import static com.codeborne.selenide.Condition.disappear
import static com.codeborne.selenide.Condition.exactText
import static com.codeborne.selenide.Selectors.*
import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.$$

class FoundConnectionsPage extends Pages {

    private static final String ZERO_TRANSFERS = "Prestup: 0"

    FoundConnectionsPage() {
        elementToWaitFor = transfersSpan
    }

    private static def transfersSpan = $(byId("transfers-desktop"))
    private static def allConnectionDetailsDivs = $$(byAttribute("class*", "connection-detail"))

    static void printFirstDirectConnectionInfo() {
        def detailsDiv = getAllDirectConnectionDetailsDivs()[0]
        String departureTime = getDepartureTimeFromDetailDiv(detailsDiv)
        String arrivalTime = getArrivalTimeFromDetailDiv(detailsDiv)
        String price = getPriceFromDetailDiv(detailsDiv)

        println("The first direct connection details:")
        println("Departure time: " + departureTime)
        println("Arrival time: " + arrivalTime)
        println("Price: " + price)
    }

    static void verifyAllDirectConnections(ConnectionDto connection) {
        for (SelenideElement detailsDiv : getAllDirectConnectionDetailsDivs()) {
            // open details
            Elements.scrollElementIntoView(detailsDiv)
            detailsDiv.click()

            // Verify From
            detailsDiv.$$(byAttribute("class*", "station text-h2")).shouldHave(size(2))[0]
                    .$(byTagName("strong"))
                    .shouldHave(exactText(connection.From))

            // Verify To
            detailsDiv.$$(byAttribute("class*", "station text-h2"))[1]
                    .$(byTagName("strong"))
                    .shouldHave(exactText(connection.To))

            // Close details
            detailsDiv.$(byId("detail-arrow-desktop")).click()
            detailsDiv.$(byAttribute("class*", "connection-info")).should(disappear)
        }
    }

    static void printLowestPrice() {
        def detailsDivs = getAllDirectConnectionDetailsDivs()
        def prices = new ArrayList()
        for(SelenideElement detailsDiv : detailsDivs) {
            String priceText = getPriceFromDetailDiv(detailsDiv)
            float price = Prices.getPriceFromText(priceText)
            prices.add(price)
        }
        println("Lowest price: " + prices.min())
    }

    private static def getAllDirectConnectionDetailsDivs() {
        return allConnectionDetailsDivs.filterBy(Condition.text(ZERO_TRANSFERS))
    }

    private static String getDepartureTimeFromDetailDiv(SelenideElement detailDiv) {
        def timeSeatsDiv = detailDiv.$(byAttribute("class*", "times-seats-wrapper"))
        def departureSpan = timeSeatsDiv.$(byAttribute("style", "font-weight: bold;"))
        return departureSpan.innerText()
    }

    private static String getArrivalTimeFromDetailDiv(SelenideElement detailDiv) {
        def timeSeatsDiv = detailDiv.$(byAttribute("class*", "times-seats-wrapper"))
        def arrivalSpan = timeSeatsDiv.$(byAttribute("style", "font-weight: normal;"))
        return arrivalSpan.innerText()
    }

    private static String getPriceFromDetailDiv(SelenideElement detailDiv) {
        def priceButton = detailDiv.$(byId("price-yellow-desktop"))
        return priceButton.innerText()
    }
}
