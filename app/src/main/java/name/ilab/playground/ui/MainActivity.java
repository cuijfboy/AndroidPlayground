package name.ilab.playground.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import name.ilab.playground.R;
import name.ilab.playground.retrofit.WeatherInfo;
import name.ilab.playground.retrofit.WeatherRxService;
import name.ilab.playground.retrofit.WeatherService;
import name.ilab.playground.ui.example.DrawerActivity;
import name.ilab.playground.ui.example.FullscreenActivity;
import name.ilab.playground.ui.example.ItemListActivity;
import name.ilab.playground.ui.example.LoginActivity;
import name.ilab.playground.ui.example.ScrollingActivity;
import name.ilab.playground.ui.example.SettingsActivity;
import name.ilab.playground.ui.example.TabActivity;
import name.ilab.util.aop.AspectInject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static name.ilab.playground.util.Log.logger;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    @BindView(R.id.button_getWeatherInfo_rx)
    Button buttonWeatherInfoRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        initRetrofit();

        RxView.clicks(buttonWeatherInfoRx).subscribe(this::getWeatherInfoRx);

    }

    @OnClick(R.id.button_scrollingActivity)
    void goToScrollingActivity() {
        startActivity(new Intent(this, ScrollingActivity.class));
    }

    @OnClick(R.id.button_drawerActivity)
    void goToDrawerActivity() {
        startActivity(new Intent(this, DrawerActivity.class));
    }

    @OnClick(R.id.button_fullScreenActivity)
    void goToFullscreenActivity() {
        startActivity(new Intent(this, FullscreenActivity.class));
    }

    @OnClick(R.id.button_loginActivity)
    void goToLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.button_settingsActivity)
    void goToSettingsActivity() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @OnClick(R.id.button_tabActivity)
    void goToTabActivity() {
        startActivity(new Intent(this, TabActivity.class));
    }

    @OnClick(R.id.button_itemActivity)
    void goToItemActivity() {
        startActivity(new Intent(this, ItemListActivity.class));
    }

    @OnClick(R.id.button_dataBindingActivity)
    void goToDataBindingActivity() {
        startActivity(new Intent(this, DataBindingActivity.class));
    }

    @OnClick(R.id.button_recyclerViewActivity)
    void goToRecyclerViewActivity() {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    @OnClick(R.id.button_dataBindingRecyclerViewActivity)
    void goToDataBindingRecyclerViewActivity() {
        startActivity(new Intent(this, DataBindingRecyclerViewActivity.class));
    }

//    @OnClick(R.id.button_j2v8TestActivity)
//    void goToJ2v8TestActivity() {
//        startActivity(new Intent(this, J2v8TestActivity.class));
//    }

    @OnClick(R.id.fab)
    void onClickFab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        String result = testAspectInject("testAspectInject args");
        System.out.println("onClickFab result = " + result);
//        new Temp().temp();
    }

    @AspectInject
    private String testAspectInject(String string) {
        System.out.println("************* MainActivity.testAspectInject *************");
        return "testAspectInject";
    }

    private void initRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            Request request = chain.request();

                            System.out.println("request = " + request);
                            System.out.println("request.body() = " + request.body().toString());
//                            System.out.println("request.body() = " + request.body());
                            System.out.println("request.headers() = " + request.headers());
                            System.out.println("request.header(\"sign\") = " + request.header("sign"));
                            System.out.println("request.header(\"AAA\") = " + request.header("AAA"));

                            String bodyString = getRequestBodyAsString(request);

//                            RequestBody body = request.body();
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            BufferedSink bufferedSink = Okio.buffer(Okio.sink(baos));
//                            body.writeTo(bufferedSink);
//                            body.writeTo(new MockBufferSink());
//                            bufferedSink.flush();
//                            System.out.println("baos = " + baos.toString());

                            System.out.println("bodyString = " + bodyString);

                            request = request.newBuilder()
                                    .post(RequestBody.create(
                                            MediaType.parse("application/json; charset=UTF-8"), "hahahahahaha"))
                                    .build();

                            String newBodyString = getRequestBodyAsString(request);
                            System.out.println("newBodyString = " + newBodyString);

                            return chain.proceed(request);
                        })
                        .addInterceptor(loggingInterceptor).build())
                .baseUrl("http://www.weather.com.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    private String getRequestBodyAsString(Request request) throws IOException {
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return null;
        }

        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return buffer.readUtf8();
    }

    @OnClick(R.id.button_getWeatherInfo)
    void getWeatherInfo() {
        WeatherService weatherService = retrofit.create(WeatherService.class);
        WeatherInfo weatherInfo = new WeatherInfo();
        WeatherInfo.Info info = new WeatherInfo.Info();
        weatherInfo.weatherinfo = info;
        info.city = "cityVal";
        info.cityid = "cityIdVal";

        Call<WeatherInfo> weatherInfoCall = weatherService.getWeatherInfo2(weatherInfo);
        weatherInfoCall.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                logger().info("MainActivity.onResponse() called with : " +
                        "call = [{}], response = [{}]", call, response);

                logger().info("MainActivity.onResponse() : response.body() = {}", response.body());
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                logger().info("MainActivity.onFailure() called with : " +
                        "call = [{}], t = [{}]", call, t);
            }
        });
    }

    //    @OnClick(R.id.button_getWeatherInfo_rx)
    void getWeatherInfoRx(Void v) {
        WeatherRxService weatherRxService = retrofit.create(WeatherRxService.class);
        weatherRxService.getWeatherInfo("101120201")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherInfo -> logger().info("getWeatherInfoRx() : weatherInfo = {}", weatherInfo),
                        throwable -> logger().error("ERROR! {}", throwable),
                        () -> logger().info("Complete!"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void test() {
//        Observable.just(1)
//                .repeat()
//                .groupBy()
    }

}
