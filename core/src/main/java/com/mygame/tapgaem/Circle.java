package com.mygame.tapgaem;

public class Circle {

    public enum Type {
        NORMAL,
        OBSTACLE,
        POWER_UP
    }

    public float x;
    public float y;
    public float radius;

    public float lifetime;
    public float maxLifetime;

    public Type type; // NEW

    public Circle(float x, float y, float radius, float maxLifetime, Type type) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.maxLifetime = maxLifetime;
        this.lifetime = 0;
        this.type = type;
    }

    public boolean isExpired() {
        return lifetime >= maxLifetime;
    }
}
