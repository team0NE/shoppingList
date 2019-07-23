package on.team.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_cards")
class ShoppingCard constructor(id: String, description: String, date: String) {
    @PrimaryKey
    var id:String = id
    @ColumnInfo(name = "description")
    var description:String = description
    @ColumnInfo(name = "date")
    var date: String = date
    @ColumnInfo(name = "is_purchased")
    var isPurchased = false
}