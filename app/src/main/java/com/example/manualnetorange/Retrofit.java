package com.example.manualnetorange;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



class InsertRequest {
    private String name;

    public InsertRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class InsertResponse {
    private boolean success;
    private String message;
    private String error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}


interface ApiService {

    @POST("/api/insert/")
    Call<InsertResponse> insertData(@Body InsertRequest request);

    @POST("/api/verify_data/")
    Call<VerifyResponse> verifyData(@Body VerifyRequest request);
}

class VerifyRequest {
    private String username;
    private String password;

    public VerifyRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class VerifyResponse {
    private boolean success;
    private String message;
    private String error;

    public VerifyResponse(boolean success, String message, String error) {
        this.success = success;
        this.message = message;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}



