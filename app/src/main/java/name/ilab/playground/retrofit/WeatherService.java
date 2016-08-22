package name.ilab.playground.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cuijfboy on 16/7/27.
 */
public interface WeatherService {

    @GET("/data/sk/{cityId}.html")
    Call<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);
}
