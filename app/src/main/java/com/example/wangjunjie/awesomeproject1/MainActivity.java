package com.example.wangjunjie.awesomeproject1;


import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wangjunjie.awesomeproject1.adapter.TabFragmentPagerAdapter;
import com.example.wangjunjie.awesomeproject1.api.model.Paging;
import com.example.wangjunjie.awesomeproject1.api.service.PatrolManagementService;
import com.example.wangjunjie.awesomeproject1.ui.fragment.ContactFragment;
import com.example.wangjunjie.awesomeproject1.ui.fragment.HomeFragment;
import com.example.wangjunjie.awesomeproject1.ui.fragment.OaFragment;
import com.example.wangjunjie.awesomeproject1.ui.fragment.ProfileFragment;
import com.example.wangjunjie.awesomeproject1.api.service.LoginService;
import com.example.wangjunjie.awesomeproject1.util.NetworkUtils;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  implements HomeFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,OaFragment.OnFragmentInteractionListener,ProfileFragment.OnFragmentInteractionListener{

    private final String baseUrl="http://192.168.0.198:8080/sdrpoms/";
    private final String nodejsUrl="http://211.87.225.248:3006/api/";
    private LoginService mLoginService;
    private PatrolManagementService mPatrolManagementService;
    private TabLayout tabLayout;
    private List<Fragment> fragments=null;
    private TabFragmentPagerAdapter mAdapter=null;
    private ViewPager mViewPager;
    private NavigationTabBar mNavigationTabBar;

    private void getPatrolTeamInfo(PatrolManagementService mPatrolManagementService)
    {
        Paging paging=new Paging();
        Gson mGson = new Gson();
        String string = mGson.toJson(paging);
        RequestBody body= RequestBody.create(MediaType.parse("application/json; charset=UTF-8"),string);
        mPatrolManagementService.patrolManagementTaskQueryList(body,NetworkUtils.getCookie())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.toString());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try{

                            //cookie=  response.raw().header("Cookie");
                            String content=new String(responseBody.bytes());
                            Log.d("===",content);

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        Log.d("===","success");
                    }
                });



    }


    private void loginUseRetrofit(){


        mLoginService.login("admin","111111")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.toString());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try{

                            //cookie=  response.raw().header("Cookie");
                            String content=new String(responseBody.bytes());
                            Log.d("===",content);

                            getPatrolTeamInfo(mPatrolManagementService);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        Log.d("===","success");
                    }
                });

    }

    private void loginInURL()
    {
        try{
            URL url=new URL(baseUrl+"j_spring_security_check");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            String cookieval = connection.getHeaderField("set-cookie");
            StringBuffer params = new StringBuffer();
            // 表单参数与get形式一样
            params.append("j_username").append("=").append("admin").append("&")
                    .append("j_password").append("=").append("111111");
            byte[] bypes = params.toString().getBytes();
            connection.getOutputStream().write(bypes);// 输入参数
            InputStream inStream=connection.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len = inStream.read(buffer)) !=-1 ){
                outStream.write(buffer, 0, len);
            }
            Log.d("/*******/",outStream.toString());
            outStream.close();
            inStream.close();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLoginService= NetworkUtils.createService(LoginService.class);
        mPatrolManagementService=NetworkUtils.createService(PatrolManagementService.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        initData();
        mAdapter=new TabFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        initUi();


        //发送登陆请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                loginInURL();
//            }
//        }).start();
        loginUseRetrofit();
    }

    private void initData()
    {
        fragments=new ArrayList<>();
        HomeFragment home=HomeFragment.newInstance("","");
        OaFragment oa=OaFragment.newInstance("","");
        ContactFragment contact=ContactFragment.newInstance("","");
        ProfileFragment profile=ProfileFragment.newInstance("","");
        fragments.add(home);
        fragments.add(oa);
        fragments.add(contact);
        fragments.add(profile);
    }

    private void initUi()
    {
        mNavigationTabBar= (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final String[] colors = getResources().getStringArray(R.array.tabs);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[2]))
                        .title("Home")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .title("Oa")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[0]))
                        .title("Diploma")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
                        .title("Flag")
                        .build()
        );

        mNavigationTabBar.setModels(models);
        mNavigationTabBar.setViewPager(mViewPager, 1);
        mNavigationTabBar.setBehaviorEnabled(true);
        mNavigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        mNavigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });



    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(MainActivity.this,"this is："+uri,Toast.LENGTH_SHORT).show();
    }


}
