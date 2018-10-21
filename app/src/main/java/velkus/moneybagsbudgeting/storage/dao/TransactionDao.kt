package velkus.moneybagsbudgeting.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import velkus.moneybagsbudgeting.storage.models.Transaction

@Dao
interface TransactionDao {

    @get:Query("SELECT * FROM `transaction`")
    val allTransactions: List<Transaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transaction: Transaction)

    @Delete
    fun deleteTransaction(transaction: Transaction)

    @Query("SELECT * FROM `transaction` WHERE accountId IS :accountId")
    fun getTransactionsFromAccountId(accountId: Int): List<Transaction>
}
