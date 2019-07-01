package on.team.shoppinglist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import on.team.shoppinglist.ShoppingApp
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.data.ShoppingListDAO
import on.team.shoppinglist.data.ShoppingListRoomDatabase

class EditCardViewModel constructor(application: Application) : AndroidViewModel(application) {
    val TAG = this.javaClass.name
    var shoppingListDB: ShoppingListRoomDatabase
    private var shoppingListDao: ShoppingListDAO

    init {
        Log.i(TAG, "EditCardViewModel")
        shoppingListDB = ShoppingApp.database
        shoppingListDao = shoppingListDB.shoppingListDAO()
    }
    fun getCard(cardId:String):LiveData<ShoppingCard> {
        return shoppingListDao.getCard(cardId)
    }
}