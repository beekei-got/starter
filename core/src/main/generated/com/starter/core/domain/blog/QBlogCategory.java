package com.starter.core.domain.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogCategory is a Querydsl query type for BlogCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlogCategory extends EntityPathBase<BlogCategory> {

    private static final long serialVersionUID = -2803035L;

    public static final QBlogCategory blogCategory = new QBlogCategory("blogCategory");

    public final com.starter.core.domain.QBaseEntity _super = new com.starter.core.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDatetime = _super.updatedDatetime;

    public QBlogCategory(String variable) {
        super(BlogCategory.class, forVariable(variable));
    }

    public QBlogCategory(Path<? extends BlogCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogCategory(PathMetadata metadata) {
        super(BlogCategory.class, metadata);
    }

}

