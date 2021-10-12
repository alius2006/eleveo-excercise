package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.SelenideElement
import tests.AbstractBaseTest

import java.time.Duration

abstract class Pages {

    protected SelenideElement elementToWaitFor

    void waitForPageLoad(Duration timeout = AbstractBaseTest.TIMEOUT_SHORT) {
        elementToWaitFor.should(Condition.exist, timeout)
    }
}
