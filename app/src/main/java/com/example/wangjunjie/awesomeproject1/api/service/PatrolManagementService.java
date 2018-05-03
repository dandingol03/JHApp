package com.example.wangjunjie.awesomeproject1.api.service;



import com.example.wangjunjie.awesomeproject1.api.model.Paging;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface PatrolManagementService {

    @Headers({"Content-Type: application/json"})
    @POST("http://192.168.0.198:8080/sdrpoms/patrol/patrolTeamInfoQueryList")
    Observable<ResponseBody> patrolManagementTaskQueryList(@Body RequestBody body, @Header("Cookie") String coikie);

}
