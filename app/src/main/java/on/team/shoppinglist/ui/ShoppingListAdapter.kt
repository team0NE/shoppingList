package on.team.shoppinglist.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import on.team.shoppinglist.R
import on.team.shoppinglist.activities.EditShoppingCardActivity
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.utils.UPDATE_CARD_ACTIVITY_REQUEST_CODE

class ShoppingListAdapter(private val context: Context, onDeleteClickList: OnDeleteClickListener) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {
    interface OnDeleteClickListener {
        fun onDeleteClickListener(shoppingCard: ShoppingCard)
    }
    private var onDeleteClickListener: OnDeleteClickListener = onDeleteClickList
    private val layoutInflater:LayoutInflater = LayoutInflater.from(context)
    var adapterCardList:List<ShoppingCard> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        var itemView:View = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ShoppingListViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        if (adapterCardList!=null) return adapterCardList.size
        else return 0
    }
    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        if (adapterCardList != null) {
            var card = adapterCardList[position]
            holder.setData(card.description, position)
            holder.setListeners()
        } else holder.cardItemView.text = R.string.no_card.toString()
    }
    fun setNotes(shoppingCards:List<ShoppingCard>) {
        this.adapterCardList = shoppingCards
        notifyDataSetChanged()
    }

    inner class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardItemView:TextView = itemView.findViewById(R.id.tvCard)
        var editButton: ImageView = itemView.findViewById(R.id.ivRowEdit)
        var deleteButton: ImageView = itemView.findViewById(R.id.ivRowDelete)
        private var hPosition: Int = 0

        fun setData(card: String, position: Int) {
            cardItemView.text = card
            hPosition = position
        }
        fun setListeners() {
            editButton.setOnClickListener {
                var intent = Intent(context, EditShoppingCardActivity::class.java)
                intent.putExtra("card_id", adapterCardList[hPosition].id)
                var activity = context as Activity
                activity.startActivityForResult(intent, UPDATE_CARD_ACTIVITY_REQUEST_CODE)
            }
            deleteButton.setOnClickListener {
                if (onDeleteClickListener != null)
                    onDeleteClickListener.onDeleteClickListener(adapterCardList[hPosition])
            }
        }
    }
}