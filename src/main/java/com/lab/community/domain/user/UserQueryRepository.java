package com.lab.community.domain.user;

import com.lab.community.domain.board.QBoard;
import com.lab.community.domain.like.QLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserQueryRepository {

    private final QBoard board = QBoard.board;
    private final QLike like = QLike.like;
    private final QUser user = QUser.user;

    private final JPAQueryFactory jpaQueryFactory;

    public List<User> findAllUsers(){
        return jpaQueryFactory.selectFrom(user)
                .join(user.boards, board).fetchJoin()
                .join(user.likes, like).fetchJoin()
                .fetchAll().fetch();
    }

}
