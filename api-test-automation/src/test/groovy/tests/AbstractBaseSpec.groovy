package tests

import io.restassured.RestAssured
import spock.lang.Specification

abstract class AbstractBaseSpec extends Specification {

    def setupSpec() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        RestAssured.baseURI = "https://brn-ybus-pubapi.sa.cz/restapi/routes/search/simple"
    }
}
