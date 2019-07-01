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

    @Query("SELECT * FROM shopping_cards")
    fun getCardList(): LiveData<List<ShoppingCard>>

    @Query("SELECT * FROM shopping_cards WHERE id=:cardId")
    fun getCard(cardId:String):LiveData<ShoppingCard>
}