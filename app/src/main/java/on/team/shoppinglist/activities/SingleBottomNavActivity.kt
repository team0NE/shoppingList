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
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    /*
    not working var
    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }*/
/*  drawer_layout???

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawer_layout, navController)
    }*/
    /*class SingleBottomNavActivity : AppCompatActivity(), ShoppingItemClickListener {
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private lateinit var shoppingListVM:ShoppingListViewModel
    lateinit var shoppingList: ArrayList<ShoppingCard>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        ButterKnife.bind(this)

        toolbar.title = "List of shopping items"

        shoppingListAdapter = ShoppingListAdapter(this, this)
        recyclerView.adapter = shoppingListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            val intent = Intent(applicationContext, AddShoppingCardActivity::class.java)
            startActivityForResult(intent, NEW_CARD_ACTIVITY_REQUEST_CODE)
            Toast.makeText(applicationContext, "addButton was clicked", Toast.LENGTH_SHORT).show()
        }

        shoppingListVM = ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
        shoppingListVM.getShoppingList().observe(this, androidx.lifecycle.Observer {
            this.shoppingList = ArrayList(it)
            shoppingListAdapter.setNotes(shoppingList)
            swipeListener(shoppingList)
        })
    }

    fun swipeListener(list: List<ShoppingCard>) {
        var simpleTouchCallback = object : ItemTouchHelper.SimpleCallback(0, LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == LEFT) {
                    var position = viewHolder.adapterPosition
                    var card = shoppingList[position]
                    shoppingListAdapter.removeItem(position)
                    card.isPurchased = true
                    shoppingListVM.updateCard(card)
                } else Toast.makeText(
                    this@SingleBottomNavActivity,
                    "Swipe left to mark as purchased item or right to delete it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onItemClickListener(position: Int) {
        var intent = Intent(this, EditShoppingCardActivity::class.java)
        intent.putExtra("card_id", shoppingList[position].id)
        var activity = this as Activity
        activity.startActivityForResult(intent, UPDATE_CARD_ACTIVITY_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_CARD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val card_id:String = UUID.randomUUID().toString()
            val date = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().time)
            val card = ShoppingCard(card_id, data!!.getStringExtra(AddShoppingCardActivity.CARD_ADDED), date)
            shoppingListVM.insert(card)
            Toast.makeText(applicationContext,
                "\"${card.description}\" was " + getString(R.string.saved),
                Toast.LENGTH_SHORT).show()
        } else if (requestCode == UPDATE_CARD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val card = ShoppingCard(
                data!!.getStringExtra(CARD_ID),
                data.getStringExtra(UPDATED_CARD),
                data.getStringExtra(CARD_DATE)
            )
            shoppingListVM.updateCard(card)
            Toast.makeText(applicationContext,
                "\"${card.description}\" was " + getString(R.string.updated),
                Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext,
                R.string.not_saved,
                Toast.LENGTH_SHORT).show()
        }
    }*/
}
