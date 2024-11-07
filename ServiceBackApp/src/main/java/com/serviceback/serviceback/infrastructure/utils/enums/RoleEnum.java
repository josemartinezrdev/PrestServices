package com.serviceback.serviceback.infrastructure.utils.enums;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ADMINISTRATOR(Arrays.asList(
        RolePermission.READ_ALL_PRODUCTS,
        RolePermission.READ_ONE_PRODUCT,
        RolePermission.CREATE_ONE_PRODUCT,
        RolePermission.UPDATE_ONE_PRODUCT,
        RolePermission.DISABLE_ONE_PRODUCT,

        RolePermission.READ_ALL_CATEGORIES,
        RolePermission.READ_ONE_CATEGORY,
        RolePermission.CREATE_ONE_CATEGORY,
        RolePermission.UPDATE_ONE_CATEGORY,
        RolePermission.DISABLE_ONE_CATEGORY,

        RolePermission.READ_MY_PROFILE
    )),

    ASSITANT_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_PRODUCT,
            RolePermission.UPDATE_ONE_PRODUCT,

            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_ONE_CATEGORY,       
            RolePermission.UPDATE_ONE_CATEGORY,     

            RolePermission.READ_MY_PROFILE
    )),

    CLIENTE(Arrays.asList(
        RolePermission.READ_MY_PROFILE,
        RolePermission.READ_ALL_TYPES_PERSONS
    )),

    PROFESIONAL(Arrays.asList(
        RolePermission.READ_APPLICATION,
        RolePermission.READ_MY_PROFILE
    ));

    private List<RolePermission> permissions;

    RoleEnum(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

}
