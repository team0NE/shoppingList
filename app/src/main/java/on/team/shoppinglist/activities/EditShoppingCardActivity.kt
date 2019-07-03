package on.team.shoppinglist.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import on.team.shoppinglist.R
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.utils.CARD_DATE
import on.team.shoppinglist.utils.CARD_ID
import on.team.shoppinglist.utils.UPDATED_CARD
import on.team.shoppinglist.viewmodel.EditCardViewModel

class EditShoppingCardActivity : AppCompatActivity() {
    @BindView(R.id.update_card)
    lateinit var editText: EditText
    lateinit var editCardViewModel: EditCardViewModel
    lateinit var bundle: Bundle
    lateinit var cardId:String
    lateinit var cardDate: String
    lateinit var shoppingCard:LiveData<ShoppingCard>

    val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        ButterKnife.bind(this)
        bundle = intent.extras
        if (bundle != null) cardId = bundle.getString("card_id")

        editCardViewModel = ViewModelProviders.of(this).get(EditCardViewModel::class.java)
        shoppingCard = editCardViewModel.getCard(cardId)
        shoppingCard.observe(this, Observer {
            editText.setText(shoppingCard.value?.description)
            cardDate = shoppingCard.value?.date.toString()
        })
    }

    fun updateNote(view: View) {

        var updatedNote = editText.text.toString()
        var resultIntent = Intent()
        resultIntent.putExtra(CARD_ID, cardId)
        resultIntent.putExtra(UPDATED_CARD, updatedNote)
        resultIntent.putExtra(CARD_DATE, cardDate)
        setResult(Activity.RESULT_OK, resultIntent)
        Log.i(TAG, "ShoppingCard was updated")
        finish()
    }
    fun cancelUpdate(view: View) {
        finish()
    }
}