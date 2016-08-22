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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import name.ilab.playground.R;
import name.ilab.playground.retrofit.WeatherInfo;
import name.ilab.playground.retrofit.WeatherRxService;
import name.ilab.playground.retrofit.WeatherService;
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
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.weather.com.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @OnClick(R.id.button_getWeatherInfo)
    void getWeatherInfo() {
        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<WeatherInfo> weatherInfoCall = weatherService.getWeatherInfo("101120201");
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
