package de.b_sqr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Utils {

    public static String[] getStringsWithLength(String[] array, int length) {
        List<String> list = new ArrayList<>();
        for (String s : array) {
            if (s.length() == length) {
                list.add(s);
            }
        }
        return list.toArray(new String[0]);
    }
    public static String[] getDifLettersUniqueInA(String a, String b) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            String c = String.valueOf(a.charAt(i));
            if (!b.contains(c)) {
                list.add(c);
            }
        }
        return list.toArray(new String[0]);
    }
    public static String get3(String[] pattern) {
        String[] stringsWithLength5 = Utils.getStringsWithLength(pattern, 5);
        String one = Utils.getStringsWithLength(pattern, 2)[0];
        String three = "";
        for (String s : stringsWithLength5) {
            if (Utils.getDifLettersUniqueInA(one, s).length == 0) {
                three = s;
                break;
            }
        }
        return three;
    }
    public static String get6(String[] pattern) {
        String one = Utils.getStringsWithLength(pattern, 2)[0];
        String[] stringsWithLength6 = Utils.getStringsWithLength(pattern, 6);
        String six = "";
        for (String maybeSix : stringsWithLength6) {
            if (Utils.getDifLettersUniqueInA(one, maybeSix).length != 0) {
                six = maybeSix;
                break;
            }
        }
        return six;
    }
    public static String[] removeFromArray(String[] array, String remove) {
        List<String> list = new ArrayList<>();
        for (String s : array) {
            if (!s.equals(remove)) {
                list.add(s);
            }
        }
        return list.toArray(new String[0]);
    }

    public static String getDigit(String value, Map<String, String> dic) {
        if (value.length() == 2) {
            return "1";
        }
        if (value.length() == 3) {
            return "7";
        }
        if (value.length() == 4) {
            return "4";
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && !value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return "0";
        }
        if (value.contains(dic.get("a"))
                && !value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && !value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return "2";
        }
        if (value.contains(dic.get("a"))
                && !value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && !value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return "3";
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && !value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && !value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return "5";
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && !value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return "6";
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return "8";
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && !value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return "9";
        }
        return null;
    }
    public static int getDigitAsInt(String value, Map<String, String> dic) {
        if (value.length() == 2) {
            return 1;
        }
        if (value.length() == 3) {
            return 7;
        }
        if (value.length() == 4) {
            return 4;
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && !value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return 0;
        }
        if (value.contains(dic.get("a"))
                && !value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && !value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return 2;
        }
        if (value.contains(dic.get("a"))
                && !value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && !value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return 3;
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && !value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && !value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return 5;
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && !value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return 6;
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return 8;
        }
        if (value.contains(dic.get("a"))
                && value.contains(dic.get("b"))
                && value.contains(dic.get("c"))
                && value.contains(dic.get("d"))
                && !value.contains(dic.get("e"))
                && value.contains(dic.get("f"))
                && value.contains(dic.get("g"))) {
            return 9;
        }
        return -1;
    }

}
