package com.example.jake.fido.Instance;

import com.example.jake.fido.Retrofit.ApiUtils;
import com.example.jake.fido.Retrofit.SOService;

public class APIFido {
    private static APIFido apiFido;
    private SOService soService;
    public static APIFido getInstance() {
        if (apiFido==null) {
            apiFido = new APIFido();
        }
        return apiFido;
    }
    public void setUpAPI(){
        soService = ApiUtils.getSOService();
    }

    public SOService getSoService() {
        return soService;
    }

    public void setSoService(SOService soService) {
        this.soService = soService;
    }
}
