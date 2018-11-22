package velkus.moneybagsbudgeting.storage.models

import android.arch.persistence.room.Relation

class AccountWithAssociations : Account() {
    @Relation(parentColumn = "id", entityColumn = "accountId")
    var transactions: List<Transaction> = listOf()

    @Relation(parentColumn = "id", entityColumn = "sourceId")
    var childAccounts: List<Account> = listOf()
}