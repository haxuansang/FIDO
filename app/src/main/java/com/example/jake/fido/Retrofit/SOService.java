package com.example.jake.fido.Retrofit;


import com.example.jake.fido.Retrofit.ObjectRetrofit.City;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctors;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Review;
import com.example.jake.fido.Retrofit.ReviewObject.ReviewResponse;
import com.example.jake.fido.Retrofit.ReviewObject.ReviewUp;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @POST("doctors/search")
    Call<Doctors> searchDoctors(@Body RequestBody body, @Query("page") String page);

    @POST("login")
    Call<LoginRetrofit> login(@Body RequestBody body);

    @GET("addresses")
    Call<List<City>> getCity();

    @POST("register")
    Call<LoginRetrofit> register(@Body RegisterRetrofit body);

    @Multipart
    @POST("doctors/{path}/certificates")
    Call<ChungChiRetrofit> upChungChi(@Path("path") String path, @Part MultipartBody.Part file, @Part("name") RequestBody name,
                                      @Part("description") RequestBody description);

    //delete chung chi

    @DELETE("doctors/{path}/certificates/{id}")
    Call<ChungChiRetrofit> deleteChungChi(@Path("path") String path,@Path("id") String id);

    @GET("doctors/{path}/certificates")
    Call<List<Data>> getChungChi(@Path("path") String path);

    //update

    @Multipart
    @POST("doctors/{path}")
    Call<LoginRetrofit> updateDoctorFile(@Path("path") String path, @Part MultipartBody.Part file, @Part("name") RequestBody name,@Part("gender") RequestBody gender,@Part("phone_number") RequestBody phone_number,
                                     @Part("id_number") RequestBody id_number,@Part("id_number_date") RequestBody id_number_date,
                                     @Part("id_number_place") RequestBody id_number_place,@Part("address_id") RequestBody address_id,@Part("address_details") RequestBody address_details,
                                     @Part("specialist_id") RequestBody specialist_id,@Part("sub_specialist_id") RequestBody sub_specialist_id,
                                     @Part("title") RequestBody chucvu,@Part("office") RequestBody office,@Part("description") RequestBody description,
                                     @Part("experience") RequestBody expierence,@Part("_method") RequestBody methodput);
    @Multipart
    @POST("doctors/{path}")
    Call<LoginRetrofit> updateDoctor(@Path("path") String path, @Part("name") RequestBody name,@Part("gender") RequestBody gender,@Part("phone_number") RequestBody phone_number,
                                         @Part("id_number") RequestBody id_number,@Part("id_number_date") RequestBody id_number_date,
                                         @Part("id_number_place") RequestBody id_number_place,@Part("address_id") RequestBody address_id,@Part("address_details") RequestBody address_details,
                                         @Part("specialist_id") RequestBody specialist_id,@Part("sub_specialist_id") RequestBody sub_specialist_id,
                                         @Part("title") RequestBody chucvu,@Part("office") RequestBody office,@Part("description") RequestBody description,
                                         @Part("expierence") RequestBody expierence,@Part("_method") RequestBody methodput);

    @Multipart
    @POST("patients/{path}")
    Call<LoginRetrofit> updatePatientsFile(@Path("path") String path, @Part MultipartBody.Part file, @Part("name") RequestBody name,@Part("gender") RequestBody gender,@Part("phone_number") RequestBody phone_number,
                                       @Part("id_number") RequestBody id_number,@Part("id_number_date") RequestBody id_number_date,
                                       @Part("id_number_place") RequestBody id_number_place,@Part("address_id") RequestBody address_id,@Part("_method") RequestBody methodput);

    @Multipart
    @POST("patients/{path}")
    Call<LoginRetrofit> updatePatients(@Path("path") String path, @Part("name") RequestBody name,@Part("gender") RequestBody gender,@Part("phone_number") RequestBody phone_number,
                                     @Part("id_number") RequestBody id_number,@Part("id_number_date") RequestBody id_number_date,
                                     @Part("id_number_place") RequestBody id_number_place,@Part("address_id") RequestBody address_id,@Part("_method") RequestBody methodput);

    @POST("reset-password")
    Call<ResetPassword> resetpassword(@Body RequestBody body);

    @POST("doctors/{path}/ratings")
    Call<ReviewResponse> review(@Path("path") String path, @Body ReviewUp body);
}