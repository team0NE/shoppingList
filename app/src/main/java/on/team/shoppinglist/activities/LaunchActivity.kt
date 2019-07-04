package on.team.shoppinglist.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.floatingactionbutton.FloatingActionButton
import on.team.shoppinglist.R
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.ui.ShoppingListAdapter
import on.team.shoppinglist.utils.*
import on.team.shoppinglist.viewmodel.ShoppingListViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LaunchActivity : AppCompatActivity(), ShoppingListAdapter.OnDeleteClickListener {
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
        setContentView(R.layout.activity_launch)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

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
                    this@LaunchActivity,
                    "Swipe left to mark as purchased item or right to delete it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    override fun onDeleteClickListener(shoppingCard: ShoppingCard) {
        shoppingListVM.deleteCard(shoppingCard)
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
    }
}
