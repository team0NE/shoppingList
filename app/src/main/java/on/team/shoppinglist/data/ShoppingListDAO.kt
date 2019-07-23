package on.team.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingListDAO {
    @Insert
    fun insert(shoppingCard:ShoppingCard)

    @Update
    fun updateCard(shoppingCard: ShoppingCard)

    @Delete
    fun deleteCard(shoppingCard: ShoppingCard)

    @Query("SELECT * FROM shopping_cards WHERE is_purchased = 0")
    fun getShoppingList(): LiveData<List<ShoppingCard>>

    @Query("SELECT * FROM shopping_cards WHERE is_purchased = 1")
    fun getPurchasedList(): LiveData<List<ShoppingCard>>

    @Query("SELECT * FROM shopping_cards WHERE id=:cardId")
    fun getCard(cardId:String):LiveData<ShoppingCard>

    @Query("Delete FROM shopping_cards WHERE is_purchased = 1")
    fun deletePurchasedItems()
}