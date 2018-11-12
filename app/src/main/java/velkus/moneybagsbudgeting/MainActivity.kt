package velkus.moneybagsbudgeting

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_account_layout.view.*
import velkus.moneybagsbudgeting.storage.DatabaseFactory
import velkus.moneybagsbudgeting.storage.dao.AccountDao
import velkus.moneybagsbudgeting.storage.models.Account

class MainActivity : AppCompatActivity() {

    private lateinit var accountDao: AccountDao
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accountId = intent.getLongExtra("accountId", 1)

        setSupportActionBar(toolbar)

        viewPager.adapter = FragmentPagerAdapter(supportFragmentManager, accountId)
        tabs.setupWithViewPager(viewPager)

        accountDao = DatabaseFactory.getInstance(this).accountDao

        viewModel = ViewModelProviders.of(this, MainActivityViewModelFactory(accountDao, accountId)).get(MainActivityViewModel::class.java)

        viewModel.accountWithAssociations.observe(this, Observer { account ->
            supportActionBar!!.title = account!!.name //todo this doesn't work
        })
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