package velkus.moneybagsbudgeting;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import velkus.moneybagsbudgeting.storage.AppDatabase;
import velkus.moneybagsbudgeting.storage.dao.AccountDao;
import velkus.moneybagsbudgeting.storage.models.Account;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class RoomReadWriteTest {
    private AccountDao accountDao;
    private AppDatabase database;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        accountDao = database.getAccountDao();
    }

    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void writeAccountAndReadAsList() {
        Account account = new Account("hi", null);
        Account account2 = new Account("bye", null);

        accountDao.saveAccount(account);
        accountDao.saveAccount(account2);

        List<Account> accountList = accountDao.getAllAccounts();
        assertThat(2, equalTo(accountList.size()));
    }
}
