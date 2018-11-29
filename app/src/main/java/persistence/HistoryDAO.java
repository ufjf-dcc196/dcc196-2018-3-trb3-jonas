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
            String candyId = cursor.getString(cursor.getColumnIndex("candyId"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String paymentMethod = cursor.getString(cursor.getColumnIndex("paymentMethod"));

            history.add(new History(Integer.parseInt(id), Integer.parseInt(candyId), date, location, price, paymentMethod));
        }

        cursor.close();

        return history;
    }
}
