package velkus.moneybagsbudgeting.accounts

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import velkus.moneybagsbudgeting.storage.dao.AccountDao

class AccountsViewModelFactory(private val accountDao: AccountDao) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountsViewModel(accountDao) as T
    }
}