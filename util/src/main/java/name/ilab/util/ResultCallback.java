package name.ilab.util;

/**
 * Created by cuijfboy on 2016/10/21.
 */

public interface ResultCallback<T> {

    void onResult(boolean isSuccess, String msg, T data);

}
