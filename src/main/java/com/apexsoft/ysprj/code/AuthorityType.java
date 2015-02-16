package com.apexsoft.ysprj.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 21.
 * Time: 오후 10:23
 * To change this template use File | Settings | File Templates.
 */
public enum AuthorityType {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String value;

    private AuthorityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, AuthorityType> map = new HashMap<String, AuthorityType>();

    static {
        map.put(AuthorityType.ROLE_USER.getValue(), AuthorityType.ROLE_USER);
        map.put(AuthorityType.ROLE_ADMIN.getValue(), AuthorityType.ROLE_ADMIN);
    }

    /**
     *
     * @param value
     * @return
     */
    public static AuthorityType nameOf(String value) {
        return map.get(value);
    }

}
