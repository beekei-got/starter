package com.starter.place.domain.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaceType {

    RESTAURANT("식당"),
    CAFE("카페"),
    NATURE("자연"),
    SPORT("스포츠"),
    THEME_PARK("테마파크"),
    CAMPING("캠핑"),
    ETC("기타");

    private final String name;

}
