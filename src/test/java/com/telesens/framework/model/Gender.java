package com.telesens.framework.model;


import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum()
public enum Gender {

    @XmlEnumValue("m")
    @JsonProperty("m")
    MALE,

    @XmlEnumValue("f")
    @JsonProperty("f")
    FEMALE;

    public static Gender valueOf(char name) {
        switch (name) {
            case 'f':
                return Gender.FEMALE;

            case 'm':
                return Gender.MALE;
        }
        return null;
    }

    public static Gender fromRu(char ru) {
        switch (ru) {
            case 'ж':
                return Gender.FEMALE;

            case 'м':
                return Gender.MALE;
        }
        return null;
    }

    public Character toChar() {
        switch (this) {
            case MALE:
                return 'm';

            case FEMALE:
                return 'f';
        }
        return null;
    }

    @Override
    public String toString() {
        return toChar().toString();
    }
}
