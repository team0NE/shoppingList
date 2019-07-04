package on.team.shoppinglist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(ShoppingCard::class)], version = 1)
abstract class ShoppingListRoomDatabase : RoomDatabase() {
    abstract fun shoppingListDAO():ShoppingListDAO
}