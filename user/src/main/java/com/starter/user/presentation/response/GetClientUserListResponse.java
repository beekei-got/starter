package com.starter.user.presentation.response;

import com.starter.user.application.dto.ClientUserListDTO;
import com.starter.user.config.payload.PagingResponseDTO;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class GetClientUserListResponse extends PagingResponseDTO {

    public GetClientUserListResponse(Page<ClientUserListDTO> clientUserPage) {
        super(clientUserPage);
        this.clientUserList = clientUserPage.getContent();
    }

    private final List<ClientUserListDTO> clientUserList;

}
