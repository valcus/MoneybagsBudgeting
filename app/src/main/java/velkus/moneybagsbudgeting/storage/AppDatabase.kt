package velkus.moneybagsbudgeting.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import velkus.moneybagsbudgeting.storage.dao.AccountDao
import velkus.moneybagsbudgeting.storage.dao.TransactionDao
import velkus.moneybagsbudgeting.storage.models.Account
import velkus.moneybagsbudgeting.storage.models.Transaction

@Database(entities = [Transaction::class, Account::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val transactionDao: TransactionDao

    abstract val accountDao: AccountDao
}