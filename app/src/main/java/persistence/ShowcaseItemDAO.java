package persistence;

import android.content.ContentValues;
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

    public ShowcaseItem read(Integer id) {
        ShowcaseItem showcaseItem;

        String getAllQuery = "SELECT * FROM " + TABLE + " WHERE showcase_id=" + id;

        Cursor cursor = gw.getDatabase().rawQuery(getAllQuery, null);

        if (cursor.moveToFirst()){
            String quantity = cursor.getString(cursor.getColumnIndex("quantity"));
            String candyId = cursor.getString(cursor.getColumnIndex("candy_id"));
            showcaseItem = new ShowcaseItem(id, Integer.parseInt(candyId),Integer.parseInt(quantity));

            cursor.close();

            return showcaseItem;
        }

        return null;

    }

    public static boolean update(ShowcaseItem showcaseItem) {
        if ((showcaseItem.getShowcaseItemId() > 0)) {
            ContentValues cv = new ContentValues();
            cv.put("quantity", showcaseItem.getQuantity());

            return gw.getDatabase().update(TABLE, cv, "showcase_id=?", new String[]{showcaseItem.getShowcaseItemId() + ""}) > 0;
        }
        return false;
    }
}
