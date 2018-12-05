package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import model.Revenue;

public class RevenueDAO {
    private static final String TABLE = "revenue";
    private static DBGateway gw;

    public RevenueDAO(Context ctx) {
        gw = DBGateway.getInstance(ctx);
    }

    public Revenue read(){
        Revenue revenue;
        String readById = "SELECT * FROM " + TABLE + " WHERE revenue_id=" + 1 ;

        Cursor cursor = gw.getDatabase().rawQuery(readById, null);

        if (cursor.moveToFirst()){
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));

            revenue = new Revenue(amount);
            cursor.close();
            return revenue;
        }

        return null;
    }
    public boolean update(Revenue revenue) {
        ContentValues cv = new ContentValues();
        cv.put("amount", String.valueOf(revenue.getAmount()));

        return gw.getDatabase().update(TABLE, cv, "revenue_id=?", new String[]{ 1 + "" }) > 0;
    }


}
