package com.starter.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminUser is a Querydsl query type for AdminUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminUser extends EntityPathBase<AdminUser> {

    private static final long serialVersionUID = 31123166L;

    public static final QAdminUser adminUser = new QAdminUser("adminUser");

    public final QUser _super = new QUser(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath loginId = createString("loginId");

    //inherited
    public final StringPath name = _super.name;

    public final StringPath password = createString("password");

    //inherited
    public final EnumPath<UserStatus> status = _super.status;

    //inherited
    public final EnumPath<UserType> type = _super.type;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDatetime = _super.updatedDatetime;

    public QAdminUser(String variable) {
        super(AdminUser.class, forVariable(variable));
    }

    public QAdminUser(Path<? extends AdminUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminUser(PathMetadata metadata) {
        super(AdminUser.class, metadata);
    }

}

