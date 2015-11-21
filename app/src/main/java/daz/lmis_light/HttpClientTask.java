package daz.lmis_light;

import android.os.AsyncTask;
import android.util.Log;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

/**
 * Created by Zubair<rajazubair.asghar@gmail.com> on 15.11.15.;
 */


public class HttpClientTask extends AsyncTask<String,Void, JSONResponse> {

    private Message message;
    private String text;
    private HttpRequest request = null;
    private static String dhisUrl = "https://play.dhis2.org/demo/api/messageConversations.json";
    private static String contentTypeJson = "application/json";
    private static String userName = "system";
    private static String passwd = "System123";



    public  HttpClientTask()
    {}

    @Override
    protected JSONResponse doInBackground(String... urls) {
        InputStream is = null;
        JSONResponse jsonResponse = new JSONResponse();

        try {

            List<Map<String,String>> users = new ArrayList<Map<String,String>>();
            users.add(new HashMap<String, String>(){{put("id", "PhzytPW3g2J");
            }});

            message = new Message();
            message.setText("Following mentioned commodities required.\n" + urls[0] + "\nRegards");
            message.setSubject("Logistic Message");
            message.setUsers(users);

            Gson gson = new Gson();
            String payLoad = gson.toJson(message);


            Log.d("D"," payload is "+payLoad.trim());
            byte[] bytePayload = payLoad.getBytes();
            request =  HttpRequest.post(dhisUrl).
                    basic(userName, passwd).
                    contentType(contentTypeJson).
                    send(payLoad.trim());

            if(request.created() || request.ok()){
                String response = request.body();
                jsonResponse.setStatus(200);
                jsonResponse.setResponse(response);
                Log.d("D", "created : "+response);
            }

            return jsonResponse;

        } catch (Exception e) {
            jsonResponse.setStatus(400);
            jsonResponse.setResponse(e.getMessage());
            return jsonResponse;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("D", "Pre");
    }

    @Override
    protected void onPostExecute(JSONResponse result) {
        Log.d("D", "post  " + " RESULT IS "+result.getResponse());
        super.onPostExecute(result);
        clearRequest();
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

    public  void setMessage(Message message)
    {
        this.message = message;
    }

    public Message getMessage()
    {
        return this.message;
    }

    public  void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }

}