package com.starter.core.domain.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShopStatus {

	OPEN_READY("오픈 준비"),
	OPEN("오픈"),
	CLOSE("폐점");

	private final String name;

}
