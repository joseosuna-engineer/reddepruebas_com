package org.september.util;

public enum EnumProperty {

    URL_CHECK("http://reddepruebas.com"),
    URL_MAIL_SENDER("http://cmtool-reddepruebas.appspot.com/mail");

    private final String value;

    private EnumProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
