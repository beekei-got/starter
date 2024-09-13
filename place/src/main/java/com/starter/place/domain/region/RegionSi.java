package com.starter.place.domain.region;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Comment("시(지역)")
@Getter
@Entity
@Table(name = "region_si")
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionSi {

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

    @OrderBy("sortOrder ASC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "si")
    private List<RegionGunGu> gunGuList;

}
