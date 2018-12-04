package velkus.moneybagsbudgeting

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.testing.MigrationTestHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import velkus.moneybagsbudgeting.storage.AppDatabase
import velkus.moneybagsbudgeting.storage.Migrations

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    var helper = MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
            AppDatabase::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory())

    @Test
    fun migrate1to2() {
        val db = helper.createDatabase(TEST_DB, 1)

        db.execSQL("INSERT INTO `Transaction`(date, amount, typeOrdinal, accountId) VALUES(15, 0, 5, 2)")
        db.close()

        helper.runMigrationsAndValidate(TEST_DB, 2, true, Migrations.MIGRATION_1_2)
    }

    @Test
    fun migrate2to3() {
        var db = helper.createDatabase(TEST_DB, 2)

        db.execSQL("INSERT INTO Account (name, sourceId) VALUES('account', null)")
        db.execSQL("INSERT INTO Account (name, sourceId) VALUES('account child', 1)")
        db.close()

        db = helper.runMigrationsAndValidate(TEST_DB, 3, true, Migrations.MIGRATION_2_3)

        db.execSQL("INSERT INTO SourceAccount (accountId) VALUES(1)")
    }
}