package com.codecool.SpringAPI.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LogManager.getLogger();

    @Pointcut("execution(* com.codecool.SpringAPI.controller.DirectorController.*(..))" +
    "|| execution(* com.codecool.SpringAPI.controller.ActorController.*(..))" +
    "|| execution(* com.codecool.SpringAPI.controller.MovieController.*(..))")
    public void springModelControllerPointcut(){}

    @Pointcut("execution(* com.codecool.SpringAPI.service.ActorService.*(..))")
    public void springErrorControllerPointcut(){}

    @Before("springModelControllerPointcut()")
    public void info(JoinPoint point) {
        log.info("Called {}.{}()", point.getSignature().getDeclaringTypeName(),
                point.getSignature().getName());;
    }

    @After("springErrorControllerPointcut()")
    public void error(JoinPoint point) {
        log.error("Exception in {}.{}()", point.getSignature().getDeclaringTypeName(),
                point.getSignature().getName());
    }
}
