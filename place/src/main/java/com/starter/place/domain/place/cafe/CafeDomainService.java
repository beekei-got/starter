package com.starter.place.domain.place.cafe;

import com.starter.core.config.payload.exception.DuplicatedDataException;
import com.starter.core.config.payload.exception.ExceptionMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeDomainService {

    private final CafeRepository cafeRepository;

    public void checkDuplicated(Cafe cafe) {
        long duplicatedCafeCount = Optional.ofNullable(cafe.getId())
            .map(cafeId -> cafeRepository.countByRegistrationNumberAndIdNot(cafe.getRegistrationNumber(), cafeId))
            .orElseGet(() -> cafeRepository.countByRegistrationNumber(cafe.getRegistrationNumber()));
        if (duplicatedCafeCount > 0)
            throw new DuplicatedDataException(ExceptionMessageType.DUPLICATED_CAFE);
    }

    public void save(Cafe cafe) {
        cafeRepository.save(cafe);
    }

}
