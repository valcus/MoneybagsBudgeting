package velkus.moneybagsbudgeting.storage.models

import android.arch.persistence.room.Relation

class SourceAccountWithData : SourceAccount() {

    @Relation(parentColumn = "accountId", entityColumn = "id")
    var account: Account = Account()
}