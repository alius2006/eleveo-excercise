package tests

import helperMethods.Dates
import io.restassured.RestAssured
import io.restassured.response.Response
import org.junit.FixMethodOrder

@FixMethodOrder
class SampleTest extends AbstractBaseSpec {

    private static final String DEPARTURE_DATE = Dates.getNextMonday()
    private static final String FROM = "10202000" // Ostrava
    private static final String TO = "10202002" // Brno

    static Response getResponse

    /** GET all direct connection between Ostrava and Brno */
    def setupSpec() {
        def request = RestAssured
                .given()
                .queryParam("departureDate", DEPARTURE_DATE)
                .queryParam("fromLocationId", FROM)
                .queryParam("fromLocationType", "CITY")
                .queryParam("tariffs", "REGULAR")
                .queryParam("toLocationId", TO)
                .queryParam("toLocationType", "CITY")

        def response = request.get()
        getResponse = response
        def bodyString = response.getBody().asString()
        println(bodyString)
    }

    def "temp"() {
        when:
        sleep(1)

        then:
        assert true
    }

    private static def getDirectConnections() {

    }
}
