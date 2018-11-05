package velkus.moneybagsbudgeting.accounts

import android.arch.lifecycle.ViewModel
import velkus.moneybagsbudgeting.storage.dao.AccountDao

class AccountsViewModel(accountDao: AccountDao) : ViewModel() {

    val accountsWithTransactions = accountDao.allAccountsWithTransactions

}
