package velkus.moneybagsbudgeting.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import velkus.moneybagsbudgeting.storage.models.Transaction;

@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);

    @Query("SELECT * FROM `transaction`")
    List<Transaction> getAllTransactions();

    @Query("SELECT * FROM `transaction` WHERE accountId IS :accountId")
    List<Transaction> getTransactionsFromAccountId(int accountId);
}
