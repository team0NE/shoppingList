package on.team.shoppinglist.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_cards")
public class JShoppingCard implements Parcelable {
    public static final Creator<JShoppingCard> CREATOR = new Creator<JShoppingCard>() {
        @Override
        public JShoppingCard createFromParcel(Parcel in) {
            return new JShoppingCard(in);
        }

        @Override
        public JShoppingCard[] newArray(int size) {
            return new JShoppingCard[size];
        }
    };
    @PrimaryKey
    String id;
    @ColumnInfo(name = "description")
    String description;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "is_purchased")
    boolean isPurchased = false;

    public JShoppingCard(
            String constructId,
            String constructDescription,
            String constructDate
    ) {
        id = constructId;
        description = constructDescription;
        date = constructDate;
    }

    protected JShoppingCard(Parcel in) {
        id = in.readString();
        description = in.readString();
        date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(description);
        dest.writeString(date);
    }
}
