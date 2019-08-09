package on.team.shoppinglist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import on.team.shoppinglist.R
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.ui.interfaces.ShoppingItemClickListener

class ShoppingListAdapter(context: Context, itemListener: ShoppingItemClickListener) :
    RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    private var itemClickListener = itemListener
    private val layoutInflater:LayoutInflater = LayoutInflater.from(context)
    var adapterCardList: ArrayList<ShoppingCard> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val itemView: View = layoutInflater.inflate(R.layout.shopping_list_item, parent, false)
        return ShoppingListViewHolder(itemView, itemClickListener)
    }
    override fun getItemCount(): Int {
        if (adapterCardList!=null) return adapterCardList.size
        else return 0
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        if (adapterCardList != null) {
            val card = adapterCardList[position]
            val date = adapterCardList[position].date
            holder.setData(card.description, date, position)
        } else holder.cardItemView.text = R.string.no_card.toString()
    }
    fun setNotes(shoppingCards:List<ShoppingCard>) {
        this.adapterCardList = ArrayList(shoppingCards)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        adapterCardList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, adapterCardList.size)
        notifyDataSetChanged()
    }

    inner class ShoppingListViewHolder(itemView: View, itemClickListener: ShoppingItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var cardItemView: TextView = itemView.findViewById(R.id.card_text)
        var dateView: TextView = itemView.findViewById(R.id.card_date)
        private var hPosition: Int = 0
        var holderItemClickListener: ShoppingItemClickListener = itemClickListener

        init {
            itemView.setOnClickListener(this)
        }

        fun setData(card: String, date: String, position: Int) {
            cardItemView.text = card
            dateView.text = date
            hPosition = position
        }

        override fun onClick(v: View?) {
            holderItemClickListener.onItemClickListener(adapterPosition)

        }
    }
}