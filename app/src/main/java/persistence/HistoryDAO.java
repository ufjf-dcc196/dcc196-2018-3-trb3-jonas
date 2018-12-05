package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import model.Candy;
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
            String id = cursor.getString(cursor.getColumnIndex("history_id"));
            String candyId = cursor.getString(cursor.getColumnIndex("candy_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String price = cursor.getString(cursor.getColumnIndex("price"));


            history.add(new History(Integer.parseInt(id), Integer.parseInt(candyId),name, type, date, price));
        }

        cursor.close();

        return history;
    }

    public boolean create(int candyId, Context ctx){
        CandyDAO candyDAO = new CandyDAO(ctx);
        Candy candy = candyDAO.read(candyId);

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = s.format(Calendar.getInstance().getTime());

        ContentValues cv = new ContentValues();
        cv.put("candy_id", candyId);
        cv.put("name", candy.getName());
        cv.put("type", candy.getType());
        cv.put("date", date);
        cv.put("price", candy.printPrice());

        return gw.getDatabase().insert(TABLE, null, cv) > 0;
    }

    public History getLast() {
        History history;
        String getAllQuery = "SELECT * FROM " + TABLE + " ORDER BY history_id DESC";

        Cursor cursor = gw.getDatabase().rawQuery(getAllQuery, null);

        if (cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("history_id"));
            int candyId = cursor.getInt(cursor.getColumnIndex("candy_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            history = new History(id, candyId, name, type, date, price);
            cursor.close();

            return history;
        }

        return null;
    }
}
