package com.example.iqra.lmis_light;




public class JSONResponse {
    public int status;
    public String response = "";

    public JSONResponse(){}

    public JSONResponse(int status, String response){
        this.status = status;
        this.response = response;
    }
}