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
public class Address {

    @Comment("우편번호")
    @Column(name = "zip_code", length = 50, nullable = false)
    private String zipCode;

    @Comment("기본 주소")
    @Column(name = "default_address", length = 250, nullable = false)
    private String defaultAddress;

    @Comment("상세 주소")
    @Column(name = "detail_address", length = 250)
    private String detailAddress;

    public Address(String zipCode, String defaultAddress) {
        this.zipCode = zipCode;
        this.defaultAddress = defaultAddress;
    }

}
