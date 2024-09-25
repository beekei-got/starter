package com.starter.user.presentation.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResignClientUserRequest {

    @NotNull
    @Min(1)
    private Long userId;

}
