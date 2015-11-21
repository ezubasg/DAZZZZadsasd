package daz.lmis_light;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.gson.Gson;


public class MainActivity extends Activity {

    TableLayout table_layout;
    EditText ETcommodityName, ETquantity;
    SQLController sqlController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlController = new SQLController(this);
        sqlController.open();
        ETcommodityName = (EditText) findViewById(R.id.ETcommodityName);
        ETquantity = (EditText) findViewById(R.id.ETquantity);
        table_layout = (TableLayout) findViewById(R.id.tableLayout1);
        makeTable();
    }

    public void btn_addClicked(View view){
        table_layout.removeAllViews();
        String commodityName = ETcommodityName.getText().toString();
        String quality = ETquantity.getText().toString();
        sqlController.open();
        sqlController.addData(commodityName, quality);
        ETcommodityName.setText("");
        ETquantity.setText("");
        makeTable();
    }

    public void btn_deleteClicked(View view){
        table_layout.removeAllViews();
        String commodityName = ETcommodityName.getText().toString();
        sqlController.open();
        Cursor c = sqlController.readData();
        if(c.getCount()==0){
            showMessage("Error","No data found");//show message}
        }else{
            sqlController.deleteData(commodityName);
        }
        ETcommodityName.setText("");
        ETquantity.setText("");
        makeTable();
    }

    public void btn_sendClicked(View view){
        sqlController.open();
        Cursor c = sqlController.readData();
        if(c.getCount()==0){
            showMessage("Error","No data found");//show message}
        }else{
            Log.d("DEBUG ", "Button Send Clicked ");
            sendMessageToDhis(sqlController.databaseToString());
            showtoast("Logistic Message Sent");
        }
    }

    private void sendMessageToDhis(String input) {
        try {
            HttpClientTask clientTask = new HttpClientTask();
            clientTask.execute(input);
        }catch (Exception e){}
    }

    public void showtoast(String message)
    {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    private void makeTable() {
        sqlController.open();
        Cursor c = sqlController.readData();
        int rows = c.getCount();
        int cols = c.getColumnCount();
        c.moveToFirst();
        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < cols; j++) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);
                tv.setText(c.getString(j));
                row.addView(tv);
            }
            c.moveToNext();
            table_layout.addView(row);
        }
        sqlController.close();
    }
}
