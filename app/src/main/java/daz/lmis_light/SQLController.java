package daz.lmis_light;

/**
 * Created by Devesh on 18/11/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

    private MyDbManager dbManager;
    private Context context;
    private SQLiteDatabase db;

    public SQLController(Context c) {
        context = c;
    }

    public SQLController open() throws SQLException {
        dbManager = new MyDbManager(context);
        db = dbManager.getWritableDatabase();
        return this;

    }

    public void close() {
        dbManager.close();
    }



    public void addData(String commodityName, String quantity) {

        ContentValues cv = new ContentValues();
        cv.put(MyDbManager.COMMODITY_NAME, commodityName);
        cv.put(MyDbManager.QUANTITY, quantity);
        db.insert(MyDbManager.TABLE_COMMODITY, null, cv);

    }


    public Cursor readData() {

        String[] allColumns = new String[] {MyDbManager.COMMODITY_NAME,
                MyDbManager.QUANTITY };

        Cursor c = db.query(MyDbManager.TABLE_COMMODITY, allColumns, null, null, null,
                null, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;

    }

    public void deleteData(String commodityName) {
        db.execSQL("DELETE FROM " + MyDbManager.TABLE_COMMODITY + " WHERE " + MyDbManager.COMMODITY_NAME +
                "=\"" + commodityName + "\";");
    }

    public void deleteAllData(){

        db.execSQL("DELETE * FROM " + MyDbManager.TABLE_COMMODITY + ";");


    }

}