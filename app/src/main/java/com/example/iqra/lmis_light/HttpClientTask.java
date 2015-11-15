package com.example.iqra.lmis_light;

/**
 * Created by iqra on 15.11.15.
 */


import android.os.AsyncTask;
import android.util.Log;
import java.io.InputStream;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

//import com.spearcom.Models.JSONResponse;


public class HttpClientTask extends AsyncTask<String,Void, JSONResponse> {

    HttpRequest request = null;

    @Override
    protected JSONResponse doInBackground(String... urls) {
        InputStream is = null;
        JSONResponse jsonResponse = new JSONResponse(400, "Request failed");

        try {

            Log.d("D", urls[0]);
            Log.d("D", "Start Request");


            request =  HttpRequest.get(urls[0]);

            Log.d("D", "post Request");

            if(request.ok()){
                String response = request.body().toString();
                Gson gson = new Gson();
                jsonResponse = gson.fromJson(response, JSONResponse.class);
            }

            clearRequest();

            return jsonResponse;

        } catch (Exception e) {
            jsonResponse.status = 400;
            jsonResponse.response = e.toString();
            return jsonResponse;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        clearRequest();
        Log.d("D", "Pre");
    }

    @Override
    protected void onPostExecute(JSONResponse result) {
        clearRequest();
        Log.d("D", "post");
        super.onPostExecute(result);
    }

    @Override
    protected void onCancelled(JSONResponse jsonResponse) {
        clearRequest();
        Log.d("D", "cancel");
        super.onCancelled(jsonResponse);
    }

    private  void clearRequest(){
        if(request!=null){
            request.disconnect();
            Log.d("D", "clearRequest");
        }
    }
}