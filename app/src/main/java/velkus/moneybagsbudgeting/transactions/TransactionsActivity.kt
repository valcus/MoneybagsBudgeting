package velkus.moneybagsbudgeting.transactions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import velkus.moneybagsbudgeting.transactions.ui.transactions.TransactionsFragment

class transactionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transactions_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TransactionsFragment.newInstance())
                    .commitNow()
        }
    }

}
