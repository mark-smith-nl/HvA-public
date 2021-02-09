package hva.ads.practicum.week1.lsmeets;

import hva.ads.practicum.week1.lsmeets.Board;
import hva.ads.practicum.week1.lsmeets.Circle;
import hva.ads.practicum.week1.lsmeets.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {
    Circle circle1, circle2, circle3;
    Rectangle rectangle1, rectangle2, rectangle3;
    Board<Rectangle> rectangleBoard;
    Board<Circle> circleBoard;
    int boardSize = 9;
    @BeforeEach
    void setUp() {
        circle1 = new Circle(Color.BLACK,10);
        circle2 = new Circle(Color.BLACK,10);
        circle3 = new Circle(Color.BLUE,10);
        rectangle1 = new Rectangle(Color.BLUE, 4,5);
        rectangle2 = new Rectangle(Color.BLUE, 4,5);
        rectangle3 = new Rectangle(Color.BLUE,2,10);

        rectangleBoard = new Board<>(boardSize);
        for (int i =0; i <=boardSize; i++ ){
            for (int j = 0; j<= boardSize; j++) {
                rectangleBoard.add(new Rectangle(Color.BLUE, i+1, j+1), i, j);
            }
        }
        circleBoard = new Board<>(1);
    }

    @org.junit.jupiter.api.Test
    void getArea() {
        Assertions.assertEquals(314.1592653589793, circle1.getArea());
        Assertions.assertEquals(20, rectangle1.getArea());
    }

    @org.junit.jupiter.api.Test
    void equals() {
        assertEquals(true, circle1.equals(circle2));
        assertEquals(true, rectangle1.equals(rectangle2));
        assertEquals(false,circle1.equals(circle3));
        assertEquals(false,rectangle2.equals(rectangle3));
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        assertEquals(true, circle1.hashCode() ==circle2.hashCode());
        assertEquals(true, rectangle1.hashCode()==rectangle2.hashCode());
        assertEquals(false,circle1.hashCode()==circle3.hashCode());
    }

    @Test
    void testIterator() {
        int count = 0;
        Iterator iterator = rectangleBoard.iterator();
        while (iterator.hasNext()){
            count++;
            System.out.println(iterator.next());

        }
        assertEquals(boardSize*boardSize,count);

    }
    @Test
    void testBoardSize(){
        //a Board of size 1 (a grid of 1 by 1) can only add a Shape at position 0, 0
        //when a shape is added it returns true
        assertEquals(true,circleBoard.add(circle1,0,0));
        //this should not crash but just return false
        assertEquals(false, circleBoard.add(circle2,1,1));

    }
    @Test
    void testIteratorForBoardSizeOne() {
        int count = 0;
        Iterator iterator = circleBoard.iterator();
        while (iterator.hasNext()){
            count++;
            System.out.println(iterator.next());

        }
        assertEquals(1,count);

    }
    @Test
    void testInsertRemove(){
        assertEquals(true,rectangleBoard.add(rectangle1,3,4));
        assertEquals(rectangle1,rectangleBoard.remove(3,4));


    }
    @Test
    void testAddOutOfBounds(){
        assertEquals(true, circleBoard.add(circle2,0,0));
        assertEquals(false, circleBoard.add(circle2,-1,-1));
        assertEquals(false, circleBoard.add(circle2,1,1));
    }
    @Test
    void testGetGrid(){
        //The getGrid method returns a 2D array of Shape
        Shape shapes [][] = rectangleBoard.getGrid();
        for(int x = 0; x<shapes.length; x++){
            for(int y = 0; y<shapes.length;y++){
                Shape s = shapes[x][y];
                assertEquals(true, s instanceof Shape);
            }

        }

    }
}
