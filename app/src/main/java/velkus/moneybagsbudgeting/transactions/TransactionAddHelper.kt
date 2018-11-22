package velkus.moneybagsbudgeting.transactions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.transaction_add_layout.view.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.DatabaseFactory
import velkus.moneybagsbudgeting.storage.models.Transaction

object TransactionAddHelper {

    fun showTransactionAddPopup(context: Context, parent: ViewGroup, accountId: Long) {

        val transactionDao = DatabaseFactory.getInstance(context).transactionDao
        val layoutInflater = LayoutInflater.from(context)
        val dialogView = layoutInflater.inflate(R.layout.transaction_add_layout, parent, false)

        AlertDialog.Builder(context)
                .setNeutralButton("Deposit") { dialog, _ ->
                    if (!dialogView.amount.text.isEmpty()) {
                        transactionDao.saveTransaction(Transaction(dialogView.amount.text.toString().toDouble(), Transaction.Type.DEPOSIT, "", accountId))
                    }
                    dialog.dismiss()
                }
                .setPositiveButton("Spend") { dialog, _ ->
                    if (!dialogView.amount.text.isEmpty()) {
                        transactionDao.saveTransaction(Transaction(("-" + dialogView.amount.text.toString()).toDouble(), Transaction.Type.WITHDRAW, "", accountId))
                    }
                    dialog.dismiss()
                }
                .setView(dialogView)
                .show().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(dialogView.amount, InputMethodManager.SHOW_IMPLICIT)
    }

}