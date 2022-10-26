package edu.chnu.library.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author artem
 * @version: 1.0.0
 * @project microJava_01
 * @date 04.10.2022 14:41
 * @class GeneralInterceptorAspect
 */
@Component
@Aspect
public class GeneralInterceptorAspect {

    Logger logger = LogManager.getLogger();

    @Pointcut("execution(* edu.chnu.library.controller..*(..)) ||" +
            "execution(* edu.chnu.library.service..*(..)) ||" +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))))")
    public void loggingPointCut() {
    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        logger.info("Before method invoke::" + joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint) {
        logger.info("After method invoke::" + joinPoint.getSignature());
    }

    @Around("loggingPointCut()")
    public Object executionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object result = pjp.proceed();
        long end = System.nanoTime();
        logger.info("Execution time of " + pjp.getSignature().getName() + " took " + TimeUnit.NANOSECONDS.toMillis(end - start) + "ms");
        return result;
    }

}
