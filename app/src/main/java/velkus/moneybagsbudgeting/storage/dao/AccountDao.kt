package velkus.moneybagsbudgeting.storage.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import velkus.moneybagsbudgeting.storage.models.Account
import velkus.moneybagsbudgeting.storage.models.AccountWithAssociations

@Dao
interface AccountDao {

    @get:Query("SELECT * FROM account")
    val allAccounts: LiveData<List<Account>>

    @get:Query("SELECT * FROM account")
    val allAccountsWithAssociations: LiveData<List<AccountWithAssociations>>

    @Query("SELECT * FROM account WHERE id = :accountId")
    fun getAccountWithAssociations(accountId: Long): LiveData<AccountWithAssociations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)
}