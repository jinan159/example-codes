package com.example.item10;

/**
 * 0.2 동등성<br>
 * <a href="https://github.com/Study-2-Effective-Java/Effective-Java/discussions/26">참고 링크</a>
 */
public class Equality {

    public static void main(String[] args) {
        String nickName1 = new String("jinan159");
        String nickName2 = new String("jinan159");

        System.out.println(nickName1 == nickName2);      // false (동일성)
        System.out.println(nickName1.equals(nickName2)); // true  (동등성)
    }
}
