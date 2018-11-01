package velkus.moneybagsbudgeting.accounts

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.account_card.view.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.models.Account
import velkus.moneybagsbudgeting.transactions.TransactionsActivity

class AccountRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<AccountRecyclerViewAdapter.AccountViewHolder>() {

    class AccountViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title
        val balanceView: TextView = view.balance
        val accountCard: CardView = view.accountCard
    }

    var accounts: List<Account> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.account_card, parent, false) as View)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accounts[position]
        holder.titleView.text = account.name
        holder.balanceView.text = account.id.toString()
        holder.accountCard.setOnClickListener { _ ->
            val i = Intent(context, TransactionsActivity::class.java)
            i.putExtra("accountId", account.id)
            context.startActivity(i)
        }
    }
}