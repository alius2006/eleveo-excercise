package helperMethods

import org.apache.commons.lang3.StringUtils

import java.text.NumberFormat

class Prices {

    static float getPriceFromText(String text) {
        String textWithoutCurrency = StringUtils.substringBefore(text, " €")
        String textWithoutRange
        if (StringUtils.contains(textWithoutCurrency, "od ")) {
            textWithoutRange = StringUtils.substringAfter(textWithoutCurrency, "od ")
        } else {
            textWithoutRange = textWithoutCurrency
        }

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY)
        return numberFormat.parse(textWithoutRange).toFloat()

    }
}
