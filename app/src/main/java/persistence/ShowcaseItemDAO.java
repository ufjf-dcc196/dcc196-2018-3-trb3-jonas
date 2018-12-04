package persistence;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import model.ShowcaseItem;

public class ShowcaseItemDAO {

    private static final String TABLE = "showcase";
    private static DBGateway gw;

    public ShowcaseItemDAO(Context ctx) {
        gw = DBGateway.getInstance(ctx);
    }

    public ArrayList<ShowcaseItem> getAll() {
        ArrayList<ShowcaseItem> showcase = new ArrayList<>();

        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM " + TABLE, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("showcase_id"));
            String candyId = cursor.getString(cursor.getColumnIndex("candy_id"));
            String quantity = cursor.getString(cursor.getColumnIndex("quantity"));

            showcase.add(new ShowcaseItem(Integer.parseInt(id),Integer.parseInt(candyId), Integer.parseInt(quantity)));
        }

        cursor.close();

        return showcase;
    }

    public static void update(ShowcaseItem showcaseItem) {
    }
}
