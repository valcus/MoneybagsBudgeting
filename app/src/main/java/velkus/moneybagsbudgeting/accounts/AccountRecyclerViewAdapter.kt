package velkus.moneybagsbudgeting.accounts

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.account_card.view.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.DatabaseFactory
import velkus.moneybagsbudgeting.storage.models.Account
import velkus.moneybagsbudgeting.transactions.TransactionAddHelper
import velkus.moneybagsbudgeting.transactions.TransactionsActivity
import velkus.moneybagsbudgeting.util.MoneyFormatter

class AccountRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<AccountRecyclerViewAdapter.AccountViewHolder>() {

    class AccountViewHolder(view: View, val parent: ViewGroup) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title
        val balanceView: TextView = view.balance
        val accountCard: CardView = view.accountCard
        val optionsButton: ImageButton = view.accountOptions
    }

    var accounts: List<Account> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.account_card, parent, false) as View,
                parent)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accounts[position]
        holder.titleView.text = account.name
        holder.balanceView.text = MoneyFormatter.format(DatabaseFactory.getInstance(context).transactionDao.getAccountBalance(account.id!!))
        holder.accountCard.setOnClickListener { _ ->
            val i = Intent(context, TransactionsActivity::class.java)
            i.putExtra("accountId", account.id!!)
            context.startActivity(i)
        }
        holder.optionsButton.setOnClickListener { view ->
            val popup = PopupMenu(view.context, view)
            val inflater = popup.menuInflater
            inflater.inflate(R.menu.account_card_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.addTransaction -> {
                        TransactionAddHelper.showTransactionAddPopup(context, holder.parent, accounts[position].id!!)
                        true
                    }
                    R.id.accountSettings -> {
                        //todo implement
                        Toast.makeText(context, "account settings not yet implemented.", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.deleteAccount -> {
                        AlertDialog.Builder(context)
                                .setPositiveButton(R.string.delete) { dialog, _ ->
                                    DatabaseFactory.getInstance(context).accountDao.deleteAccount(account)
                                    dialog.dismiss()
                                }
                                .setMessage(R.string.delete_confirmation)
                                .show()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}