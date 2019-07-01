package on.team.shoppinglist

import android.app.Application
import androidx.room.Room
import on.team.shoppinglist.data.ShoppingListRoomDatabase

class ShoppingApp : Application() {
    companion object {
        lateinit var database: ShoppingListRoomDatabase
        lateinit var instance: ShoppingApp
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, ShoppingListRoomDatabase::class.java,
            "shopping_cards").build()
        instance = this
    }
}