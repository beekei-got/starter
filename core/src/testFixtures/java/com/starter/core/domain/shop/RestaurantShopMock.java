package com.starter.core.domain.shop;

import com.starter.core.domain.shop.restaurant.RestaurantShop;
import com.starter.core.domain.shop.restaurant.RestaurantType;

public class RestaurantShopMock {

	public static RestaurantShop createShop() {
		RestaurantShop restaurantShop = RestaurantShop.createShop(
			RestaurantType.KOREAN_FOOD,
			"봉교식당",
			"01234124124",
			Address.create("서울특별시", "강남구", "102호"));
		restaurantShop.id = 1L;
		return restaurantShop;
	}

}
