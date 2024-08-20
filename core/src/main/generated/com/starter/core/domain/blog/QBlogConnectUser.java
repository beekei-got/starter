package com.starter.core.domain.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlogConnectUser is a Querydsl query type for BlogConnectUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlogConnectUser extends EntityPathBase<BlogConnectUser> {

    private static final long serialVersionUID = -1714511666L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlogConnectUser blogConnectUser = new QBlogConnectUser("blogConnectUser");

    public final com.starter.core.domain.QBaseEntity _super = new com.starter.core.domain.QBaseEntity(this);

    public final QBlog blog;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final QBlogConnectUserId id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDatetime = _super.updatedDatetime;

    public QBlogConnectUser(String variable) {
        this(BlogConnectUser.class, forVariable(variable), INITS);
    }

    public QBlogConnectUser(Path<? extends BlogConnectUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlogConnectUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlogConnectUser(PathMetadata metadata, PathInits inits) {
        this(BlogConnectUser.class, metadata, inits);
    }

    public QBlogConnectUser(Class<? extends BlogConnectUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blog = inits.isInitialized("blog") ? new QBlog(forProperty("blog")) : null;
        this.id = inits.isInitialized("id") ? new QBlogConnectUserId(forProperty("id")) : null;
    }

}

