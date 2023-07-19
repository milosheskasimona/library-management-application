package com.simonamilosheska.constants;

public final class SecurityConstants {
  private SecurityConstants() throws Exception{
    throw new Exception("Don't instantiate this class");
  }

  public static final String JWT_COOKIE_NAME = "access_token";
}
