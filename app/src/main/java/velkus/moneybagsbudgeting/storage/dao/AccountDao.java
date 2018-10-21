package velkus.moneybagsbudgeting.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import velkus.moneybagsbudgeting.storage.models.Account;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAccount(Account account);

    @Delete
    void deleteAccount(Account account);

    @Query("SELECT * FROM account")
    List<Account> getAllAccounts();
}