package on.team.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShoppingListDAO {
    @Insert
    suspend fun insert(shoppingCard: ShoppingCard)

    @Update
    suspend fun updateCard(shoppingCard: ShoppingCard)

    @Query("SELECT * FROM shopping_cards WHERE is_purchased = 0")
    fun getShoppingList(): LiveData<List<ShoppingCard>>

    @Query("SELECT * FROM shopping_cards WHERE is_purchased = 1")
    fun getPurchasedList(): LiveData<List<ShoppingCard>>

    @Query("Delete FROM shopping_cards WHERE is_purchased = 1")
    suspend fun deletePurchasedItems()
}