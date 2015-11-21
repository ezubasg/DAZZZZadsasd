package daz.lmis_light;


/**
 * Created by Zubair<rajazubair.asghar@gmail.com> on 15.11.15.;
 */



public class JSONResponse {
    private int status;
    private String response = "";

    public JSONResponse(){}

    public JSONResponse(int status, String response){
        this.status = status;
        this.response = response;
    }

    public int getStatus()
    {
        return this.status;
    }
    public  String getResponse()
    {
        return this.response;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public void setResponse (String response)
    {
        this.response = response;
    }
}
