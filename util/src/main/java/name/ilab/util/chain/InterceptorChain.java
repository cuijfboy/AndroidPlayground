package name.ilab.util.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuijfboy on 2016/12/9.
 */
public class InterceptorChain<T, R> implements Interceptor.Chain<T, R> {

    private final List<Interceptor<T, R>> interceptors;
    private final T target;
    private final int index;
    private int calls;

    public InterceptorChain(T target, List<Interceptor<T, R>> interceptorList, int index) {
        this.interceptors = interceptorList;
        this.target = target;
        this.index = index;
    }

    @Override
    public T getTarget() {
        return target;
    }

    @Override
    public R proceed(T target) throws Exception {
        if (index >= interceptors.size()) {
            throw new AssertionError();
        }

        calls++;

        Interceptor<T, R> interceptor = interceptors.get(index);
        InterceptorChain<T, R> next = new InterceptorChain<>(target, interceptors, index + 1);
        R result = interceptor.intercept(next);

        if (index < interceptors.size() - 1 && calls != 1) {
            throw new IllegalStateException(String.format(
                    "Interceptor %s must call proceed() exactly once", interceptor));
        }

        return result;
    }

    public static <T, R> R start(T target, Interceptor<T, R> finalInterceptor, List<Interceptor<T, R>> interceptors)
            throws Exception {
        List<Interceptor<T, R>> allInterceptors = new ArrayList<>(interceptors);
        allInterceptors.add(finalInterceptor);
        InterceptorChain<T, R> chain = new InterceptorChain<>(target, allInterceptors, 0);
        return chain.proceed(target);
    }

}
