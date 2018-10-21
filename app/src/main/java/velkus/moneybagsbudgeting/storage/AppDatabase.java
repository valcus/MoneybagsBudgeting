package velkus.moneybagsbudgeting.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import velkus.moneybagsbudgeting.storage.dao.AccountDao;
import velkus.moneybagsbudgeting.storage.dao.TransactionDao;
import velkus.moneybagsbudgeting.storage.models.Account;
import velkus.moneybagsbudgeting.storage.models.Transaction;
import velkus.moneybagsbudgeting.util.SingletonHoler;

@Database(entities = {Transaction.class, Account.class},
        version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao getTransactionDao();

    public abstract AccountDao getAccountDao();
}
