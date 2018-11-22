package velkus.moneybagsbudgeting.transactions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.transaction_item.view.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.models.Transaction

class TransactionsRecyclerViewAdapter : RecyclerView.Adapter<TransactionsRecyclerViewAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amount: TextView = view.amount
        val description: TextView = view.description
    }

    var transactions: List<Transaction> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_item, parent, false) as View)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.amount.text = transaction.toString()
    }
}