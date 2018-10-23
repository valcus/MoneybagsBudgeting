package velkus.moneybagsbudgeting.accounts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.models.Account

class AccountRecyclerViewAdapter : RecyclerView.Adapter<AccountRecyclerViewAdapter.AccountViewHolder>() {

    class AccountViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val balanceView: TextView = view.findViewById(R.id.balance)
    }

    var accounts: List<Account> = ArrayList()
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
    }
}