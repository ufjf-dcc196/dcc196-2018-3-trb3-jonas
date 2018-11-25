package persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {
    private static DBGateway gw;
    private SQLiteDatabase db;

    private DBGateway(Context ctx){
        DBHandler helper = new DBHandler(ctx);
        db = helper.getWritableDatabase();
    }

    public static DBGateway getInstance(Context context){
        if(gw == null)
            gw = new DBGateway(context);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}
