package com.starter.place.domain.place;

import com.starter.place.domain.place.Address;
import com.starter.place.domain.place.PlaceStatus;
import com.starter.place.domain.place.RegionCode;
import com.starter.place.domain.place.cafe.Cafe;

import java.time.LocalDate;

public class CafeMock {

    public static Cafe createOpenReadyCafe() {
        Cafe cafe = Cafe.createCafe(PlaceStatus.OPEN_READY,
            "OpenCafe", LocalDate.of(2011, 11, 11),
            new RegionCode("1000000000", "1100000000"),
            new Address("111-111", "서울특별시 ...", "101호"),
            "111111111111");
        cafe.id = 1L;
        return cafe;
    }

    public static Cafe createOpenCafe() {
        Cafe cafe = Cafe.createCafe(PlaceStatus.OPEN,
            "OpenReadyCafe", LocalDate.of(2022, 2, 22),
            new RegionCode("2000000000", "2200000000"),
            new Address("222-222", "서울특별시 ...", "202호"),
            "22222222222");
        cafe.id = 2L;
        return cafe;
    }

}
