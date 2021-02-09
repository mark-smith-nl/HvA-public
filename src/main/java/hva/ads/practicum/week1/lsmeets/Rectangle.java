package hva.ads.practicum.week1.lsmeets;

import java.awt.*;
import java.util.Objects;

public class Rectangle extends Shape {
    private int width = 0;
    private int lenght = 0;

    public Rectangle(Color colour, int width, int lenght) {
        super(colour);
        this.width = width;
        this.lenght = lenght;
    }

    @Override
    public double getArea() {
        return this.width * this.lenght;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width &&
                lenght == rectangle.lenght && getColor().equals(rectangle.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width, lenght);
    }
}
