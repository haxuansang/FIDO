package com.example.jake.fido.Retrofit;

import static com.example.jake.fido.Utils.Constants.BASE_URL;

public class ApiUtils {
    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}