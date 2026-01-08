package com.theuran.corenetwork;

public class Dispatcher extends AbstractDispatcher {
    @Override
    protected void setup() {
        this.register(Test.class);
    }
}
