package com.lab.community.domain.board;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lab.community.config.jpa.QueryDslConfig;
import com.lab.community.domain.user.UserQueryRepository;
import com.lab.community.domain.user.UserRepository;
import com.lab.community.utils.MemoryAppender;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Slf4j
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_batch_fetch_size=1000")
@Import({BoardQueryRepository.class, QueryDslConfig.class, UserQueryRepository.class})
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardQueryRepository boardQueryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserQueryRepository userQueryRepository;

    private MemoryAppender memoryAppender;

    private final String LOGGER_NAME = "ropoTest";

    @BeforeEach
    public void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }


    @Test
    @DisplayName("Entity Graph Test")
    void testEntityGraph() throws JsonProcessingException {

        // GIVEN
        boardRepository.findAll();

        //WHEN & THEN
        System.out.println();
    }

    @Test
    @DisplayName("Fetch Join Test")
    void testFetchJoin(){
        List<Board> boardList = boardQueryRepository.findAllBoardsWithLikesAndUsers();
    }


    // MultipleBagFetchException
    @Test
    @DisplayName("Double Fetch Join Test")
    void testDoubleFetchJoin(){
        assertThrows(IllegalArgumentException.class, () ->  userQueryRepository.findAllUsers());
    }

    // MultipleBagFetchException
    @Test
    @DisplayName("Graph Entity Join Test")
    void testGraphEntityJoinTest(){
        assertThrows(IllegalArgumentException.class, () ->  userRepository.findAll());
    }

}