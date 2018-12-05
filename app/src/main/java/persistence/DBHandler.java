package persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "candies.db";

    private static final String TABLE_CANDIES = "my_candies";
    private static final String COLUMN_CANDY_ID = "candy_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String TABLE_HISTORY = "history";
    private static final String COLUMN_HISTORY_ID = "history_id";
    private static final String COLUMN_DATE = "date";

    private static final String TABLE_SHOWCASE = "showcase";
    private static final String COLUMN_SHOWCASE_ID = "showcase_id";
    private static final String COLUMN_QUANTITY = "quantity";


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

        db.execSQL("INSERT INTO " + TABLE_CANDIES + "( " +
                COLUMN_NAME + "," +
                COLUMN_DESCRIPTION + "," +
                COLUMN_PRICE + "," +
                COLUMN_TYPE +
                ") values " +
                "('Ovomaltine', 'Descricao', '250', 'Bombom')," +
                "('Brigadeiro', 'Descricao', '250', 'Bombom')," +
                "('Café', 'Descricao', '250', 'Bombom')," +
                "('Maracujá', 'Descricao', '250', 'Bombom')," +
                "('Morango', 'Descricao', '250', 'Bombom')," +
                "('Coco', 'Descricao', '250', 'Bombom')," +
                "('Amendoim', 'Descricao', '100', 'Barrinha')," +
                "('Coco', 'Descricao', '100', 'Barrinha')," +
                "('Crocante', 'Descricao', '100', 'Barrinha')," +
                "('Unicórnio', 'Descricao', '100', 'Barrinha')," +
                "('Panda', 'Descricao', '100', 'Barrinha')," +
                "('Marshmallow', 'Descricao', '100', 'Barrinha')"
        );

        String create_history_table = "CREATE TABLE " + TABLE_HISTORY + "(" +
                COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CANDY_ID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_PRICE + " INTEGER " +
                ");";

        db.execSQL(create_history_table);

        String create_showcase_table = "CREATE TABLE " + TABLE_SHOWCASE + "(" +
                COLUMN_SHOWCASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CANDY_ID + " INTEGER, " +
                COLUMN_QUANTITY + " INTEGER " +
                ");";

        db.execSQL(create_showcase_table);

        db.execSQL("INSERT INTO " + TABLE_SHOWCASE + "( " +
                COLUMN_CANDY_ID + "," +
                COLUMN_QUANTITY +
                ") values " +
                "('1', '20')," +
                "('2', '1')"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANDIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOWCASE);
        onCreate(db);
    }
}
