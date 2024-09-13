package com.starter.place.infra;

import com.starter.place.domain.place.cafe.Cafe;
import com.starter.place.domain.place.cafe.CafeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeJpaRepository extends JpaRepository<Cafe, Long>, CafeRepository {
}
