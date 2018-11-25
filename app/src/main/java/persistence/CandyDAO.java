package persistence;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import model.Candy;

public class CandyDAO {

    private static final String TABLE = "candies";
    private static DBGateway gw;

    public CandyDAO(Context ctx) {
        gw = DBGateway.getInstance(ctx);
    }

    public static ArrayList<Candy> getAll() {
        ArrayList<Candy> candies = new ArrayList<>();

        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM " + TABLE, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String type = cursor.getString(cursor.getColumnIndex("type"));

            candies.add(new Candy(Integer.parseInt(id), name, description, Integer.parseInt(price), type));
        }

        cursor.close();

        return candies;
    }

    public static Candy read(int id){
        Candy candy;
        String readById = "SELECT * FROM " + TABLE + " WHERE id =" + id ;

        Cursor cursor = gw.getDatabase().rawQuery(readById, null);

        if (cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String type = cursor.getString(cursor.getColumnIndex("type"));

            candy = new Candy(id, name, description, Integer.parseInt(price), type);
            cursor.close();
            return candy;
        }

        return null;
    }

}
