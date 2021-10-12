package helperMethods

import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.Keys

import static com.codeborne.selenide.Selenide.actions

class PredefinedActions {

    static void editInputAutocomplete(SelenideElement input, String text) {
        input.parent().parent().parent().parent().click()
        sendKeys(text)
        pressKey(Keys.ENTER)
    }

    static void pressKey(Keys key, int count = 1) {
        actions().sendKeys(key * count)
                .build()
                .perform()
    }

    static void sendKeys(CharSequence charSequence) {
        actions().sendKeys(charSequence)
                .build()
                .perform()
    }
}
