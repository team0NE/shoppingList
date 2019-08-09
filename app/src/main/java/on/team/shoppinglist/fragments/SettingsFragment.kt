package on.team.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import butterknife.ButterKnife
import on.team.shoppinglist.R


class SettingsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)
        ButterKnife.bind(this, view)

        (activity as? AppCompatActivity)?.supportActionBar?.setTitle(R.string.destination_settings)
        return view
    }
}