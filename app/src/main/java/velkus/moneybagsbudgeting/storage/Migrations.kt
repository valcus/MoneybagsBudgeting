package velkus.moneybagsbudgeting.storage

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

object Migrations {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE `Transaction` ADD COLUMN `description` VARCHAR DEFAULT '' NOT NULL;")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                    "CREATE TABLE SourceAccount (" +
                            "id INTEGER PRIMARY KEY," +
                            "accountId INTEGER," +
                            "FOREIGN KEY(accountId) REFERENCES Account(id)" +
                            ")"
            )
        }
    }
}