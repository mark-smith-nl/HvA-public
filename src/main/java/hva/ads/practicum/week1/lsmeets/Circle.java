package hva.ads.practicum.week1.lsmeets;

import java.awt.*;
import java.util.Objects;

public class Circle extends Shape {

    private double radius = 0;


    public Circle(Color colour, double radius) {
        super(colour);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return (this.radius * this.radius * Math.PI);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return getArea() == circle.getArea() && getColor().equals(circle.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }
}

