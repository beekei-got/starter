package com.starter.place.domain.cafe;

import com.starter.place.domain.place.Address;
import com.starter.place.domain.place.CafeMock;
import com.starter.place.domain.place.PlaceStatus;
import com.starter.place.domain.place.RegionCode;
import com.starter.place.domain.place.cafe.Cafe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CafeTest {

    @Test
    @DisplayName("카페 생성")
    public void createCafe() {
        PlaceStatus status = PlaceStatus.OPEN;
        String name = "BeekeiCafe";
        LocalDate openDate = LocalDate.of(2020, 10, 20);
        RegionCode regionCode = new RegionCode("100000000", "110000000");
        Address address = new Address("123-12", "서울특별시 ...", "102호");
        String registrationNumber = "1234567890";

        Cafe cafe = Cafe.createCafe(status, name, openDate, regionCode, address, registrationNumber);

        assertThat(cafe.getStatus()).isEqualTo(status);
        assertThat(cafe.getName()).isEqualTo(name);
        assertThat(cafe.getOpenDate()).isEqualTo(openDate);
        assertThat(cafe.getRegionCode()).isEqualTo(regionCode);
        assertThat(cafe.getAddress()).isEqualTo(address);
        assertThat(cafe.getRegistrationNumber()).isEqualTo(registrationNumber);
    }

    @Test
    @DisplayName("카페 정보 변경")
    public void updateInfo() {
        Cafe cafe = CafeMock.createOpenCafe();

        String name = "BeekeiCafeUpdate";
        LocalDate openDate = LocalDate.of(2024, 1, 15);
        RegionCode regionCode = new RegionCode("200000000", "220000000");
        Address address = new Address("222-22", "경기도 수원시 ...", "202호");
        String registrationNumber = "2222222220";
        cafe.updateInfo(name, openDate, regionCode, address, registrationNumber);

        assertThat(cafe.getName()).isEqualTo(name);
        assertThat(cafe.getOpenDate()).isEqualTo(openDate);
        assertThat(cafe.getRegionCode()).isEqualTo(regionCode);
        assertThat(cafe.getAddress()).isEqualTo(address);
        assertThat(cafe.getRegistrationNumber()).isEqualTo(registrationNumber);
    }

}