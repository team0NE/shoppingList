package on.team.shoppinglist.viewmodel

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import on.team.shoppinglist.ShoppingApp
import on.team.shoppinglist.data.ShoppingCard
import on.team.shoppinglist.data.ShoppingListDAO
import on.team.shoppinglist.data.ShoppingListRoomDatabase


class ShoppingListViewModel constructor(application: Application) : AndroidViewModel(application) {
    val TAG = this.javaClass.name
    var shoppingListDB:ShoppingListRoomDatabase
    private var shoppingListDao:ShoppingListDAO
    private var shoppingCardList: LiveData<List<ShoppingCard>>

    init {
        Log.i(TAG, "ShoppingListViewModel")
        shoppingListDB = ShoppingApp.database
        shoppingListDao = shoppingListDB.shoppingListDAO()
        shoppingCardList = shoppingListDao.getShoppingList()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ShoppingListViewModel destroyed")
    }
    fun insert(shoppingCard: ShoppingCard) {
        InsertAsyncTask(shoppingListDao).execute(shoppingCard)
    }
    fun updateCard(shoppingCard: ShoppingCard) {
        UpdateAsyncTask(shoppingListDao).execute(shoppingCard)
    }
    fun deleteCard(shoppingCard: ShoppingCard) {
        DeleteAsyncTask(shoppingListDao).execute(shoppingCard)
    }
    fun getShoppingList():LiveData<List<ShoppingCard>> {
        return shoppingCardList
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
}