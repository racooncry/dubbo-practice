package com.shenfeng.yxw.dubbo.spi.dubbo;

public class Rebot2Wrapper implements Robot2 {
    private Robot2 robot2;

    public Rebot2Wrapper(Robot2 robot2) {
        this.robot2 = robot2;
    }

    @Override
    public void sayHello() {
        System.out.println("begin");
        robot2.sayHello();
        System.out.println("end");
    }
}
