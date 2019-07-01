package on.team.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_cards")
class ShoppingCard(id:String, description:String) {
    @PrimaryKey
    var id:String = id
    @ColumnInfo(name = "description")
    var description:String = description
}