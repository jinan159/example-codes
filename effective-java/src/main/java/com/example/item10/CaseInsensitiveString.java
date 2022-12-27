package com.example.item10;

import java.util.Objects;

/**
 * 3.2 대칭성<br>
 * <a href="https://github.com/Study-2-Effective-Java/Effective-Java/discussions/26">참고 링크</a>
 */
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString)o).s);
        if (o instanceof String) // 이 경우가 문제..!
            return s.equalsIgnoreCase((String) o);
        return false;
    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("hello");
        String str = new String("hello");

        System.out.println(cis.equals(str)); // true
        System.out.println(str.equals(cis)); // false
    }
}
