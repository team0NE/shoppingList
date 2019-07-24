package on.team.shoppinglist.data

import androidx.lifecycle.LiveData
import on.team.shoppinglist.ShoppingApp

class ShoppingAppRepo private constructor() {
    var shoppingListDao: ShoppingListDAO

    init {
        val shoppingListDB = ShoppingApp.database
        shoppingListDao = shoppingListDB.shoppingListDAO()
    }

    companion object {
        @Volatile
        private var instance: ShoppingAppRepo? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ShoppingAppRepo().also { instance = it }
            }
    }

    //ShoppingListVM
    suspend fun insert(shoppingCard: ShoppingCard) {
        shoppingListDao.insert(shoppingCard)
    }
    fun getShoppingList(): LiveData<List<ShoppingCard>> {
        return shoppingListDao.getShoppingList()
    }

    suspend fun updateCard(shoppingCard: ShoppingCard) {
        shoppingListDao.updateCard(shoppingCard)
    }

    //PurchasedListVM
    suspend fun deletePurchasedList() {
        shoppingListDao.deletePurchasedItems()
    }
    fun getPurchasedList(): LiveData<List<ShoppingCard>> {
        return shoppingListDao.getPurchasedList()
    }
}