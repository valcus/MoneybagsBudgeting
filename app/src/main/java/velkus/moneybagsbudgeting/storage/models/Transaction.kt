package velkus.moneybagsbudgeting.storage.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import velkus.moneybagsbudgeting.util.MoneyFormatter
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Account::class, parentColumns = arrayOf("id"), childColumns = arrayOf("accountId"))])
class Transaction() {

    constructor(amount: Double?, type: Type, description: String, accountId: Long?) : this() {
        this.amount = amount
        this.type = type
        this.accountId = accountId
        this.description = description
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var amount: Double? = null

    var accountId: Long? = null

    var date: Date? = null

    var description: String = ""

    var typeOrdinal: Int = 0

    //does not need @Ignore because it has no backing field
    var type: Type
        get() = Type.values()[typeOrdinal]
        set(type) {
            typeOrdinal = type.ordinal
        }

    init {
        this.type = type
    }

    override fun toString(): String {
        return MoneyFormatter.format(amount!!)
    }

    enum class Type {
        WITHDRAW,
        DEPOSIT,
        TRANSFER
    }
}

