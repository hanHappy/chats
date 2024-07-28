package chats.aop;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Aspect
@Component
public class ControllerLoggingAspect {

    /**
     * {@code @Around} <br/> Spring AOP에서 제공하는 Advice 타입의 하나. <br/> 메서드의 실행 전/중/후에 로직을 수행할 수 있게 한다.
     * <br/> {@code @Around}는 가장 강력한 Advice 타입으로, 메서드 실행을 완전히 제어할 수 있게 한다. <br/><br/>
     * {@code ProceddingJoinPoint} <br/> Advice된 메서드의 실행을 제어하는 기능을 제공한다. <br/> - proceed(): 원본 메서드를
     * 실행한다. {@code @Around} Advice에서는 이 메서드를 반드시 호출해야 한다. <br/> - getSignature(): Advice되는 메서드의
     * 시그니처 정보를 제공한다. <br/> - getArgs(): 메서드에 전달된 인자들의 배열을 반환한다. <br/> - getTarget(): 대상 객체를 반환한다.
     * <br/> - getThis(): 현재 AOP 프록시 객체를 반환한다.
     */
    @Around("execution(* chats..*Controller.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 호출 메서드
        String methodName = joinPoint.getSignature().getName();
        log.info("[{}] ▶▶▶ API Call", methodName);

        // 파라미터 로깅
        logParameters(joinPoint);

        // 메서드 실행
        Object result = joinPoint.proceed();

        // 결과 로깅
        log.info("[{}] ◀◀◀ Result : {}", methodName, result);

        long executionTime = System.currentTimeMillis() - start;
        log.info("[{}] Executed in {} ms", methodName, executionTime);

        return result;
    }

    private void logParameters(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        Object[] args = joinPoint.getArgs();

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> pathVariables = new HashMap<>();
        Object requestBody = null;

        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            Object arg = args[i];

            if (param.isAnnotationPresent(RequestParam.class)) {
                RequestParam requestParam = param.getAnnotation(RequestParam.class);
                String paramName =
                    requestParam.value().isEmpty() ? param.getName() : requestParam.value();
                params.put(paramName, arg);
            } else if (param.isAnnotationPresent(PathVariable.class)) {
                PathVariable pathVariable = param.getAnnotation(PathVariable.class);
                String paramName =
                    pathVariable.value().isEmpty() ? param.getName() : pathVariable.value();
                pathVariables.put(paramName, arg);
            } else if (param.isAnnotationPresent(RequestBody.class)) {
                requestBody = arg;
            }
        }

        String methodName = signature.getName();
        if (!params.isEmpty()) {
            log.info("[{}] @RequestParam : {}", methodName, params);
        }
        if (!pathVariables.isEmpty()) {
            log.info("[{}] @PathVariable : {}", methodName, pathVariables);
        }
        if (requestBody != null) {
            log.info("[{}] @RequestBody : {}", methodName, requestBody);
        }
    }

}
