package com.starter.core.domain.shop.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestaurantType {

	KOREAN_FOOD("한식"),
	JAPANESE_FOOD("일식"),
	CHINESE_FOOD("중식"),
	SNACK_FOOD("분식"),
	WESTERN_FOOD("양식")
	;

	private final String name;

}
