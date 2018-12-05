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

    public boolean create(ShowcaseItem showcaseItem){
        ContentValues cv = new ContentValues();
        cv.put("candy_id", showcaseItem.getCandyId());
        cv.put("quantity", showcaseItem.getQuantity());

        return gw.getDatabase().insert(TABLE, null, cv) > 0;
    }

    public static boolean update(ShowcaseItem showcaseItem) {
        if ((showcaseItem.getShowcaseItemId() > 0)) {
            ContentValues cv = new ContentValues();
            cv.put("quantity", showcaseItem.getQuantity());

            return gw.getDatabase().update(TABLE, cv, "showcase_id=?", new String[]{showcaseItem.getShowcaseItemId() + ""}) > 0;
        }
        return false;
    }

    public ShowcaseItem getLast() {
        ShowcaseItem item;
        String getAllQuery = "SELECT * FROM " + TABLE + " ORDER BY showcase_id DESC";

        Cursor cursor = gw.getDatabase().rawQuery(getAllQuery, null);

        if (cursor.moveToFirst()){
            int showcaseId = cursor.getInt(cursor.getColumnIndex("showcase_id"));
            int candyId = cursor.getInt(cursor.getColumnIndex("candy_id"));
            int quantity  = cursor.getInt(cursor.getColumnIndex("quantity"));

            item = new ShowcaseItem(showcaseId, candyId, quantity);
            cursor.close();

            return item;
        }

        return null;
    }
}
