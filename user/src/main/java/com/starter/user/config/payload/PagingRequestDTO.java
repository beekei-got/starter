package com.starter.user.config.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public class PagingRequestDTO {

    private final static int DEFAULT_PAGE = 1;
    private final static int DEFAULT_SIZE = 10;

    @Getter
    @Schema(description = "default: " + DEFAULT_PAGE)
    private Integer page;

    @Getter
    @Schema(description = "default: " + DEFAULT_SIZE)
    private Integer size;

    public PagingRequestDTO(Integer page, Integer size) {
        setPage(page);
        setSize(size);
    }

    public void setPage(Integer page) {
        this.page = Optional.ofNullable(page)
            .map(p -> p <= 0 ? 1 : p)
            .orElse(DEFAULT_PAGE);
    }

    public void setSize(Integer size) {
        this.size = Optional.ofNullable(size)
            .map(s -> s <= 0 ? 1 : s)
            .orElse(DEFAULT_SIZE);
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size);
    }

}
