package com.spay.utils.enums;

public enum DatePatternEnum {
    YYYYMMDD("YYYYMMDD"), YYYY_MM_DD("YYYY-MM-dd"), yyyyMMdd("yyyyMMdd");

    final String value;

    DatePatternEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}