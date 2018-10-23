package velkus.moneybagsbudgeting.storage.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import velkus.moneybagsbudgeting.storage.models.Account

@Dao
interface AccountDao {

    @get:Query("SELECT * FROM account")
    val allAccounts: LiveData<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)
}