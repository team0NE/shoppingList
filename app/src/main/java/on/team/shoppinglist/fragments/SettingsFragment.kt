package on.team.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import butterknife.ButterKnife
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import on.team.shoppinglist.R
import on.team.shoppinglist.viewmodel.PurchasedListViewModel


class SettingsFragment : Fragment() {

    lateinit var purchasedListVM: PurchasedListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)
        ButterKnife.bind(this, view)

        (activity as? AppCompatActivity)?.supportActionBar?.setTitle(R.string.destination_settings)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        purchasedListVM = ViewModelProviders.of(this).get(PurchasedListViewModel::class.java)

        settings_fragment__database_card_clear_purchased_button.setOnClickListener {
            Toast.makeText(context, "All purchased item was cleared", Toast.LENGTH_SHORT).show()
            GlobalScope.launch(Dispatchers.Main) {
                purchasedListVM.deletePurchasedList() // Temporary variant. BAD 4 PRODUCTION
            }
        }
    }
}