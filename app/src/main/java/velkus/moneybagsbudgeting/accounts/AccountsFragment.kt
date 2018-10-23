package velkus.moneybagsbudgeting.accounts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.DatabaseFactory

class AccountsFragment : Fragment() {

    companion object {
        fun newInstance() = AccountsFragment()
    }

    private lateinit var viewModel: AccountsViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AccountRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.accounts_fragment, container, false)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = AccountRecyclerViewAdapter()

        val accountsRecyclerView = view.findViewById<RecyclerView>(R.id.accountsView)
        accountsRecyclerView.layoutManager = viewManager
        accountsRecyclerView.adapter = viewAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, AccountsViewModelFactory(DatabaseFactory.getInstance(context!!).accountDao)).get(AccountsViewModel::class.java)

        viewModel.accountsList.observe(this, Observer { accounts ->
            viewAdapter.accounts = accounts!!.toMutableList()
        })
    }

}
