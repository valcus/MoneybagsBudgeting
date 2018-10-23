package velkus.moneybagsbudgeting.accounts

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import velkus.moneybagsbudgeting.storage.dao.AccountDao
import velkus.moneybagsbudgeting.storage.models.Account

class AccountsViewModel(accountDao: AccountDao) : ViewModel() {

    val accountsList: LiveData<List<Account>> = accountDao.allAccounts

}
