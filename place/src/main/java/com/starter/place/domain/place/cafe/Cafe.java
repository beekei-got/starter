package com.starter.place.domain.place.cafe;

import com.starter.place.domain.place.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Comment("카페")
@Getter
@Entity
@Table(name = "place_cafe", uniqueConstraints = {
    @UniqueConstraint(name = "UK_place_cafe_registration_number", columnNames = { "registration_number" })
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DiscriminatorValue("CAFE")
public class Cafe extends Place {

    @Comment("사업자등록번호")
    @Column(name = "registration_number")
    private String registrationNumber;

    private Cafe(PlaceStatus status,
                 String name, @Nullable LocalDate openDate,
                 RegionCode regionCode, Address address,
                 String registrationNumber) {
        super(PlaceType.CAFE, status, name, openDate, regionCode, address);
        this.registrationNumber = registrationNumber;
    }

    public static Cafe createCafe(PlaceStatus status,
                                  String name, @Nullable LocalDate openDate,
                                  RegionCode regionCode, Address address,
                                  String registrationNumber) {
        return new Cafe(status, name, openDate, regionCode, address, registrationNumber);
    }

    public void updateInfo(String name, @Nullable LocalDate openDate,
                           RegionCode regionCode, Address address,
                           String registrationNumber) {
        this.name = name;
        this.openDate = openDate;
        this.regionCode = regionCode;
        this.address = address;
        this.registrationNumber = registrationNumber;
    }

}
