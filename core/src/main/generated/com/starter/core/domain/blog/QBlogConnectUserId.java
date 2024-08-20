package com.starter.core.domain.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogConnectUserId is a Querydsl query type for BlogConnectUserId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBlogConnectUserId extends BeanPath<BlogConnectUserId> {

    private static final long serialVersionUID = 1621733001L;

    public static final QBlogConnectUserId blogConnectUserId = new QBlogConnectUserId("blogConnectUserId");

    public final NumberPath<Long> blogId = createNumber("blogId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBlogConnectUserId(String variable) {
        super(BlogConnectUserId.class, forVariable(variable));
    }

    public QBlogConnectUserId(Path<? extends BlogConnectUserId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogConnectUserId(PathMetadata metadata) {
        super(BlogConnectUserId.class, metadata);
    }

}

