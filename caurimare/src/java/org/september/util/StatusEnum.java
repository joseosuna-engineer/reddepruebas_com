package org.september.util;

public enum StatusEnum {

    STATUS_ESTABLE(0),
    STATUS_CRITICO(1),
    STATUS_INESTABLE(2);         

    private final int value;

    private StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
