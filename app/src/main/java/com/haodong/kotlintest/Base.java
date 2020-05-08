package com.haodong.kotlintest;

/**
 * created by linghaoDo on 2020-04-26
 * description:
 * <p>
 * version:
 */
public class Base {
    public void fun() {
        System.out.println("i'm Base foo");
    }

    class Extended extends Base {
        @Override
        public void fun() {
            System.out.println("I'm extended foo");
        }
    }
}

