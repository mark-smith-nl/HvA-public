package hva.ads.practicum.week1.lsmeets;

import java.util.Iterator;

public class Board<S extends Shape> implements Iterable<S> {

    private int size = 0;
    private Shape[][] shapes;

    public Board(int size) {
        this.size = size;
        shapes = new Shape[size][size];
    }

    public boolean add(S shape, int x, int y) {
        if (x >= size || x < 0 || y >= size || y < 0) {
            return false;
        } else {
            shapes[x][y] = shape;
            return true;
        }
    }

    public Shape remove(int x, int y) {
        if (x >= size || x < 0 || y >= size || y < 0) {
            return null;
        }
        Shape removed = shapes[x][y];
        shapes[x][y] = null;
        return removed;
    }

    @Override
    public Iterator<S> iterator() {
        return new Iterator<S>() {

            private int nextX = 0;
            private int nextY = 0;

            @Override
            public boolean hasNext() {
                if (nextX == 0 && nextY == 0) {
                    return true;
                }
                if (nextY >= Board.this.size) {
                    return false;
                }
                return true;
            }

            @Override
            public S next() {
                if (hasNext()) {
                    S shape = (S) Board.this.shapes[nextX][nextY];
                    if (nextX < Board.this.size - 1) {
                        nextX++;
                    } else {
                        nextX = 0;
                        nextY++;
                    }
                    return shape;
                }
                return null;
            }
        };
    }

    public Shape[][] getGrid() {
        return shapes;
    }
}
