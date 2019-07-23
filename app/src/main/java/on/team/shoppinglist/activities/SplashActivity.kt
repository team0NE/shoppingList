package on.team.shoppinglist.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import on.team.shoppinglist.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        val runnable = Runnable {
            val intent = Intent(this, SingleBottomNavActivity::class.java)
            startActivity(intent)
            finish()
        }
        handler.postDelayed(runnable, 2000)
        /*val intent = Intent(this, SingleBottomNavActivity::class.java)
        startActivity(intent)
        finish()*/
    }
}