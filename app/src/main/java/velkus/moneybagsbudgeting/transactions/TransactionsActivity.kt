package velkus.moneybagsbudgeting.transactions

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.transaction_add_layout.view.*
import kotlinx.android.synthetic.main.transactions_activity.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.DatabaseFactory
import velkus.moneybagsbudgeting.storage.models.Transaction

class TransactionsActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transactions_activity)

        val accountId = intent.getLongExtra("accountId", 0)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TransactionsFragment.newInstance(accountId))
                    .commitNow()
        }

        viewModel = ViewModelProviders
                .of(this, TransactionsViewModelFactory(DatabaseFactory.getInstance(this).transactionDao, accountId))
                .get(TransactionsViewModel::class.java)


        fab.setOnClickListener { _ ->

            val layoutInflater = LayoutInflater.from(this)
            val dialogView = layoutInflater.inflate(R.layout.transaction_add_layout, container, false)

            AlertDialog.Builder(this)
                    .setNeutralButton("Deposit") { dialog, _ ->
                        if (!dialogView.amount.text.isEmpty()) {
                            viewModel.saveTransaction(Transaction(dialogView.amount.text.toString().toDouble(), Transaction.Type.DEPOSIT, "", accountId))
                        }
                        dialog.dismiss()
                    }
                    .setPositiveButton("Spend") { dialog, _ ->
                        if (!dialogView.amount.text.isEmpty()) {
                            viewModel.saveTransaction(Transaction(("-" + dialogView.amount.text.toString()).toDouble(), Transaction.Type.WITHDRAW, "", accountId))
                        }
                        dialog.dismiss()
                    }
                    .setView(dialogView)
                    .show().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(dialogView.amount, InputMethodManager.SHOW_IMPLICIT)
        }
    }

}
