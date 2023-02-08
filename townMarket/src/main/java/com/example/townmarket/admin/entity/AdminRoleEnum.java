package com.example.townmarket.admin.entity;

public enum AdminRoleEnum {
  TOP_MANAGER(Authority.TOP_MANAGER),
  MIDDLE_MANAGER(Authority.MIDDLE_MANAGER);

  private final String authority;

  AdminRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {

    public static final String TOP_MANAGER = "ROLE_TOP_MANAGER";
    public static final String MIDDLE_MANAGER = "ROLE_MIDDLE_MANAGER";
  }
}
