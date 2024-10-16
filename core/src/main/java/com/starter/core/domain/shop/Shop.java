package com.starter.core.domain.shop;

import com.starter.core.domain.EntityBase;
import com.starter.core.domain.blog.BlogConnectUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.*;

@Comment("매장")
@Getter
@Entity
@Table(name = "shop", uniqueConstraints = {
	@UniqueConstraint(name = "UK_shop_business_number", columnNames = { "business_number" })
},
	indexes = {
	@Index(name="IDX_shop_shop_type", columnList = "shop_type"),
	@Index(name="IDX_shop_shop_status", columnList = "shop_status"),
})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "shop_type", length = 50, discriminatorType = DiscriminatorType.STRING)
public abstract class Shop extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("고유번호")
	@Column(name = "id")
	protected Long id;

	@Enumerated(EnumType.STRING)
	@Comment("매장 유형")
	@Column(name = "shop_type", length = 50, nullable = false, insertable = false, updatable = false)
	protected ShopType shopType;

	@Enumerated(EnumType.STRING)
	@Comment("매장 상태")
	@Column(name = "shop_status", length = 50, nullable = false)
	protected ShopStatus shopStatus;

	@Comment("매장명")
	@Column(name = "shop_name", length = 100, nullable = false)
	protected String shopName;

	@Comment("사업자등록번호")
	@Column(name = "business_number", length = 100, nullable = false)
	protected String businessNumber;

	@Embedded
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<ShopOfUser> shopOfUsers;

	protected Shop(ShopType shopType, String shopName, String businessNumber, Address address) {
		this.shopType = shopType;
		this.shopStatus = ShopStatus.OPEN_READY;
		this.shopName = shopName;
		this.businessNumber = businessNumber;
		this.address = address;
	}

	public void setBusinessUser(long businessUserId) {
		if (this.shopOfUsers == null) this.shopOfUsers = new HashSet<>();
		if (this.shopOfUsers.stream().noneMatch(r -> r.getUserId() == businessUserId))
			this.shopOfUsers.add(ShopOfUser.create(this, businessUserId));
	}

}
