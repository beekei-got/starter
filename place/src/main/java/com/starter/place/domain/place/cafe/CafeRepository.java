package com.starter.place.domain.place.cafe;

public interface CafeRepository {
    Cafe save(Cafe cafe);
    long countByRegistrationNumber(String registrationNumber);
    long countByRegistrationNumberAndIdNot(String registrationNumber, long cafeId);
}
