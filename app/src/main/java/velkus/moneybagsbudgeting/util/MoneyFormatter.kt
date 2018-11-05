package velkus.moneybagsbudgeting.util

import java.text.DecimalFormat
import java.util.*

object MoneyFormatter {
    fun format(amount: Double) : String {
        val currencySymbol = Currency.getInstance(Locale.getDefault()).symbol
        val df = DecimalFormat("#,##0.00")
        return currencySymbol + " " + df.format(amount)
    }
}