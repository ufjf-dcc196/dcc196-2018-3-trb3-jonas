package persistence;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import model.History;

public class HistoryDAO {

    private static final String TABLE = "history";
    private static DBGateway gw;

    public HistoryDAO(Context ctx) {
        gw = DBGateway.getInstance(ctx);
    }

    public static ArrayList<History> getAll() {
        ArrayList<History> history = new ArrayList<>();

        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM " + TABLE, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String candyId = cursor.getString(cursor.getColumnIndex("name"));
            String date = cursor.getString(cursor.getColumnIndex("description"));
            String location = cursor.getString(cursor.getColumnIndex("price"));
            String price = cursor.getString(cursor.getColumnIndex("type"));
            String paymentMethod = cursor.getString(cursor.getColumnIndex("type"));

            history.add(new History(Integer.parseInt(id), Integer.parseInt(candyId), date, location, price, paymentMethod));
        }

        cursor.close();

        return history;
    }
}
