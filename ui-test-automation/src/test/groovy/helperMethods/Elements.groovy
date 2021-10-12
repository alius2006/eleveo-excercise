package helperMethods

import com.codeborne.selenide.SelenideElement

class Elements {

    static void scrollElementIntoView(SelenideElement element) {
        element.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
    }
}
