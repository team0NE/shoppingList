package on.team.shoppinglist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import on.team.shoppinglist.data.ShoppingAppRepo
import on.team.shoppinglist.data.ShoppingCard

class PurchasedListViewModel constructor(application: Application) : AndroidViewModel(application) {
    private val TAG = this.javaClass.name
    var repo: ShoppingAppRepo

    init {
        Log.i(TAG, "ShoppingListViewModel")


        repo = ShoppingAppRepo.getInstance()
    }
    fun getPurchasedList(): LiveData<List<ShoppingCard>> {
        return repo.getPurchasedList()
    }

    suspend fun deletePurchasedList() {
        repo.deletePurchasedList()
    }
}