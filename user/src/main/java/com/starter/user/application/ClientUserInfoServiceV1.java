package com.starter.user.application;

import com.starter.user.application.dto.ClientUserInfoDTO;
import com.starter.user.application.dto.ClientUserListDTO;
import com.starter.user.application.dto.GetClientUserListSearchFieldDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ClientUserInfoServiceV1 {
    @Transactional(readOnly = true)
    ClientUserInfoDTO getClientUserInfo(long userId);
    @Transactional(readOnly = true)
    Page<ClientUserListDTO> getClientUserList(GetClientUserListSearchFieldDTO searchField, PageRequest pageRequest);
}
