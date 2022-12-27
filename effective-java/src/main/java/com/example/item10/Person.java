package com.example.item10;

/**
 * 1.2 인스턴스가 논리적으로 같은지 검사할 일이 없는 경우<br>
 * 3.3 추이성<br>
 * <a href="https://github.com/Study-2-Effective-Java/Effective-Java/discussions/26">참고 링크</a>
 */
public class Person {

    public Person(int 주민번호, String 이름) {
        this.주민번호 = 주민번호;
        this.이름 = 이름;
    }

    private int 주민번호;
    private String 이름;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return 주민번호 == person.주민번호;
    }

    public static void main(String[] args) {
        Person personA = new Person(1, "A Name");
        Person personB = new Person(2, "B Name");
        Person personAA = new Person(1, "AA Name");
        // -------------------------------------------------------
        System.out.println(personA.equals(personB));  // false
        System.out.println(personB.equals(personAA)); // false
        System.out.println(personA.equals(personAA)); // true
        /*
            이 예시가 추이성을 위반한 것이 아니냐고 할 수 있는데,
            추이성은 '같을때' 만 해당된다.
         */
    }
}
