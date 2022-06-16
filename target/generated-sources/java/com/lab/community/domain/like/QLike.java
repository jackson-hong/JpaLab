package com.lab.community.domain.like;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLike is a Querydsl query type for Like
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLike extends EntityPathBase<Like> {

    private static final long serialVersionUID = 1653773687L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLike like = new QLike("like1");

    public final com.lab.community.domain.QBaseEntity _super = new com.lab.community.domain.QBaseEntity(this);

    public final com.lab.community.domain.board.QBoard board;

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDtm = _super.modDtm;

    //inherited
    public final StringPath modId = _super.modId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDtm = _super.regDtm;

    //inherited
    public final StringPath regId = _super.regId;

    public final com.lab.community.domain.user.QUser user;

    public QLike(String variable) {
        this(Like.class, forVariable(variable), INITS);
    }

    public QLike(Path<? extends Like> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLike(PathMetadata metadata, PathInits inits) {
        this(Like.class, metadata, inits);
    }

    public QLike(Class<? extends Like> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.lab.community.domain.board.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.lab.community.domain.user.QUser(forProperty("user")) : null;
    }

}

