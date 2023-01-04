package com.example.item17;

import java.time.LocalDate;
import java.util.Date;

public class DateAndLocalDate {

    public static void main(String[] args) {
        // 생성한 Date 객체의 상태가 변함 == 가변 객체
        Date date = new Date();
        date.setDate(12);

        /*
            LocalDate 는 외부에서 호출할 수 있는 생성자가 없음(생성자가 private 임)
            그래서 무조건 정적팩토리메서드로 생성해야 함
         */
        // 생성한 객체와 상태가 변한 객체가 다름 == 불변 객체
        LocalDate localDate = LocalDate.now();
        LocalDate plusLocalDate = localDate.plusDays(1);

        System.out.println(localDate == plusLocalDate); // false
    }
}
