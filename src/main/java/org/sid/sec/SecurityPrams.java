package org.sid.sec;

public interface SecurityPrams {
    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String SECRET="syllabus@baba.net";
    public static final String HEADER_PREFIX = "Bearer ";
    public static final long EXPIRATION=864000; //10*24*3600
}
