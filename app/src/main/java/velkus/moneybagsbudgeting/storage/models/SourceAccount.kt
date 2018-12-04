package velkus.moneybagsbudgeting.storage.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Account::class, parentColumns = arrayOf("id"), childColumns = arrayOf("accountId"))])
open class SourceAccount {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var accountId: Long? = null
}