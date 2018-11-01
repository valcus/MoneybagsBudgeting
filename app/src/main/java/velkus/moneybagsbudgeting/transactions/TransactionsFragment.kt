package velkus.moneybagsbudgeting.transactions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.transactions_fragment.view.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.DatabaseFactory

class TransactionsFragment : Fragment() {

    companion object {
        fun newInstance(accountId: Long): TransactionsFragment {
            val fragment = TransactionsFragment()

            val args = Bundle()
            args.putLong("accountId", accountId)
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var viewModel: TransactionsViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: TransactionsRecyclerViewAdapter

    private var accountId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountId = arguments?.getLong("accountId")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.transactions_fragment, container, false)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = TransactionsRecyclerViewAdapter()

        val transactionRecyclerView = view.transactionsView
        transactionRecyclerView.layoutManager = viewManager
        transactionRecyclerView.adapter = viewAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders
                .of(activity!!, TransactionsViewModelFactory(DatabaseFactory.getInstance(context!!).transactionDao, accountId!!))
                .get(TransactionsViewModel::class.java)

        viewModel.transactionList.observe(this, Observer { transactions ->
            viewAdapter.transactions = transactions!!.toMutableList()
        })
    }
}
