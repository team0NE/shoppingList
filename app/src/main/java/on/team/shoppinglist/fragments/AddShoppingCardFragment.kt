package on.team.shoppinglist.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import on.team.shoppinglist.R

class AddShoppingCardFragment : Fragment() {
    @BindView(R.id.save_btn)
    lateinit var button: Button
    @BindView(R.id.add_card_text)
    lateinit var addText: EditText
    lateinit var resultIntent: Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.add_new_item_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            *//*resultIntent = Intent()

            if (addText.text.isEmpty()) setResult(Activity.RESULT_CANCELED, resultIntent)
            else {
                var card = addText.text.toString()
                resultIntent.putExtra(AddShoppingCardActivity.CARD_ADDED, card)
                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()*//*
        }
    }*/
}