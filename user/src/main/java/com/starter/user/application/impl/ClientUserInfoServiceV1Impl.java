package com.starter.user.application.impl;

import com.starter.user.application.ClientUserInfoServiceV1;
import com.starter.user.application.dto.ClientUserInfoDTO;
import com.starter.user.application.dto.ClientUserListDTO;
import com.starter.user.application.dto.GetClientUserListSearchFieldDTO;
import com.starter.user.config.payload.exception.ExceptionMessageType;
import com.starter.user.config.payload.exception.NotExistDataException;
import com.starter.user.config.querydsl.QuerydslRepository;
import com.starter.user.domain.user.client.ClientUser;
import com.starter.user.domain.user.client.service.ClientUserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import static com.starter.user.domain.user.client.QClientUser.clientUser;

@Component
@RequiredArgsConstructor
public class ClientUserInfoServiceV1Impl implements ClientUserInfoServiceV1 {

    private final QuerydslRepository querydslRepository;
    private final ClientUserDomainService clientUserDomainService;

    @Override
    public ClientUserInfoDTO getClientUserInfo(long userId) {
        ClientUser clientUser = clientUserDomainService.getClientUser(userId);
        return ClientUserInfoDTO.of(clientUser);
    }

    @Override
    public Page<ClientUserListDTO> getClientUserList(GetClientUserListSearchFieldDTO searchField, PageRequest pageRequest) {
        return querydslRepository
            .select(ClientUserListDTO.class)
            .countSelect(clientUser.countDistinct())
            .from(clientUser)
            .optionalWhereIn(searchField.getUserStatusIn(), clientUser.status)
            .optionalLike(searchField.getUserEmail(), clientUser.email)
            .optionalLike(searchField.getUserName(), clientUser.name)
            .optionalLike(searchField.getNickname(), clientUser.nickname)
            .optionalWhereIn(searchField.getUserGenderIn(), clientUser.gender)
            .optionalMoreThanEqual(searchField.getStartUserBirthday(), clientUser.birthday)
            .optionalLessThanEqual(searchField.getEndUserBirthday(), clientUser.birthday)
            .optionalLike(searchField.getPhoneNumber(), clientUser.phoneNumber)
            .getPage(pageRequest);
    }

}
