package com.starter.place.domain.region;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Comment("군,구(지역)")
@Getter
@Entity
@Table(name = "region_gun_gu")
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionGunGu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    @Column(name = "id")
    private Long id;

    @Comment("지역명")
    @Column(name = "region_name", length = 50, nullable = false)
    private String name;

    @Comment("지역코드")
    @Column(name = "region_code", length = 100, nullable = false)
    private String code;

    @Comment("정렬순서")
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_si_id", nullable = false, updatable = false)
    private RegionSi si;

}
