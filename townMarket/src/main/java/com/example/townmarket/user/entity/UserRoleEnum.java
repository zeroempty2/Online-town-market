package com.example.townmarket.user.entity;

public enum UserRoleEnum {
  MEMBER(Authority.MEMBER);

  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {

    public static final String MEMBER = "ROLE_MEMBER";

  }
}
