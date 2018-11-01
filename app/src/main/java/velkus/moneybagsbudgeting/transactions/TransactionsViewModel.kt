package velkus.moneybagsbudgeting.transactions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import velkus.moneybagsbudgeting.storage.dao.TransactionDao
import velkus.moneybagsbudgeting.storage.models.Transaction

class TransactionsViewModel(private val transactionDao: TransactionDao, accountId: Long) : ViewModel() {

    val transactionList: LiveData<List<Transaction>> = transactionDao.transactionsByAccount(accountId)

    fun saveTransaction(transaction: Transaction) {
        transactionDao.saveTransaction(transaction)
    }
}
