package on.team.shoppinglist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import on.team.shoppinglist.data.ShoppingAppRepo
import on.team.shoppinglist.data.ShoppingCard

class PurchasedListViewModel constructor(application: Application) : AndroidViewModel(application) {
    private val TAG = this.javaClass.name
    /*private var shoppingListDB: ShoppingListRoomDatabase
    private var shoppingListDao: ShoppingListDAO
    private var purchasedCardList: LiveData<List<ShoppingCard>>*/
    lateinit var repo: ShoppingAppRepo

    init {
        Log.i(TAG, "ShoppingListViewModel")

        /*shoppingListDB = ShoppingApp.database
        shoppingListDao = shoppingListDB.shoppingListDAO()
        purchasedCardList = shoppingListDao.getPurchasedList()*/
        repo = ShoppingAppRepo.getInstance()
    }

    fun getPurchasedList(): LiveData<List<ShoppingCard>> {
        return repo.getPurchasedList()
    }

    private fun deletePurchasedList() {
        repo.deletePurchasedList()
    }
}