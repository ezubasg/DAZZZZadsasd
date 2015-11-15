package com.example.iqra.lmis_light;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private LinearLayout linear1 = null;
    private EditText numberOfMedicine = null;
    private Button btnSubmit = null;
    private Button btnSend = null;
    private TableLayout tbLayout1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        numberOfMedicine = (EditText) findViewById(R.id.editText);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tbLayout1 = (TableLayout) findViewById(R.id.tableLayout1);
    }


    public  void editTextClicked(View view)
    {

       numberOfMedicine.setText("");

    }

    public void BtnClicked(View view)
    {
        // add edit text

        int count = Integer.parseInt(numberOfMedicine.getText().toString());
        EditText medicineName = null;
        EditText quantity = null;

        final List<EditText> itemListMedicine = new ArrayList<EditText>();
        List<EditText> itemListQuantity = new ArrayList<EditText>();

        TableRow tbRow1 = null;

        for (int i = 0; i < count; i++) {

            tbRow1 = new TableRow(this);

            medicineName = new EditText(MainActivity.this);
            quantity = new EditText(MainActivity.this);


            medicineName.setText("Medicine Name");
            medicineName.setId(i + 1);
            quantity.setId(i+100);

            quantity.setText("Quantity");
            itemListMedicine.add(medicineName);
            itemListQuantity.add(quantity);


            tbRow1.addView(medicineName);
            tbRow1.addView(quantity);
            tbLayout1.addView(tbRow1);



        }

        btnSend = new Button(this);
        btnSend.setText("SendMessage");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.d("DEBUG ", "Button Send Clicked ");
                    showtoast(itemListMedicine.get(0).getText().toString());
                    sendMessageToDhis("INPUT");
            }
        });

        tbLayout1.addView(btnSend);

    }

    private void sendMessageToDhis(String imput) {
        try {

            String url = String.format("http://jsonplaceholder.typicode.com/posts/1");
            HttpClientTask clientTask = new HttpClientTask() {

                @Override
                protected void onPostExecute(JSONResponse result) {

                        Log.d("Response", result.response);


                }
            };

            clientTask.execute(url);


        }catch (Exception e){}
    }

    public void showtoast(String message)
      {

          Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
          toast.show();
      }

}
