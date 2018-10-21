package velkus.moneybagsbudgeting

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.transaction_add_layout.view.*
import velkus.moneybagsbudgeting.storage.DatabaseFactory
import velkus.moneybagsbudgeting.storage.dao.TransactionDao
import velkus.moneybagsbudgeting.storage.models.Transaction

class MainActivity : AppCompatActivity() {

    var transactions : MutableList<Transaction> = ArrayList()
    lateinit var transactionDao: TransactionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        transactionDao = DatabaseFactory.getInstance(this).transactionDao

        transactions = transactionDao.allTransactions.toMutableList()

        fab.setOnClickListener { _ ->

            val layoutInflater = LayoutInflater.from(this)
            val dialogView = layoutInflater.inflate(R.layout.transaction_add_layout, parentLayout, false)

            val builder = AlertDialog.Builder(this)
            builder.setNeutralButton("Deposit") { dialog, _ ->
                if (!dialogView.amount.text.isEmpty()) {
                    val transactionToAdd = Transaction(dialogView.amount.text.toString().toDouble(), Transaction.Type.DEPOSIT, null)

                    transactionDao.saveTransaction(transactionToAdd)
                    transactions.add(transactionToAdd)

                    val adapter = transactionList.adapter
                    if (adapter is ArrayAdapter<*>) {
                        adapter.notifyDataSetChanged()
                    }
                }
                dialog.dismiss()
            }
            .setPositiveButton("Spend") {dialog, _ ->
                if (!dialogView.amount.text.isEmpty()) {
                    val transactionToAdd = Transaction(("-" + dialogView.amount.text.toString()).toDouble(), Transaction.Type.WITHDRAW, null)

                    transactionDao.saveTransaction(transactionToAdd)
                    transactions.add(transactionToAdd)

                    val adapter = transactionList.adapter
                    if (adapter is ArrayAdapter<*>) {
                        adapter.notifyDataSetChanged()
                    }
                }
                dialog.dismiss()
            }
            .setView(dialogView)
            .show().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(dialogView.amount, InputMethodManager.SHOW_IMPLICIT)
        }

        transactionList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transactions)

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
