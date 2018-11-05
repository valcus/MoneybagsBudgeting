package velkus.moneybagsbudgeting.transactions

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.transactions_activity.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.DatabaseFactory

class TransactionsActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transactions_activity)

        val accountId = intent.getLongExtra("accountId", 0)
        val transactionDao = DatabaseFactory.getInstance(this).transactionDao

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TransactionsFragment.newInstance(accountId))
                    .commitNow()
        }

        viewModel = ViewModelProviders
                .of(this, TransactionsViewModelFactory(transactionDao, accountId))
                .get(TransactionsViewModel::class.java)


        fab.setOnClickListener { _ ->
            TransactionAddHelper.showTransactionAddPopup(this, container, accountId)
        }
    }

}
