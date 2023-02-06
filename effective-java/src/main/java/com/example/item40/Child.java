package com.example.item40;

public class Child {

    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();
        childClass.test();

        ChildAbstractClass childAbstractClass = new ChildAbstractClass();
        childAbstractClass.test();

        ChildInterface childInterface = new ChildInterface();
        childInterface.test();
    }
}

class ChildClass extends ParentClass {

    @Override // 없으면 컴파일 에러 발생
    void test() {
        System.out.println("ChildClass.Test()");
    }
}

class ChildAbstractClass extends ParentAbstractClass {

    // @Override // 어노테이션 없어도 컴파일 에러 없음
    void test() {
        System.out.println("ChildAbstractClass.Test()");
    }
}

class ChildInterface implements ParentInterface {

    /// @Override // 없어도 컴파일 에러 발생 안함
    public void test() {
        System.out.println("ChildInterface.Test()");
    }
}
