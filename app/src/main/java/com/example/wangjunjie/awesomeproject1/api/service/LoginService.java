package com.example.wangjunjie.awesomeproject1.api.service;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface LoginService {

    @FormUrlEncoded
    @POST("j_spring_security_check")
    Observable<ResponseBody> login(@Field("j_username") String username, @Field("j_password") String password);

    @GET("welcome")
    Call<ResponseBody> oa(@Query("menuName") String menuName, @Query("menuId") String menuId, @Header("Cookie") String coikie);


}
