package com.module.DesignMode.singleton;

/**
 * 懒汉式
 * 在第二种写法的基础上进行优化
 * 缺点: 虽然减小了锁的粒度，但是多线程并发时还是会出现创建多个对象的问题
 */
public class LazySingleton3 {
    private static LazySingleton3 lazySingleton;

    private LazySingleton3() {
    }

    public static LazySingleton3 getInstance() {
        if (lazySingleton == null) {
            synchronized (LazySingleton3.class) {
                lazySingleton = new LazySingleton3();
            }
        }
        return lazySingleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LazySingleton3.getInstance());
            }).start();
        }
    }
}
