package com.example.item40;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SomethingSet {
    public static void main(String[] args) {
        Set<Something> set = new HashSet<>();
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 500; j++) {
                set.add(new Something(j));
            }
        }
        System.out.println(set.size()); // 25
    }
}

class Something {
    private final int num;

    public Something(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Something)) {
            return false;
        }
        Something something = (Something) o;
        return num == something.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
