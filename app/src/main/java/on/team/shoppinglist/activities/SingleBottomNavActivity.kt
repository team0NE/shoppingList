package on.team.shoppinglist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import on.team.shoppinglist.R
import on.team.shoppinglist.fragments.PurchasedListFragment
import on.team.shoppinglist.fragments.SettingsFragment
import on.team.shoppinglist.fragments.ShoppingListFragment

class SingleBottomNavActivity : AppCompatActivity() {
    @BindView(R.id.toolbar)
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    @BindView(R.id.bottom_navi)
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        ButterKnife.bind(this)

        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            val selectedFragment = when (menuItem.itemId) {
                R.id.destination_shopping -> ShoppingListFragment()
                R.id.destination_purchased -> PurchasedListFragment()
                R.id.destination_settings -> SettingsFragment()
                else -> throw IllegalStateException("not that item")
            }
            loadFragment(selectedFragment)
            true
        }
        bottomNav.selectedItemId = R.id.destination_shopping
        setSupportActionBar(toolbar)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
