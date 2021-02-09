package hva.ads.practicum.week1.msmith;

import java.util.Iterator;
import java.util.Objects;

import static hva.ads.practicum.week1.msmith.Board.Shape.Color;

/**
 * De shapes opdracht.
 * Alle classes in een file (static inner classes)
 * Maakt gebruik van eigen Color enums
 */

public class Board<S extends Board.Shape> implements Iterable<S> {

    private final S[][] shapes;

    private final int numberOfRows, numberOfColumns;

    // Note: When we declare the instance using the superclass only instances of one concrete class extending S may be added.
    private Class<? extends Shape> elementOfType;

    public boolean add(S shape, int row, int column) {
        if (row < 0 || row >= shapes.length) {
            return false;
        }

        if (column < 0 || column >= shapes[0].length) {
            return false;
        }

        if (shapes[row][column] != null) {
            return false; // Note: First delete the object and then insert.
        }

        if (elementOfType == null) {
            elementOfType = shape.getClass();
        } else {
            if (elementOfType != shape.getClass()) {
                throw new IllegalArgumentException("Wrong type");
            }
        }

        shapes[row][column] = shape;

        return true;
    }

    public Board(int numberOfRows, int numberOfColumns) {
        if (numberOfRows <= 0 || numberOfColumns <= 0) {
            throw new IllegalArgumentException("Specify a positive number of rows and columns");
        }

        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        shapes = (S[][]) (new Shape[numberOfRows][numberOfColumns]);
    }

    public Board(int numberOfRows) {
        this(numberOfRows, numberOfRows);
    }

    public S remove(int row, int column) {
        if (row < 0 || row >= shapes.length) return null;

        if (column < 0 || column >= shapes[0].length) return null;

        S value = shapes[row][column];

        shapes[row][column] = null;
        return value;
    }

/*    public Iterator<S> iterator() {
        return new Iterator<S>() {

            int row = 0;

            int column = 0;

            int numberOfElementsProcessed = 0;

            @Override
            public boolean hasNext() {
                if (column == 0) {
                    // Convenient for output.
                    System.out.println();
                }
                return numberOfElementsProcessed < numberOfRows * numberOfColumns;
            }

            @Override
            public S next() {
                S value = shapes[row][column]; // Cast Object to S
                if (column == numberOfColumns - 1) {
                    column = 0;
                    row++;
                } else {
                    column++;
                }

                numberOfElementsProcessed++;

                return value;
            }
        };
    }*/

    @Override
    public Iterator<S> iterator() {
        return new Iterator<S>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < numberOfColumns * numberOfRows;
            }

            @Override
            public S next() {
                int row = i / numberOfColumns;
                int column = i % numberOfColumns;
                i++;
                if (column == 0) System.out.println();

                return shapes[row][column];
            }
        };
    }

    public static abstract class Shape {

        public enum Color {
            RED,
            BLUE,
            GREEN,
            BLACK,
            WHITE
        }

        private final Color color;

        public Shape(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public abstract double getArea();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Shape shape = (Shape) o;
            return color.equals(shape.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(color);
        }

        @Override
        public String toString() {
            return "color='" + color + '\'' + '}';
        }
    }

    public static class Circle extends Shape {

        private final double radius;

        public Circle(Color color, int radius) {
            super(color);
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o) && ((Circle) o).radius == radius;
        }

        @Override
        public int hashCode() {
            return Objects.hash(radius, getColor());
        }

        @Override
        public double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public String toString() {
            return "Circle{" + "radius=" + radius + " " + super.toString();
        }
    }

    public static class Rectangle extends Shape {

        private final double LENGTH, WIDTH;

        public Rectangle(Color color, int LENGTH, int WIDTH) {
            super(color);
            this.LENGTH = LENGTH;
            this.WIDTH = WIDTH;
        }

        public double getLENGTH() {
            return LENGTH;
        }

        public double getWIDTH() {
            return WIDTH;
        }

        @Override
        public double getArea() {
            return LENGTH * WIDTH;
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o) && ((Rectangle) o).LENGTH == LENGTH && ((Rectangle) o).WIDTH == WIDTH;
        }

        @Override
        public int hashCode() {
            return Objects.hash(LENGTH, WIDTH, getColor());
        }

        @Override
        public String toString() {
            return "Rectangle{" + "a=" + LENGTH + ", b=" + WIDTH + " " + super.toString();
        }
    }

    public static void main(String[] args) {
        Board<Shape> shapeBoard = new Board<>(3, 4);
        System.out.println(shapeBoard.add(new Circle(Color.BLACK, 2), 2, 3));
        //  System.out.println(circleBoard.add(new Rectangle(Color.BLACK, 2, 6), 0, 3)); <-- Dit moet fout gaan
        System.out.println(shapeBoard.add(new Circle(Color.RED, 4), 0, 1));
        System.out.println(shapeBoard.add(new Circle(Color.WHITE, 2), 1, 0));
        System.out.println(shapeBoard.add(new Circle(Color.BLUE, 2), 1, 0));

        //  System.out.println(circleBoard.remove(2, 3));
        // System.out.println(circleBoard.remove(2,3));
        //System.out.println(circleBoard.add(new Circle("Purple-Pink", 2), 2, 3));
        //System.out.println(circleBoard.remove(2,3));

        System.out.println();
        for (Shape shape : shapeBoard) {
            System.out.printf("%-40s", shape);
        }
        System.out.println("\n-----");


    }
}
