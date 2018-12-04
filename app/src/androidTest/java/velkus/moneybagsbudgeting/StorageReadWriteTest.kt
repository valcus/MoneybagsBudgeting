package velkus.moneybagsbudgeting

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import velkus.moneybagsbudgeting.storage.AppDatabase
import velkus.moneybagsbudgeting.storage.dao.AccountDao
import velkus.moneybagsbudgeting.storage.models.Account

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat

@RunWith(AndroidJUnit4::class)
class StorageReadWriteTest {
    private var accountDao: AccountDao? = null
    private var database: AppDatabase? = null

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        accountDao = database!!.accountDao
    }

    @After
    fun closeDb() {
        database!!.close()
    }

    @Test
    fun writeAccountAndReadAsList() {
        val account = Account("hi", null)
        val account2 = Account("bye", null)

        accountDao!!.saveAccount(account)
        accountDao!!.saveAccount(account2)

        val accountList = accountDao!!.allAccounts
        assertThat(2, equalTo(accountList.value!!.size))
    }
}
