package velkus.moneybagsbudgeting.storage.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

import java.text.DecimalFormat
import java.util.Currency
import java.util.Date
import java.util.Locale

@Entity(foreignKeys = [ForeignKey(entity = Account::class, parentColumns = arrayOf("id"), childColumns = arrayOf("accountId"))])
class Transaction() {

    constructor(amount: Double?, type: Type, accountId: Long?) : this() {
        this.amount = amount
        this.type = type
        this.accountId = accountId
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var amount: Double? = null

    var accountId: Long? = null

    var date: Date? = null

    var typeOrdinal: Int = 0

    var type: Type
        get() = Type.values()[typeOrdinal]
        set(type) {
            typeOrdinal = type.ordinal
        }

    init {
        this.type = type
    }

    override fun toString(): String {
        val currencySymbol = Currency.getInstance(Locale.getDefault()).symbol
        val df = DecimalFormat("#,##0.00")
        return currencySymbol + " " + df.format(amount)
    }

    enum class Type {
        WITHDRAW,
        DEPOSIT,
        TRANSFER
    }
}

