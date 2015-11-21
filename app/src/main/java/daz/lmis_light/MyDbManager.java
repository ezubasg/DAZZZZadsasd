package daz.lmis_light;

/**
 * Created by Devesh on 18/11/15.
 */
    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class MyDbManager extends SQLiteOpenHelper {

        static final String DB_NAME = "COMMODITY.DB";     // Name of the database
        static final int DB_VERSION = 1;// Database Version
        public static final String TABLE_COMMODITY = "commodity";     // Name of the Table
        public static final String COMMODITY_ID = "commodityId";               //ID Primary Key
        public static final String COMMODITY_NAME = "commodityName";     //First Column
        public static final String QUANTITY = "quantity";             //Second Column



        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_COMMODITY
                + "(" + COMMODITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COMMODITY_NAME + " TEXT ," + QUANTITY
                + " TEXT );";



        public MyDbManager(Context context) {
            super(context, DB_NAME, null, DB_VERSION);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMODITY);
            onCreate(db);

        }


    }
