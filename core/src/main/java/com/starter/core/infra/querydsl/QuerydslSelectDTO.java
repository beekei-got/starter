package com.starter.core.infra.querydsl;

import com.querydsl.core.types.ConstructorExpression;

public interface QuerydslSelectDTO {
    ConstructorExpression<?> constructor();
}
