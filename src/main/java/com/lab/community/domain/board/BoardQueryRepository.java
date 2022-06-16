package com.lab.community.domain.board;

import com.lab.community.domain.like.QLike;
import com.lab.community.domain.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardQueryRepository {

    private final QBoard board = QBoard.board;
    private final QLike like = QLike.like;
    private final QUser user = QUser.user;

    private final JPAQueryFactory jpaQueryFactory;

    public Page<Board> findAllBoards(Pageable pageable) {
        List<Board> result = jpaQueryFactory.selectFrom(board)
                .join(board.user, user).fetchJoin()
                .orderBy(board.regDtm.asc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetchAll().fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    public List<Board> findAllBoardsWithLikesAndUsers(){
        return jpaQueryFactory.selectFrom(board)
                .join(board.likes, like).fetchJoin()
                .join(like.user, user).fetchJoin()
                .fetchAll().fetch();
    }

}