package com.starter.core.domain.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlogConnectCategory is a Querydsl query type for BlogConnectCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlogConnectCategory extends EntityPathBase<BlogConnectCategory> {

    private static final long serialVersionUID = -270387071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlogConnectCategory blogConnectCategory = new QBlogConnectCategory("blogConnectCategory");

    public final com.starter.core.domain.QBaseEntity _super = new com.starter.core.domain.QBaseEntity(this);

    public final QBlog blog;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final QBlogConnectCategoryId id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDatetime = _super.updatedDatetime;

    public QBlogConnectCategory(String variable) {
        this(BlogConnectCategory.class, forVariable(variable), INITS);
    }

    public QBlogConnectCategory(Path<? extends BlogConnectCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlogConnectCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlogConnectCategory(PathMetadata metadata, PathInits inits) {
        this(BlogConnectCategory.class, metadata, inits);
    }

    public QBlogConnectCategory(Class<? extends BlogConnectCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blog = inits.isInitialized("blog") ? new QBlog(forProperty("blog")) : null;
        this.id = inits.isInitialized("id") ? new QBlogConnectCategoryId(forProperty("id")) : null;
    }

}

