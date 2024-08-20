package com.starter.core.domain.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogConnectCategoryId is a Querydsl query type for BlogConnectCategoryId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBlogConnectCategoryId extends BeanPath<BlogConnectCategoryId> {

    private static final long serialVersionUID = -2143935108L;

    public static final QBlogConnectCategoryId blogConnectCategoryId = new QBlogConnectCategoryId("blogConnectCategoryId");

    public final NumberPath<Long> blogId = createNumber("blogId", Long.class);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public QBlogConnectCategoryId(String variable) {
        super(BlogConnectCategoryId.class, forVariable(variable));
    }

    public QBlogConnectCategoryId(Path<? extends BlogConnectCategoryId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogConnectCategoryId(PathMetadata metadata) {
        super(BlogConnectCategoryId.class, metadata);
    }

}

