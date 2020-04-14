package fr.olaqin.pfd.utils;

import static org.apache.commons.lang3.StringUtils.replaceEach;

public class StringUtilsPfd {

    public static String replaceAccent(String word) {
        final String accents = "ÀÁÂÃÄÅàáâãäåÈÉÊËèéêëç";
        final String letters = "AAAAAAaaaaaaEEEEeeeec";

        return replaceEach(word, accents.split(""), letters.split(""));
    }
}
