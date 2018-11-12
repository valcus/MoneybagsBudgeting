package velkus.moneybagsbudgeting

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import velkus.moneybagsbudgeting.storage.dao.AccountDao

class MainActivityViewModelFactory(private val accountDao: AccountDao, private val accountId: Long) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(accountDao, accountId) as T
    }
}
