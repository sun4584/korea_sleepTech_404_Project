package com.example.back404.teamproject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectAffiliation {
    LIBERAL_ARTS("인문계열"),
    NATURAL_SCIENCES("자연계열");

    private final String description;

    SubjectAffiliation(String description) {
        this.description = description;
    }
}