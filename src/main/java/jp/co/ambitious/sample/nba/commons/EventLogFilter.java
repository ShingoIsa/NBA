package jp.co.ambitious.sample.nba.commons;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class EventLogFilter {
    
    @Before("execution(* jp.co.ambitious.sample.nba..*Controller.*(..))")
    public void beforeLog(JoinPoint joinPoint) {

        // Controllerのメソッドの呼び出しに前処理として実行される
        log.info("START " + joinPoint.toShortString());
    }

    @After("execution(* jp.co.ambitious.sample.nba..*Controller.*(..))")
    public void afterLog(JoinPoint joinPoint) {

        // Controllerのメソッドの呼び出しに後処理として実行される
        log.info("END " + joinPoint.toLongString());
    }
}
