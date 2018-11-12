package velkus.moneybagsbudgeting.transactions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import velkus.moneybagsbudgeting.storage.dao.TransactionDao

class TransactionsViewModelFactory(
        private val transactionDao: TransactionDao,
        private val accountId: Long)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionsViewModel(transactionDao, accountId) as T
    }
}