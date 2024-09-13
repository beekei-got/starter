package com.starter.place.domain.place;

import com.starter.place.domain.EntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Comment("장소")
@Getter
@Entity
@Table(name = "place", indexes = {
    @Index(name="IDX_place_place_type", columnList = "place_type"),
    @Index(name="IDX_place_place_status", columnList = "place_status"),
})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "place_type", length = 50, discriminatorType = DiscriminatorType.STRING)
public abstract class Place extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    @Column(name = "id")
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Comment("장소 유형")
    @Column(name = "place_type", length = 50, nullable = false, insertable = false, updatable = false)
    protected PlaceType type;

    @Enumerated(EnumType.STRING)
    @Comment("장소 상태")
    @Column(name = "place_status", length = 50, nullable = false)
    protected PlaceStatus status;

    @Comment("장소명")
    @Column(name = "place_name", length = 150, nullable = false)
    protected String name;

    @Comment("오픈일자")
    @Column(name = "open_date")
    protected LocalDate openDate;

    @Embedded
    protected RegionCode regionCode;

    @Embedded
    protected Address address;

    protected Place(PlaceType type, PlaceStatus status,
                    String name, @Nullable LocalDate openDate,
                    RegionCode regionCode, Address address) {
        this.type = type;
        this.status = status;
        this.name = name;
        this.openDate = openDate;
        this.regionCode = regionCode;
        this.address = address;
    }

    public void openReady() {
        this.status = PlaceStatus.OPEN_READY;
    }

    public void close() {
        this.status = PlaceStatus.CLOSE;
    }

    public void open() {
        this.status = PlaceStatus.OPEN;
    }

}
