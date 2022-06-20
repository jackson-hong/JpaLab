package com.lab.community.common.aspect;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.lab.community.common.annotation.QueryCounter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Slf4j
@Component
@Profile(value = {"local","dev"})
public class QueryCounterAspect {

    private ListAppender<ILoggingEvent> listAppender;

    @Around("@annotation(com.lab.community.common.annotation.QueryCounter)")
    public Object countQuery(ProceedingJoinPoint point) throws Throwable {

        listAppender = new ListAppender<>();
        Logger logger = (Logger) LoggerFactory.getLogger("org.hibernate.SQL");
        logger.setLevel(Level.DEBUG);
        logger.addAppender(listAppender);
        listAppender.start();

        log.info("QueryCounter Started at : {}", point.getSignature().toShortString());

        try{
            return point.proceed();
        } finally {
            List<ILoggingEvent> testLogs = listAppender.list;
            log.info("Executed query count : {}", testLogs.size());
        }
    }
}
