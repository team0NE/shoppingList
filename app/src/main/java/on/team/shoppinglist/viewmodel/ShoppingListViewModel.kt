package on.team.shoppinglist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import on.team.shoppinglist.data.ShoppingAppRepo
import on.team.shoppinglist.data.ShoppingCard


class ShoppingListViewModel constructor(application: Application) : AndroidViewModel(application) {
    val TAG = this.javaClass.name
    var repo: ShoppingAppRepo

    init {
        Log.i(TAG, "ShoppingListViewModel")
        repo = ShoppingAppRepo.getInstance()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ShoppingListViewModel destroyed")
    }

    suspend fun insert(shoppingCard: ShoppingCard) {
        repo.insert(shoppingCard)
    }

    suspend fun updateCard(shoppingCard: ShoppingCard) {
        repo.updateCard(shoppingCard)
    }
    fun getShoppingList():LiveData<List<ShoppingCard>> {
        return repo.getShoppingList()
    }
}