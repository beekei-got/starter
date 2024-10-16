package com.starter.core.domain.shop;

import com.starter.core.domain.shop.restaurant.RestaurantShop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {

	@Test
	@DisplayName("사업자 회원 설정")
	void setBusinessUser() {
		RestaurantShop restaurantShop = RestaurantShopMock.createShop();

		restaurantShop.setBusinessUser(1L);
		assertThat(restaurantShop.getShopOfUsers().size()).isEqualTo(1);
		assertThat(restaurantShop.getShopOfUsers().stream().anyMatch(u -> u.getUserId() == 1L)).isEqualTo(true);

		restaurantShop.setBusinessUser(2L);
		assertThat(restaurantShop.getShopOfUsers().size()).isEqualTo(2);
		assertThat(restaurantShop.getShopOfUsers().stream().anyMatch(u -> u.getUserId() == 2L)).isEqualTo(true);

		// 중복 설정은 그대로 유지
		restaurantShop.setBusinessUser(2L);
		assertThat(restaurantShop.getShopOfUsers().size()).isEqualTo(2);
		assertThat(restaurantShop.getShopOfUsers().stream().anyMatch(u -> u.getUserId() == 3L)).isEqualTo(false);
	}

}