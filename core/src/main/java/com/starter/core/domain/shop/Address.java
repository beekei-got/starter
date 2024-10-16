package com.starter.core.domain.shop;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.lang.Nullable;

@Embeddable
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

	@Comment("우편번호")
	@Column(name = "zip_code", length = 50, nullable = false)
	protected String zipCode;

	@Comment("주소")
	@Column(name = "address", length = 250, nullable = false)
	protected String address;

	@Comment("상세주소")
	@Column(name = "detail_address", length = 250)
	protected String detailAddress;

	public static Address create(String zipCode, String address, @Nullable String detailAddress) {
		return Address.builder()
			.zipCode(zipCode)
			.address(address)
			.detailAddress(detailAddress)
			.build();
	}

}
