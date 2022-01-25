//package com.song.blog.Aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//@Aspect
//@Component
//@Slf4j
//public class PerformanceAspect {
//
//    // 특정 어노테이션 대상 지정
//    @Pointcut("@annotation(com.song.blog.annotation.RunningTime)")
//    private void enableRunningTime() {}
//
//    // 기본 패키지의 모든 메소드드
//    @Pointcut("execution(* com.song.blog..*.*(..))")
//    private  void cut() {};
//
//    // 실행 시점을 설정 (두 조건을 모두 만족하는 대상을 전후로 부가 기능을 삽입)
//    @Around("cut() && enableRunningTime()") // 동시조건     // 컷에서 지정된 기본패키지 하위의 메소드 이면서, RunningTime을 가지고 있는 애들을 실행!!!!!!!!
//    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable { // 그 대상을 실행까지 할 수 있는 기능?
//        // 메소드 수행전, 측정 시작
//        StopWatch stopWatch= new StopWatch(); // 스프링부트에서 제공하는 스탑워치
//        stopWatch.start();
//
//        // 메소드 수행
//        Object returningObj = joinPoint.proceed();
//
//        // 메소드 수행 후, 측정 종료 및 로깅
//        stopWatch.stop();
//        String methodName = joinPoint.getSignature().getName();
//
//        // 메소드 수행 후, 측정 종료 및 로깅
//        stopWatch.stop();
//        log.info("{}의 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
//    }
//}
