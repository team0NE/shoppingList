package on.team.shoppinglist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import on.team.shoppinglist.data.ShoppingAppRepo
import on.team.shoppinglist.data.ShoppingCard


class ShoppingListViewModel constructor(application: Application) : AndroidViewModel(application) {
    val TAG = this.javaClass.name
    /* var shoppingListDB:ShoppingListRoomDatabase
     private var shoppingListDao:ShoppingListDAO
     private var shoppingCardList: LiveData<List<ShoppingCard>>*/
    lateinit var repo: ShoppingAppRepo

    init {
        Log.i(TAG, "ShoppingListViewModel")
        /* shoppingListDB = ShoppingApp.database
         shoppingListDao = shoppingListDB.shoppingListDAO()
         shoppingCardList = shoppingListDao.getShoppingList()*/
        repo = ShoppingAppRepo.getInstance()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ShoppingListViewModel destroyed")
    }
    fun insert(shoppingCard: ShoppingCard) {
        repo.insert(shoppingCard)
    }
    fun deleteCard(shoppingCard: ShoppingCard) {
        repo.deleteCard(shoppingCard)
    }

    fun getCard(cardId: String): LiveData<ShoppingCard> {
        return repo.getCard(cardId)
    }

    fun updateCard(shoppingCard: ShoppingCard) {
        repo.updateCard(shoppingCard)
    }
    fun getShoppingList():LiveData<List<ShoppingCard>> {
        return repo.getShoppingList()
    }
    /* private class InsertAsyncTask(var shoppingListDAO: ShoppingListDAO) : AsyncTask<ShoppingCard, Void, Void>() {
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
     }*/
}