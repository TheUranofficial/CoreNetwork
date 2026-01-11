package com.theuran.corenetwork;

import com.theuran.corenetwork.utils.Side;

public class Dispatcher extends AbstractDispatcher {
    public Dispatcher(Side side) {
        super(side);
    }

    @Override
    protected void setup() {
        this.register(Test.class);
    }
}
