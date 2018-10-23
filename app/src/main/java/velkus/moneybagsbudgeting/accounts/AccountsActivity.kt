package velkus.moneybagsbudgeting.accounts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import velkus.moneybagsbudgeting.accounts.ui.accounts.AccountsFragment

class AccountsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accounts_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AccountsFragment.newInstance())
                    .commitNow()
        }
    }

}
