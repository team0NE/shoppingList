package on.team.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import on.team.shoppinglist.R
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.ui.PurchasedListAdepter
import on.team.shoppinglist.viewmodel.PurchasedListViewModel

class PurchasedListFragment : Fragment() {
    lateinit var purchasedListVM: PurchasedListViewModel
    @BindView(R.id.purchased_recycler_view)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.clear_button)
    lateinit var clearButton: ImageButton
    private lateinit var purchasedListAdapter: PurchasedListAdepter
    lateinit var purchasedList: ArrayList<ShoppingCard>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.purchased_list_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        purchasedListAdapter = PurchasedListAdepter(activity!!.applicationContext)
        recyclerView.adapter = purchasedListAdapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager


        purchasedListVM = ViewModelProviders.of(this).get(PurchasedListViewModel::class.java)
        purchasedListVM.getPurchasedList().observe(this, androidx.lifecycle.Observer {
            this.purchasedList = ArrayList(it)
            purchasedListAdapter.setNotes(purchasedList)

            clearButton.setOnClickListener {
                Toast.makeText(context, "delete btn was clicked", Toast.LENGTH_SHORT).show()
            }

        })
    }
}