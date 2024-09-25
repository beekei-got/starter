package com.starter.user.presentation.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IssueAuthTokenRequest {

    @NotNull
    private UUID authTokenId;

}
