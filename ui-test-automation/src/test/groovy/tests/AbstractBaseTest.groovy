package tests

import com.codeborne.selenide.Configuration
import pages.LandingPage
import spock.lang.Specification

import java.time.Duration

import static com.codeborne.selenide.Selenide.closeWebDriver
import static com.codeborne.selenide.Selenide.open

abstract class AbstractBaseTest extends Specification {
    {
        Configuration.reopenBrowserOnFail = true
        Configuration.reportsFolder = "target/spock-reports"
        Configuration.headless = false
        Configuration.browser = "chrome"
    }

    public static final String BASE_URL = "https://shop.regiojet.sk/"

    public static final Duration TIMEOUT_SHORT = Duration.ofSeconds(4)
    public static final Duration TIMEOUT_MID = Duration.ofSeconds(8)
    public static final Duration TIMEOUT_LONG = Duration.ofSeconds(16)

    def setupSpec() {
        open(BASE_URL)
        new LandingPage().waitForPageLoad(TIMEOUT_MID)
    }

    def cleanupSpec() {
        closeWebDriver()
    }
}