package velkus.moneybagsbudgeting

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.testing.MigrationTestHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import velkus.moneybagsbudgeting.storage.AppDatabase
import velkus.moneybagsbudgeting.storage.DatabaseFactory

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    var helper = MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
            AppDatabase::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory())

    @Test
    fun migrate1to2() {
        var db = helper.createDatabase(TEST_DB, 1)

        db.execSQL("INSERT INTO `Transaction`(date, amount, typeOrdinal, accountId) VALUES(15, 0, 5, 2)")

        db.close()
        db = helper.runMigrationsAndValidate(TEST_DB, 2, true, DatabaseFactory.MIGRATION_1_2)
    }
}