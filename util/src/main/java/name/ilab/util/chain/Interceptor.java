package name.ilab.util.chain;

/**
 * Created by cuijfboy on 2016/12/9.
 */
public interface Interceptor<T, R> {

    R intercept(Chain<T, R> chain) throws Exception;

    interface Chain<T, R> {

        T getTarget();

        R proceed(T target) throws Exception;
    }
}
