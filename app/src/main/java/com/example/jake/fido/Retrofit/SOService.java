package com.example.jake.fido.Retrofit;


import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctors;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SOService {
    /*@GET("/users/{path}/")
      Call<List<RequestUser>> getCurrentUser(@Path("path") String path);
      @POST("/users/")
      Call<List<RequestUser>> registerAccount(@Body CurrentUser currentUser);
      @GET("/users/post/{path}/")
      Call<List<ExplorePost>> getExplorePosts(@Path("path") String path);
  */
    @GET("doctors-pagination")
    Call<Doctors> getDoctors(@Query("page") String path);

    @POST("search")
    Call<Doctors> searchDoctors(@Body RequestBody body);
}
