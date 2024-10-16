package com.starter.core.domain.shop.restaurant;

import com.starter.core.domain.shop.Address;
import com.starter.core.domain.shop.Shop;
import com.starter.core.domain.shop.ShopType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("음식점 매장")
@Getter
@Entity
@Table(name = "restaurant_shop", uniqueConstraints = {
	@UniqueConstraint(name = "UK_client_user_email", columnNames = { "email" }),
	@UniqueConstraint(name = "UK_client_user_nickname", columnNames = { "nickname" }),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DiscriminatorValue("RESTAURANT")
public class RestaurantShop extends Shop {

	@Enumerated(EnumType.STRING)
	@Comment("음식점 유형")
	@Column(name = "restaurant_type", length = 50, nullable = false)
	private RestaurantType restaurantType;

	private RestaurantShop(RestaurantType restaurantType, String shopName, String businessNumber, Address address) {
		super(ShopType.RESTAURANT, shopName, businessNumber, address);
		this.restaurantType = restaurantType;
	}

	public static RestaurantShop createShop(RestaurantType restaurantType, String shopName, String businessNumber, Address address) {
		return new RestaurantShop(restaurantType, shopName, businessNumber, address);
	}

}
