package daz.lmis_light;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

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
    private static String dhisMessageUrl = "https://play.dhis2.org/dev/api/messageConversations";
    private static String dhisRecipientUrl = "https://play.dhis2.org/dev/api/";
    private static String contentTypeJson = "application/json";
    private static String userName = "system";
    private static String passwd = "System123";
    private List<Map<String,String>> users;
    private HttpRequestType requestType;
    private ProgramTaskType programTaskType;

    private List<String> userNames;
    private Map<String,String> userIDMapper;


    private JSONResponse postMessageToServer(String text)
    {

        JSONResponse jsonResponse = new JSONResponse();
        if (requestType == HttpRequestType.POST) {
            try {

                users = new ArrayList<Map<String, String>>();
                users.add(new HashMap<String, String>() {{
                    put("id", "GOLswS44mh8");
                }});

                message = new Message();
                message.setSubject("Logistic Message");
                message.setText("Following mentioned commodities required.\n" + text + "\nRegards");
                message.setUsers(users);

                Gson gson = new Gson();
                String payLoad = gson.toJson(message);
                Log.d("D", " payload is " + payLoad.trim());
                byte[] bytePayload = payLoad.getBytes();
                request = HttpRequest.post(dhisMessageUrl).
                        basic(userName, passwd).
                        contentType(contentTypeJson).
                        send(payLoad.trim());

                if (request.created() || request.ok()) {
                    String response = request.body();
                    jsonResponse.setStatus(200);
                    jsonResponse.setResponse(response);
                    Log.d("D", "created : " + response);
                    return jsonResponse;
                }

            } catch (Exception e) {
                jsonResponse.setStatus(400);
                jsonResponse.setResponse(e.getMessage());
                return jsonResponse;
            }
        }

            jsonResponse.setResponse("Nothing Happend ");
            return jsonResponse;

    }

    private  void processResult(List<Map<String,String>> input)
    {
        List<String> names = new ArrayList<String>();

        Map<String,String> nameIdMapper = new HashMap<String,String>();

        for (Map<String,String> object: input)
        {
            names.add(object.get("name"));
            nameIdMapper.put(object.get("name"),object.get("id"));
        }
        this.setUserNames(names);
        this.setUserIDMapper(nameIdMapper);

    }

    private  JSONResponse getDhisRecipients(String userType)
    {
        JSONResponse jsonResponse = new JSONResponse();

        if(requestType == HttpRequestType.GET)
        {

            try {

                request = HttpRequest.get(dhisRecipientUrl+userType).contentType("application/json").basic("system","System123");
                if (request.created() || request.ok()) {
                    String response = request.body();

                    Gson gson = new Gson();

                    DhisRecipients dhisRecipients = gson.fromJson(response,DhisRecipients.class);
                    DataPopulator dataPopulator = new DataPopulator();
                    if(userType.equals("users")) {
                        jsonResponse.setResponse(dhisRecipients.getUsers().toString());
                        processResult(dhisRecipients.getUsers());

                    }
                    if(userType.equals("userGroups")) {
                        jsonResponse.setResponse(dhisRecipients.getUserGroups().toString());
                        processResult(dhisRecipients.getUserGroups());

                    }
                    if(userType.equals("organisationUnits")) {
                        jsonResponse.setResponse(dhisRecipients.getOrganizationalUnits().toString());
                        processResult(dhisRecipients.getOrganizationalUnits());

                    }

                    dataPopulator.setUserNames(this.getUserNames());
                    dataPopulator.setUserIDMapper(this.getUserIDMapper());
                    jsonResponse.setDataPopulator(dataPopulator);
                    jsonResponse.setStatus(200);

                    return  jsonResponse;
                }
            }catch (Exception e)
            {

                jsonResponse.setStatus(400);
                jsonResponse.setResponse(e.getMessage());
                return jsonResponse;
            }

        }
            jsonResponse.setResponse("Nothing Happend ");
            return jsonResponse;

    }
    @Override
    protected JSONResponse doInBackground(String... urls) {

        if(programTaskType == ProgramTaskType.POSTMESSAGE)
        {
                return postMessageToServer(urls[0]);
        }
        if (programTaskType == ProgramTaskType.GETRECIPIENT)
        {
            return getDhisRecipients(urls[0]);
        }

        if (programTaskType == ProgramTaskType.LOGIN)
        {

        }else
        {
            return  null;
        }

        return null;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("D", "Pre");
    }

    @Override
    protected void onPostExecute(JSONResponse result) {
        Log.d("D", "post  " );


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


    public ProgramTaskType getProgramTaskType() {
        return programTaskType;
    }

    public void setProgramTaskType(ProgramTaskType programTaskType) {
        this.programTaskType = programTaskType;
    }


    public Map<String, String> getUserIDMapper() {
        return userIDMapper;
    }

    public void setUserIDMapper(Map<String, String> userIDMapper) {
        this.userIDMapper = userIDMapper;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }


    public HttpRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(HttpRequestType requestType) {
        this.requestType = requestType;
    }


    public List<Map<String, String>> getUsers() {
        return users;
    }

    public void setUsers(List<Map<String, String>> users) {
        this.users = users;
    }


    public  HttpClientTask()
    {}

}