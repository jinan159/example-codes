package com.example.lec02;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    // @Nullable // 이 어노테이션 붙이면 Kotlin 의 nullable 타입에 사용할 수 있다
    // @NotNull // 이 어노테이션을 붙이면 Kotlin 에서 not null 타입으로 인식한다
    // 붙이지 않으면 '플랫폼 타입' 으로 인식하여 실행은 되지만, null 이라면 런타임에 오류가 발생한다
    public String getName() {
        return name;
    }
}
