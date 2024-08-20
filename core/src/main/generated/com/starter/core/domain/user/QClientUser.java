package com.starter.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClientUser is a Querydsl query type for ClientUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClientUser extends EntityPathBase<ClientUser> {

    private static final long serialVersionUID = 1388451730L;

    public static final QClientUser clientUser = new QClientUser("clientUser");

    public final QUser _super = new QUser(this);

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDatetime = _super.createdDatetime;

    public final StringPath email = createString("email");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    public final StringPath nickname = createString("nickname");

    public final StringPath phoneNumber = createString("phoneNumber");

    //inherited
    public final EnumPath<UserStatus> status = _super.status;

    //inherited
    public final EnumPath<UserType> type = _super.type;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDatetime = _super.updatedDatetime;

    public QClientUser(String variable) {
        super(ClientUser.class, forVariable(variable));
    }

    public QClientUser(Path<? extends ClientUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClientUser(PathMetadata metadata) {
        super(ClientUser.class, metadata);
    }

}

