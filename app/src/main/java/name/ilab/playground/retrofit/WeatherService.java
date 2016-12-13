package name.ilab.playground.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by cuijfboy on 16/7/27.
 */
public interface WeatherService {

    @GET("/getTarget/sk/{cityId}.html")
    Call<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);

    @Headers("sign: true")
    @POST("/getTarget/sk/{cityId}.html")
    Call<WeatherInfo> getWeatherInfo2(@Body WeatherInfo info);
}
