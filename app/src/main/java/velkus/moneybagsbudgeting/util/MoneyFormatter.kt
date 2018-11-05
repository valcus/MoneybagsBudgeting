package velkus.moneybagsbudgeting.util

import java.text.DecimalFormat
import java.util.*

object MoneyFormatter {
    fun format(amount: Double) : String {
        val currencySymbol = Currency.getInstance(Locale.getDefault()).symbol
        val df = DecimalFormat("#,##0.00")
        val prefix: String = if (amount < 0) "-" else ""
        return prefix + currencySymbol + df.format(Math.abs(amount))
    }
}