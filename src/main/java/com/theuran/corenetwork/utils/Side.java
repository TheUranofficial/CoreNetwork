package com.theuran.corenetwork.utils;

public enum Side {
    CLIENT,
    SERVER;

    public boolean isClient() {
        return this.equals(CLIENT);
    }

    public boolean isServer() {
        return !this.isClient();
    }
}