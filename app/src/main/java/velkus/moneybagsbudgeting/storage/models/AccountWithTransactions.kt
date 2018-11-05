package velkus.moneybagsbudgeting.storage.models

import android.arch.persistence.room.Relation

class AccountWithTransactions : Account() {
    @Relation(parentColumn = "id", entityColumn = "accountId")
    var transactions: List<Transaction> = listOf()
}