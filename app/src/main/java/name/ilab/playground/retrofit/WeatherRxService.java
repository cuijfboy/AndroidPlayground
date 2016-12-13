package name.ilab.playground.retrofit;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by cuijfboy on 16/7/27.
 */
public interface WeatherRxService {

    @GET("getTarget/sk/{cityId}.html")
    Observable<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);

}
