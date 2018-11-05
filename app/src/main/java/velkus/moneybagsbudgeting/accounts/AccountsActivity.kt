package velkus.moneybagsbudgeting.accounts

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.synthetic.main.accounts_activity.*
import kotlinx.android.synthetic.main.add_account_layout.view.*
import velkus.moneybagsbudgeting.R
import velkus.moneybagsbudgeting.storage.DatabaseFactory
import velkus.moneybagsbudgeting.storage.dao.AccountDao
import velkus.moneybagsbudgeting.storage.models.Account

class AccountsActivity : AppCompatActivity() {

    private lateinit var viewModel: AccountsViewModel
    private lateinit var accountDao: AccountDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accounts_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AccountsFragment.newInstance())
                    .commitNow()
        }

        accountDao = DatabaseFactory.getInstance(this).accountDao

        viewModel = ViewModelProviders.of(this, AccountsViewModelFactory(accountDao)).get(AccountsViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.accounts_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.addAccount -> {
            val layoutInflater = LayoutInflater.from(this)
            val dialogView = layoutInflater.inflate(R.layout.add_account_layout, container, false)

            AlertDialog.Builder(this)
                    .setPositiveButton("Add") { dialog, _ ->
                        if (!dialogView.title.text.isEmpty()) {
                            val accountToAdd = Account()

                            accountToAdd.name = dialogView.title.text.toString().trim()

                            accountDao.saveAccount(accountToAdd)
                        }
                        dialog.dismiss()
                    }
                    .setView(dialogView)
                    .show().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
