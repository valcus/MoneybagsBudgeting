package velkus.moneybagsbudgeting

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import velkus.moneybagsbudgeting.accounts.AccountsFragment
import velkus.moneybagsbudgeting.transactions.TransactionsFragment

class FragmentPagerAdapter(fm: FragmentManager, private val accountId: Long): FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AccountsFragment.newInstance()
            1 -> TransactionsFragment.newInstance(accountId)
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Child Accounts"
            1 -> "Transactions"
            else -> "error"
        }
    }
}