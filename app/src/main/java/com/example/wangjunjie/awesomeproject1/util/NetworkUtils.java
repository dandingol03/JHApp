package com.example.wangjunjie.awesomeproject1.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static Retrofit mRetrofit=null;
    private static final String baseUrl="http://192.168.0.198:8080/sdrpoms/";
    private static String mCookie;

    public static void init(){


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response  response = chain.proceed(chain.request());
                        //存入Session
                        if (response.header("Cookie") != null) {
                            mCookie=response.header("Cookie");
                        }
                        return response;
                    }

                })
                .build();

        mRetrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static <T> T createService( Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public static String getCookie(){
        return mCookie;
    }
}
