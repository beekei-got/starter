package com.starter.core.domain.shop.restaurant;

import com.starter.core.domain.shop.Address;
import com.starter.core.domain.shop.ShopStatus;
import com.starter.core.domain.shop.ShopType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantShopTest {

	@Test
	@DisplayName("음식점 생성")
	void createShop() {
		RestaurantType restaurantType = RestaurantType.KOREAN_FOOD;
		String shopName = "봉교식당";
		String businessNumber = "01234124124";
		Address address = Address.create("서울특별시", "강남구", "102호");

		RestaurantShop restaurantShop = RestaurantShop.createShop(restaurantType, shopName, businessNumber, address);

		assertThat(restaurantShop.getShopType()).isEqualTo(ShopType.RESTAURANT);
		assertThat(restaurantShop.getShopStatus()).isEqualTo(ShopStatus.OPEN_READY);
		assertThat(restaurantShop.getRestaurantType()).isEqualTo(restaurantType);
		assertThat(restaurantShop.getShopName()).isEqualTo(shopName);
		assertThat(restaurantShop.getBusinessNumber()).isEqualTo(businessNumber);
		assertThat(restaurantShop.getAddress()).isEqualTo(address);
	}

}