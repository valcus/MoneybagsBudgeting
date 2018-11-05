package velkus.moneybagsbudgeting.storage

import android.arch.persistence.room.Room
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
                        .addMigrations(Migrations.MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                instance!!
            }
        }
    }
}