package name.ilab.playground.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes("name.ilab.playground.annotation.Playground")
public class AnnotationProcessor extends AbstractProcessor {

    private final String TAG = AnnotationProcessor.class.getSimpleName();

    private Messager messager;

    private void log(Kind kind, String message) {
        messager.printMessage(kind, String.format("%s: %s", TAG, message));
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        log(Kind.NOTE, "initialized!");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.size() == 0) {
            return processFinalRound(roundEnv);
        } else {
            return processRound(annotations, roundEnv);
        }
    }

    private boolean processRound(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean result = false;
        result |= processPlayground(annotations, roundEnv);
        return result;
    }

    private boolean processPlayground(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(Playground.class)
                .stream()
                .forEach(element -> {
                    Playground playground = element.getAnnotation(Playground.class);
                    String value = playground.value();
                    log(Kind.NOTE, "Playground: value = " + value);
                });

        return true;
    }

    private boolean processFinalRound(RoundEnvironment roundEnv) {
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

}
