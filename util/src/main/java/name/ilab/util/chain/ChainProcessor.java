package name.ilab.util.chain;

/**
 * Created by cuijfboy on 2016/10/21.
 */

public interface ChainProcessor<T, R> {

    R process(Chain<T, R> chain) throws Exception;

    interface Chain<T, R> {
        T getTarget();

        R proceed(T data) throws Exception;
    }

}
