package velkus.moneybagsbudgeting.storage.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Account::class, parentColumns = arrayOf("id"), childColumns = arrayOf("sourceId"))])
class Account() {

    var name: String = ""

    var sourceId: Long? = null

    constructor(name: String, sourceId: Long?) : this() {
        this.name = name
        this.sourceId = sourceId
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}