package name.ilab.util.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuijfboy on 2016/10/21.
 */

public class ProcessChain<T, R> implements ChainProcessor.Chain<T, R> {

    private List<ChainProcessor<T, R>> processors;
    private int index;
    private int calls;
    private T target;

    public ProcessChain(T target, List<ChainProcessor<T, R>> processors, int index) {
        this.target = target;
        this.processors = processors;
        this.index = index;
    }

    @Override
    public T getTarget() {
        return target;
    }

    @Override
    public R proceed(T target) throws Exception {
        return callNextProcessor(target);
    }

    protected final R callNextProcessor(T data) throws Exception {
        if (index >= processors.size()) {
            throw new AssertionError();
        }

        calls++;

        ProcessChain<T, R> next = new ProcessChain<>(data, processors, index + 1);
        ChainProcessor<T, R> processor = processors.get(index);
        R result = processor.process(next);

        if (index + 1 < processors.size() && next.calls != 1) {
            throw new IllegalStateException(String.format(
                    "ChainProcessor %s must call proceed() exactly once", processor));
        }

        return result;
    }

    public static <T, R> R process(
            T data, ChainProcessor<T, R> finalProcessor, List<ChainProcessor<T, R>> processors)
            throws Exception {
        List<ChainProcessor<T, R>> allProcessors = new ArrayList<>(processors);
        processors.add(finalProcessor);
        ChainProcessor.Chain<T, R> chain = new ProcessChain<>(data, allProcessors, 0);
        return chain.proceed(data);
    }

}
