package on.team.shoppinglist.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import on.team.shoppinglist.ShoppingApp

class ShoppingAppRepo private constructor() {
    lateinit var shoppingListDao: ShoppingListDAO

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
    fun insert(shoppingCard: ShoppingCard) {
        InsertAsyncTask(shoppingListDao).execute(shoppingCard)
    }

    fun updateCard(shoppingCard: ShoppingCard) {
        UpdateAsyncTask(shoppingListDao).execute(shoppingCard)
    }

    fun deleteCard(shoppingCard: ShoppingCard) {
        DeleteAsyncTask(shoppingListDao).execute(shoppingCard)
    }

    fun getShoppingList(): LiveData<List<ShoppingCard>> {
        return shoppingListDao.getShoppingList()
    }

    private class InsertAsyncTask(var shoppingListDAO: ShoppingListDAO) : AsyncTask<ShoppingCard, Void, Void>() {
        override fun doInBackground(vararg shoppingCards: ShoppingCard): Void? {
            shoppingListDAO.insert(shoppingCards[0])
            return null
        }
    }

    private class UpdateAsyncTask(var shoppingListDAO: ShoppingListDAO) : AsyncTask<ShoppingCard, Void, Void>() {
        override fun doInBackground(vararg shoppingCards: ShoppingCard): Void? {
            shoppingListDAO.updateCard(shoppingCards[0])
            return null
        }
    }

    private class DeleteAsyncTask(var shoppingListDAO: ShoppingListDAO) : AsyncTask<ShoppingCard, Void, Void>() {
        override fun doInBackground(vararg shoppingCards: ShoppingCard): Void? {
            shoppingListDAO.deleteCard(shoppingCards[0])
            return null
        }
    }

    //PurchasedListVM
    fun getPurchasedList(): LiveData<List<ShoppingCard>> {
        return shoppingListDao.getPurchasedList()
    }

    fun deletePurchasedList() {
        shoppingListDao.deletePurchasedItems()
    }
}