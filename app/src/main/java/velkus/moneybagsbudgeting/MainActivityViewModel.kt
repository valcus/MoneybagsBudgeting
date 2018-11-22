package velkus.moneybagsbudgeting

import android.arch.lifecycle.ViewModel
import velkus.moneybagsbudgeting.storage.dao.AccountDao

class MainActivityViewModel(accountDao: AccountDao, accountId: Long) : ViewModel() {

    val accountWithAssociations = accountDao.getAccountWithAssociations(accountId)

}