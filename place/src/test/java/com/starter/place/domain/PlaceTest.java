package com.starter.place.domain;

import com.starter.place.domain.place.CafeMock;
import com.starter.place.domain.place.PlaceStatus;
import com.starter.place.domain.place.cafe.Cafe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlaceTest {

    @Test
    @DisplayName("장소 오픈 준비")
    public void openReady() {
        Cafe cafe = CafeMock.createOpenCafe();
        cafe.openReady();

        assertThat(cafe.getStatus()).isEqualTo(PlaceStatus.OPEN_READY);
    }

    @Test
    @DisplayName("장소 오픈")
    public void open() {
        Cafe cafe = CafeMock.createOpenReadyCafe();
        cafe.open();

        assertThat(cafe.getStatus()).isEqualTo(PlaceStatus.OPEN);
    }

    @Test
    @DisplayName("장소 폐점")
    public void close() {
        Cafe cafe = CafeMock.createOpenCafe();
        cafe.close();

        assertThat(cafe.getStatus()).isEqualTo(PlaceStatus.CLOSE);
    }

}