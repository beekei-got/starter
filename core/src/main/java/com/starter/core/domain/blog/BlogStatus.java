package com.starter.core.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlogStatus {

    OPEN("개설"),
    CLOSE("폐쇄");

    private final String name;

}
