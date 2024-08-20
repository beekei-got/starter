package com.starter.core.domain.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlog is a Querydsl query type for Blog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlog extends EntityPathBase<Blog> {

    private static final long serialVersionUID = 1265733767L;

    public static final QBlog blog = new QBlog("blog");

    public final com.starter.core.domain.QBaseEntity _super = new com.starter.core.domain.QBaseEntity(this);

    public final ListPath<BlogConnectCategory, QBlogConnectCategory> connectCategoryList = this.<BlogConnectCategory, QBlogConnectCategory>createList("connectCategoryList", BlogConnectCategory.class, QBlogConnectCategory.class, PathInits.DIRECT2);

    public final ListPath<BlogConnectUser, QBlogConnectUser> connectedUserList = this.<BlogConnectUser, QBlogConnectUser>createList("connectedUserList", BlogConnectUser.class, QBlogConnectUser.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final EnumPath<BlogStatus> status = createEnum("status", BlogStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDatetime = _super.updatedDatetime;

    public QBlog(String variable) {
        super(Blog.class, forVariable(variable));
    }

    public QBlog(Path<? extends Blog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlog(PathMetadata metadata) {
        super(Blog.class, metadata);
    }

}

