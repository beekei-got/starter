package com.starter.core.domain.shop;

import com.starter.core.domain.blog.Blog;
import com.starter.core.domain.blog.BlogConnectUser;
import com.starter.core.domain.blog.BlogConnectUserId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Comment("매장 회원")
@Getter
@Entity
@Table(name = "shop_or_user", uniqueConstraints = {
	@UniqueConstraint(name = "UK_shop_or_user_id", columnNames = { "shop_id", "user_id" })
})
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopOfUser {

	@EmbeddedId
	private ShopOfUserId id;

	@MapsId("shopId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id", nullable = false, updatable = false)
	private Shop shop;

	protected static ShopOfUser create(Shop shop, long userId) {
		return ShopOfUser.builder().id(new ShopOfUserId(shop.getId(), userId)).shop(shop).build();
	}

	public long getUserId() {
		return this.id.getUserId();
	}

	public long getShopId() {
		return this.id.getShopId();
	}

}
