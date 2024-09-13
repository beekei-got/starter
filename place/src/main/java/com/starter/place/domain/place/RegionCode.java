package com.starter.place.domain.place;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionCode {

    @Comment("시 지역코드")
    @Column(name = "region_si_code", length = 100, nullable = false)
    private String siCode;

    @Comment("군구 지역코드")
    @Column(name = "region_gun_gu_code", length = 100, nullable = false)
    private String gunGuCode;

}
