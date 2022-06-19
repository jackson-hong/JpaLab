package com.lab.community.domain.board;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.lab.community.config.jpa.QueryDslConfig;
import com.lab.community.domain.user.UserQueryRepository;
import com.lab.community.domain.user.UserRepository;
import com.lab.community.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
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
@Import({BoardQueryRepository.class, QueryDslConfig.class, UserQueryRepository.class, BoardService.class})
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardQueryRepository boardQueryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private BoardService boardService;

    private ListAppender listAppender;

//    @BeforeEach
//    public void setup() {
//        Logger logger = (Logger) LoggerFactory.getLogger(BoardService.class);
//        memoryAppender = new ListAppender<>();
//        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
//        logger.setLevel(Level.ALL);
//        logger.addAppender(memoryAppender);
//        memoryAppender.start();
//    }


    @Test
    @DisplayName("Entity Graph Test")
    void testEntityGraph() {

        listAppender = new ListAppender<>();
        Logger logger = (Logger) LoggerFactory.getLogger(BoardService.class);
        logger.addAppender(listAppender);
        listAppender.start();

        // GIVEN
        boardService.findAllBoards();
        List<ILoggingEvent> testLogs = listAppender.list;
        String message = testLogs.get(0).getFormattedMessage();
        Level level = testLogs.get(0).getLevel();


        //WHEN & THEN
        System.out.println(testLogs.get(0));
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