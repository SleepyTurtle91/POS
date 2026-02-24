package core.common.util

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun Double.toCurrencyString(currencyCode: String = "MYR", locale: Locale = Locale("ms", "MY")): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    formatter.currency = Currency.getInstance(currencyCode)
    return formatter.format(this)
}
