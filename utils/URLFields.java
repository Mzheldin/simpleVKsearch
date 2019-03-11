package com.example.androidvksample.utils;

public enum URLFields {
    ABOUT("about"),
    BDATE("bdate"),
    HOME_TOWN("home_town"),
    INTERESTS("interests"),
    ONLINE("online"),
    ID("id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),

    VK_API_BASE_URL("https://api.vk.com"),
    VK_USERS_GET ("/method/users.get"),
    PARAM_USER_ID("user_ids"),
    PARAM_VERSION("v"),
    PARAM_FIELDS("fields"),
    VERSION("5.8"),
    PARAM_ACCESS_TOKEN("access_token"),
    ACCESS_TOKEN("73561e5773561e5773561e5797733f100f7735673561e572f2a28a02e6fc88198536bfb");

    private String field;

    URLFields(String field) {
        this.field = field;
    }

    public String getValue(){
        return field;
    }
}
