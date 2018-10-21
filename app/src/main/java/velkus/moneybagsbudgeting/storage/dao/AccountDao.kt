package velkus.moneybagsbudgeting.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import velkus.moneybagsbudgeting.storage.models.Account

@Dao
interface AccountDao {

    @get:Query("SELECT * FROM account")
    val allAccounts: List<Account>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)
}