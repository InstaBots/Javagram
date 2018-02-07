package net.steppschuh.instabots.utils;

/**
 * Created by Steppschuh on 07.02.18.
 */
public class ParserUtil {

    public static String getStringAfter(String originalString, String subString) {
        int index = originalString.indexOf(subString);
        return index < 0 ? originalString : originalString.substring(index + subString.length());
    }

    public static String getStringBefore(String originalString, String subString) {
        int index = originalString.indexOf(subString);
        return index < 0 ? originalString : originalString.substring(0, index);
    }

    public static String getStringBetween(String originalString, String startSubString, String endSubString) {
        String subString = getStringAfter(originalString, startSubString);
        return getStringBefore(subString, endSubString);
    }

}
