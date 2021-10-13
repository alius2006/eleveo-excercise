package tests

import io.restassured.RestAssured
import org.junit.FixMethodOrder

@FixMethodOrder
class SampleTest extends AbstractBaseSpec {

    def "GET"() {
        given:
        def request = RestAssured
                .given()
        .queryParam("departureDate", "2021-10-13")
        .queryParam("fromLocationId", "10202000")
        .queryParam("fromLocationType", "CITY")
        .queryParam("tariffs", "REGULAR")
        .queryParam("toLocationId", "10202002")
        .queryParam("toLocationType", "CITY")

        when:
        def response = request.get()

        then:
        println("Response body:")
        println(response.getBody().asString())
        response.then().statusCode(200)
    }
}
