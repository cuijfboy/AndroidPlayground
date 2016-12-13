package name.ilab.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by cuijfboy on 2016/10/25.
 */
@Aspect
public class AspectInjectAspect {

    private static final String POINTCUT_METHOD =
            "execution(@name.ilab.base.AspectInject * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@name.ilab.base.AspectInject *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void annotatedMethod() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void annotatedConstructor() {
    }

    @Around("annotatedMethod() || annotatedConstructor()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        System.out.println("AspectInjectAspect className = " + className);
        System.out.println("AspectInjectAspect methodName = " + methodName);

        System.out.println("joinPoint.getTarget() = " + joinPoint.getTarget());
        System.out.println("joinPoint.getArgs() = " + joinPoint.getArgs()[0]);

        return joinPoint.proceed();
//        return null;
//        return "dummy response";
    }
}
