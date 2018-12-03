package persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "candies.db";

    private static final String TABLE_CANDIES = "candies";
    private static final String COLUMN_CANDY_ID = "candy_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String TABLE_HISTORY = "history";
    private static final String COLUMN_HISTORY_ID = "history_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_PAYMENT_METHOD = "payment_method";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_candies_table = "CREATE TABLE " + TABLE_CANDIES + "(" +
                COLUMN_CANDY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_TYPE+ " TEXT " +
        ");";

        db.execSQL(create_candies_table);

        String lorem = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        db.execSQL("INSERT INTO " + TABLE_CANDIES + "( " +
                COLUMN_NAME + "," +
                COLUMN_DESCRIPTION + "," +
                COLUMN_PRICE + "," +
                COLUMN_TYPE +

                ") values " +

                "('Ovomaltine', lorem, '250', 'Bombom')," +
                "('Brigadeiro', lorem, '250', 'Bombom')," +
                "('Café', lorem, '250', 'Bombom')," +
                "('Maracujá', lorem, '250', 'Bombom')," +
                "('Morango', lorem, '250', 'Bombom')," +
                "('Coco', lorem, '250', 'Bombom')," +

                "('Amendoim', lorem, '100', 'Barrinha')," +
                "('Coco', lorem, '100', 'Barrinha')," +
                "('Crocante', lorem, '100', 'Barrinha')," +
                "('Unicórnio', lorem, '100', 'Barrinha')," +
                "('Panda', lorem, '100', 'Barrinha')," +
                "('Marshmallow', lorem, '100', 'Barrinha')"
        );

        String create_history_table = "CREATE TABLE " + TABLE_HISTORY + "(" +
                COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CANDY_ID + " INTEGER, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_PAYMENT_METHOD + " TEXT " +
                ");";

        db.execSQL(create_history_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANDIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
}
