package com.lab.community.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -2137778697L;

    public static final QUser user = new QUser("user");

    public final com.lab.community.domain.QBaseEntity _super = new com.lab.community.domain.QBaseEntity(this);

    public final StringPath accountId = createString("accountId");

    public final EnumPath<com.lab.community.common.type.user.AccountType> accountType = createEnum("accountType", com.lab.community.common.type.user.AccountType.class);

    public final ListPath<com.lab.community.domain.board.Board, com.lab.community.domain.board.QBoard> boardList = this.<com.lab.community.domain.board.Board, com.lab.community.domain.board.QBoard>createList("boardList", com.lab.community.domain.board.Board.class, com.lab.community.domain.board.QBoard.class, PathInits.DIRECT2);

    public final ListPath<com.lab.community.domain.like.Like, com.lab.community.domain.like.QLike> likeList = this.<com.lab.community.domain.like.Like, com.lab.community.domain.like.QLike>createList("likeList", com.lab.community.domain.like.Like.class, com.lab.community.domain.like.QLike.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDtm = _super.modDtm;

    //inherited
    public final StringPath modId = _super.modId;

    public final StringPath nickName = createString("nickName");

    public final EnumPath<com.lab.community.common.type.YnType> quit = createEnum("quit", com.lab.community.common.type.YnType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDtm = _super.regDtm;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

