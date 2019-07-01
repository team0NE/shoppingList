package on.team.shoppinglist.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.floatingactionbutton.FloatingActionButton
import on.team.shoppinglist.R

import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.ui.ShoppingListAdapter
import on.team.shoppinglist.utils.NEW_CARD_ACTIVITY_REQUEST_CODE
import on.team.shoppinglist.utils.CARD_ID
import on.team.shoppinglist.utils.UPDATED_CARD
import on.team.shoppinglist.utils.UPDATE_CARD_ACTIVITY_REQUEST_CODE
import on.team.shoppinglist.viewmodel.ShoppingListViewModel
import java.util.*

class LaunchActivity : AppCompatActivity(), ShoppingListAdapter.OnDeleteClickListener {
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton
    @BindView(R.id.recyclerview)
    lateinit var recyclerView: RecyclerView
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private lateinit var shoppingListVM:ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        shoppingListAdapter = ShoppingListAdapter(this, this)
        recyclerView.adapter = shoppingListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            var intent = Intent(applicationContext, AddShoppingCardActivity::class.java)
            startActivityForResult(intent, NEW_CARD_ACTIVITY_REQUEST_CODE)
            Toast.makeText(applicationContext, "addButton was clicked", Toast.LENGTH_SHORT).show()
        }

        shoppingListVM = ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
        shoppingListVM.getShoppingList().observe(this, androidx.lifecycle.Observer {
            shoppingListAdapter.setNotes(it)

        })
    }
    override fun onDeleteClickListener(shoppingCard: ShoppingCard) {
        shoppingListVM.deleteCard(shoppingCard)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_CARD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val card_id:String = UUID.randomUUID().toString()
            var card = ShoppingCard(card_id, data!!.getStringExtra(AddShoppingCardActivity.CARD_ADDED))
            shoppingListVM.insert(card)
            Toast.makeText(applicationContext,
                "\"${card.description}\" was " + getString(R.string.saved),
                Toast.LENGTH_SHORT).show()
        } else if (requestCode == UPDATE_CARD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            var card = ShoppingCard(
                data!!.getStringExtra(CARD_ID),
                data.getStringExtra(UPDATED_CARD)
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
