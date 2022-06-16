package com.lab.community.domain.board;

import com.lab.community.config.jpa.QueryDslConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.ObjectUtils;

import java.util.List;

@DataJpaTest
@Slf4j
@Import({BoardQueryRepository.class, QueryDslConfig.class})
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardQueryRepository boardQueryRepository;

    @Test
    @DisplayName("Entity Graph Test")
    void testEntityGraph(){

        List<Board> boardList = boardRepository.findAll();

//        boardList.forEach(board -> {
//            System.out.println(board.getBoardId());
//            System.out.println(board.getContent());
//            System.out.println(board.getTitle());
//            board.getLikes().forEach(like -> {
//                System.out.println(like.getLikeId());
//                System.out.println(like.getUser().getUserId());
//            });
//            System.out.println(board.getUser().getUserId());
//            System.out.println(board.getUser().getAccountId());
//            System.out.println(board.getUser().getNickName());
//        });

    }

    @Test
    @DisplayName("Fetch Join Test")
    void testFetchJoin(){
        List<Board> boardList = boardQueryRepository.findAllBoardsWithLikesAndUsers();

//        boardList.forEach(board -> {
//            System.out.println(board.getBoardId());
//            System.out.println(board.getContent());
//            System.out.println(board.getTitle());
//            board.getLikes().forEach(like -> {
//                System.out.println(like.getLikeId());
//                System.out.println(like.getUser().getUserId());
//            });
//            System.out.println(board.getUser().getUserId());
//            System.out.println(board.getUser().getAccountId());
//            System.out.println(board.getUser().getNickName());
//        });
    }

}