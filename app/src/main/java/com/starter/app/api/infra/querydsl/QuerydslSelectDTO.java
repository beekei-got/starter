package com.starter.app.api.infra.querydsl;

import com.querydsl.core.types.ConstructorExpression;

public interface QuerydslSelectDTO {
    ConstructorExpression<?> constructor();
}
