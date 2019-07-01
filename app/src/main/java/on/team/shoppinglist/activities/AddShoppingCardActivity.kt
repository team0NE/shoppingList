package on.team.shoppinglist.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import on.team.shoppinglist.R

class AddShoppingCardActivity : AppCompatActivity() {
    @BindView(R.id.bAdd)
    lateinit var button: Button
    @BindView(R.id.etNewCard)
    lateinit var editText: EditText
    lateinit var resultIntent:Intent

    companion object {
        const val CARD_ADDED = "new_card"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        ButterKnife.bind(this)

        button.setOnClickListener {
            resultIntent = Intent()

            if (editText.text.isEmpty()) setResult(Activity.RESULT_CANCELED, resultIntent)
            else {
                var card = editText.text.toString()
                resultIntent.putExtra(CARD_ADDED, card)
                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()
        }
    }
}