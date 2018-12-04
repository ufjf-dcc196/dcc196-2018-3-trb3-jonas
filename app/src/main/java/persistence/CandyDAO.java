package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import model.Candy;

public class CandyDAO {

    private static final String TABLE = "my_candies";
    private static DBGateway gw;

    public CandyDAO(Context ctx) {
        gw = DBGateway.getInstance(ctx);
    }

    public ArrayList<Candy> getAll() {
        ArrayList<Candy> candies = new ArrayList<>();

        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM " + TABLE, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("candy_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String type = cursor.getString(cursor.getColumnIndex("type"));

            candies.add(new Candy(Integer.parseInt(id), name, description, Integer.parseInt(price), type));
        }

        cursor.close();

        return candies;
    }

    public boolean create(Candy candy){
        ContentValues cv = new ContentValues();
        cv.put("name", candy.getName());
        cv.put("description", candy.getDescription());
        cv.put("price", candy.getPrice());
        cv.put("type", candy.getType());

        return gw.getDatabase().insert(TABLE, null, cv) > 0;
    }

    public Candy read(int id){
        Candy candy;
        String readById = "SELECT * FROM " + TABLE + " WHERE candy_id =" + id ;

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

    public boolean update(Candy candy) {
        if (!(candy.getId() > 0))
            return create(candy);

        ContentValues cv = new ContentValues();
        cv.put("name", candy.getName());
        cv.put("description", candy.getDescription());
        cv.put("price", candy.getPrice());
        cv.put("type", candy.getType());

        return gw.getDatabase().update(TABLE, cv, "candy_id=?", new String[]{ candy.getId() + "" }) > 0;
    }

}
