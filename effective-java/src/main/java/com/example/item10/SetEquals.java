package com.example.item10;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * 1.3 상위 클래스의 equals 가 하위 클래스에도 딱 맞는 경우<br>
 * <a href="https://github.com/Study-2-Effective-Java/Effective-Java/discussions/26">참고 링크</a>
 */
public class SetEquals {

    public static void main(String[] args) {
        HashSet<Integer> set1 = new HashSet<>();
        LinkedHashSet<Integer> set2 = new LinkedHashSet<>();
        TreeSet<Integer> set3 = new TreeSet<>();

        int num = 1; set1.add(num); set2.add(num); set3.add(num);

        System.out.println(set1.equals(set2)); // true
        System.out.println(set2.equals(set3)); // true
        System.out.println(set1.equals(set3)); // true
    }
}
