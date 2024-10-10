package com.starter.core.domain.user;


import com.starter.core.domain.user.admin.AdminUser;

public class AdminUserMock {

    public static AdminUser createUser() {
        AdminUser adminUser = AdminUser.createUser(
                "admin",
                "admin1234",
                "관리자");
        adminUser.id = 1L;
        return adminUser;
    }

    public static AdminUser createResignUser() {
        AdminUser adminUser = createUser();
        adminUser.resign();
        return adminUser;
    }

    public static AdminUser createBlockUser() {
        AdminUser adminUser = createUser();
        adminUser.block();
        return adminUser;
    }

}
