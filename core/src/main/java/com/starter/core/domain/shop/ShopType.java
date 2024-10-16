package com.starter.core.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShopType {

	RESTAURANT("음식점");

	private final String name;

}
