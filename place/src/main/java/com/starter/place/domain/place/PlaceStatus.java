package com.starter.place.domain.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaceStatus {

    OPEN_READY("오픈준비"),
    OPEN("오픈"),
    CLOSE("폐점");

    private final String name;

}
