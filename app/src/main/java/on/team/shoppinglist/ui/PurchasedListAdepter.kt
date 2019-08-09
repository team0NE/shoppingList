package on.team.shoppinglist.ui

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import on.team.shoppinglist.R
import on.team.shoppinglist.data.ShoppingCard

open class PurchasedListAdepter(context: Context) :
    RecyclerView.Adapter<PurchasedListAdepter.PurchasedListViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    open var adapterCardList: ArrayList<ShoppingCard> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasedListViewHolder {
        val itemView: View = layoutInflater.inflate(R.layout.purchased_list_item, parent, false)
        return PurchasedListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (adapterCardList != null) return adapterCardList.size
        else return 0
    }

    override fun onBindViewHolder(holder: PurchasedListViewHolder, position: Int) {
        if (adapterCardList != null) {
            val card = adapterCardList[position]
            val date = adapterCardList[position].date
            holder.setData(card.description, date, position)
        } else holder.cardItemView.text = R.string.no_card.toString()
    }

    open fun setNotes(shoppingCards: List<ShoppingCard>) {
        this.adapterCardList = ArrayList(shoppingCards)
        notifyDataSetChanged()
    }

    open inner class PurchasedListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        open var cardItemView: TextView = itemView.findViewById(R.id.card_text)
        open var dateView: TextView = itemView.findViewById(R.id.card_date)
        private var hPosition: Int = 0

        open fun setData(card: String, date: String, position: Int) {
            cardItemView.text = card
            cardItemView.paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG)
            dateView.text = date
            hPosition = position
        }

    }
}