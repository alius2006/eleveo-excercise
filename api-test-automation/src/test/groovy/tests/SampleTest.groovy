package tests

import groovy.json.JsonSlurper
import helperMethods.Dates
import io.restassured.RestAssured
import org.apache.commons.lang3.StringUtils
import org.junit.FixMethodOrder

import java.time.LocalTime

@FixMethodOrder
class SampleTest extends AbstractBaseSpec {

    def "Connections test"() {
        given:
        def departureDate = Dates.getNextMonday()
        def from = "10202000" // Ostrava
        def to = "10202002" // Brno

        when: "GET all connections between Ostrava and Brno on the next Monday"
        def response = get(departureDate, from, to)

        then:
        response.then().statusCode(200)

        when: "Parse response to an ArrayList"
        def bodyRaw = response.getBody().asString()
        def directRoutes = getDirectRoutes(bodyRaw)

        then: "Print results"
        printFirstConnection(directRoutes)
        printShortestTime(directRoutes)
        printLowestPrice(directRoutes)
    }

    private static def get(String date, String from, String to) {
        def request = RestAssured
                .given()
                .queryParam("departureDate", date)
                .queryParam("fromLocationId", from)
                .queryParam("fromLocationType", "CITY")
                .queryParam("tariffs", "REGULAR")
                .queryParam("toLocationId", to)
                .queryParam("toLocationType", "CITY")

        def response = request.get()

        return response
    }

    /** Returns an ArrayList of direct routes from a response body given as String */
    private static ArrayList getDirectRoutes(String responseBody) {
        def bodyJson = new JsonSlurper().parseText(responseBody)
        def routes = bodyJson["routes"] as ArrayList
        def direct = new ArrayList()
        for (int i = 0; i < routes.size(); i++) {
            if (routes[i]["transfersCount"] == 0) direct.add(routes[i])
        }
        return direct
    }

    private static void printFirstConnection(ArrayList connections) {
        println("First connection:")

        // Print departure time
        def departureTimeString = connections.get(0)["departureTime"].toString()
        println("Departure time: " + Dates.getTimeSubstring(departureTimeString))

        // Print arrival time
        def arrivalTimeString = connections.get(0)["arrivalTime"].toString()
        println("Arrival time: " + Dates.getTimeSubstring(arrivalTimeString))

        // Print price from
        println("Price from: " + connections.get(0)["priceFrom"] + " €")
        println()
    }

    private static void printShortestTime(ArrayList connections) {
        def timesArray = new ArrayList()
        for(int i = 0; i < connections.size(); i++) {
            String timeString = connections.get(i)["travelTime"]
            timeString = StringUtils.substringBefore(timeString, " h")
            LocalTime time = LocalTime.parse(timeString)
            timesArray.add(time)
        }
        println("The shortest time spent with traveling is: " + timesArray.min())
    }

    private static void printLowestPrice(ArrayList connections) {
        def pricesArray = new ArrayList()
        for(int i = 0; i < connections.size(); i++) {
            String price = connections.get(i)["priceFrom"]
            pricesArray.add(price)
        }
        println("The lowest price is: " + pricesArray.min() + " €")
    }
}
