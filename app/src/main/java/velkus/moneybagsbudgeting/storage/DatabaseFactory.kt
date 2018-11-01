package velkus.moneybagsbudgeting.storage

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context

object DatabaseFactory {
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (instance != null) {
            return instance!!
        }

        synchronized(AppDatabase::class.java) {
            return if (instance != null) {
                instance!!
            } else {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "moneybags.db")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                instance!!
            }
        }
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE `Transaction` ADD COLUMN `description` VARCHAR DEFAULT '' NOT NULL;")
        }
    }
}