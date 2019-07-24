package on.team.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import on.team.shoppinglist.R
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.ui.ShoppingListAdapter
import on.team.shoppinglist.ui.interfaces.ShoppingItemClickListener
import on.team.shoppinglist.viewmodel.ShoppingListViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListFragment : Fragment(), ShoppingItemClickListener {
    lateinit var fab: FloatingActionButton
    lateinit var recyclerView: RecyclerView
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private lateinit var shoppingListVM: ShoppingListViewModel
    lateinit var shoppingList: ArrayList<ShoppingCard>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.shopping_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        fab = view.findViewById(R.id.fab)

        shoppingListAdapter = ShoppingListAdapter(activity!!.applicationContext, this)
        recyclerView.adapter = shoppingListAdapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        fab.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this.context!!)
            val dialogView = layoutInflater.inflate(R.layout.add_new_item_fragment, null)
            val itemId: String = UUID.randomUUID().toString()
            val description = ""
            val presentDate: String = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().time)
            var newCard = ShoppingCard(itemId, description, presentDate)

            var newItemText: EditText = dialogView.findViewById(R.id.add_card_text)
            var applyButton: Button = dialogView.findViewById(R.id.save_btn)
            dialogBuilder.setTitle(getString(R.string.add_dialog_title))
                .setView(dialogView)
                .setCancelable(false)
            var alertDialog = dialogBuilder.create()
            alertDialog.show()
            applyButton.setOnClickListener {
                if (newItemText.text.toString().isEmpty()) {
                    Toast.makeText(
                        context,
                        "Please, input information",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    newCard.description = newItemText.text.toString()
                    shoppingListVM.insert(newCard)
                    alertDialog.dismiss()

                    Toast.makeText(
                        context,
                        "\"${newCard.description}\" was " + getString(R.string.saved),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            /* working code for activity variant
            val intent = Intent(context, AddShoppingCardActivity::class.java)
            startActivityForResult(intent, NEW_CARD_ACTIVITY_REQUEST_CODE)
            Toast.makeText(context, "addButton was clicked", Toast.LENGTH_SHORT).show()*/
        }

        shoppingListVM = ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
        shoppingListVM.getShoppingList().observe(this, androidx.lifecycle.Observer {
            this.shoppingList = ArrayList(it)
            shoppingListAdapter.setNotes(shoppingList)
            swipeListener(shoppingList)
        })
    }

    fun swipeListener(list: List<ShoppingCard>) {
        var simpleTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {
                    var position = viewHolder.adapterPosition
                    var card = shoppingList[position]
                    shoppingListAdapter.removeItem(position)
                    card.isPurchased = true
                    shoppingListVM.updateCard(card)
                } else Toast.makeText(
                    context,
                    "Swipe left to mark as purchased item or right to delete it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onItemClickListener(position: Int) {
        var editCard = shoppingList[position]

        val editDialogBuilder = AlertDialog.Builder(this.context!!)
        val editDialogView = layoutInflater.inflate(R.layout.edit_fragment, null)

        var editText: EditText = editDialogView.findViewById(R.id.update_card)
        var updateButton: Button = editDialogView.findViewById(R.id.update_btn)
        var cancelButton: Button = editDialogView.findViewById(R.id.cancel_btn)
        editDialogBuilder.setTitle(getString(R.string.edit_dialog_title))
            .setView(editDialogView)
            .setCancelable(false)
        editText.setText(editCard.description)
        var alertDialog = editDialogBuilder.create()
        alertDialog.show()
        cancelButton.setOnClickListener {
            Toast.makeText(this.context!!, R.string.not_saved, Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }
        updateButton.setOnClickListener {
            editCard.description = editText.text.toString()
            shoppingListVM.updateCard(editCard)
            Toast.makeText(this.context!!, R.string.saved, Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }
        /*  applyButton.setOnClickListener {
              if (newItemText.text.toString().isEmpty()) {
                  Toast.makeText(
                      context,
                      "Please, input information",
                      Toast.LENGTH_SHORT
                  ).show()
              }
              else {
                  newCard.description = newItemText.text.toString()
                  shoppingListVM.insert(newCard)
                  alertDialog.dismiss()

                  Toast.makeText(
                      context,
                      "\"${newCard.description}\" was " + getString(R.string.saved),
                      Toast.LENGTH_SHORT
                  ).show()
              }
          }*/


        /* working code for activity var
        var intent = Intent(context, EditShoppingCardActivity::class.java)
        intent.putExtra("card_id", shoppingList[position].id)
        var activity = this
        activity.startActivityForResult(intent, UPDATE_CARD_ACTIVITY_REQUEST_CODE)*/
    }

    /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (requestCode == NEW_CARD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
             val card_id: String = UUID.randomUUID().toString()
             val date = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().time)
             val card = ShoppingCard(card_id, data!!.getStringExtra(AddShoppingCardActivity.CARD_ADDED), date)
             shoppingListVM.insert(card)
             Toast.makeText(
                 context,
                 "\"${card.description}\" was " + getString(R.string.saved),
                 Toast.LENGTH_SHORT
             ).show()
         } else if (requestCode == UPDATE_CARD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
             val card = ShoppingCard(
                 data!!.getStringExtra(CARD_ID),
                 data.getStringExtra(UPDATED_CARD),
                 data.getStringExtra(CARD_DATE)
             )
             shoppingListVM.updateCard(card)
             Toast.makeText(
                 context,
                 "\"${card.description}\" was " + getString(R.string.updated),
                 Toast.LENGTH_SHORT
             ).show()
         } else {
             Toast.makeText(
                 context,
                 R.string.not_saved,
                 Toast.LENGTH_SHORT
             ).show()
         }
     }*/
}