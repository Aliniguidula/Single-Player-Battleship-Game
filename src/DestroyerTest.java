// SÍ

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestroyerTest {
    static Destroyer DestroyerTest;
    static Ocean ocean;

    @BeforeEach
    void init() {
        DestroyerTest = new Destroyer();
        ocean = new Ocean();
    }

    @Test
    public void testDestroyer(){
        assertEquals(2, DestroyerTest.length);
        assertEquals(2, DestroyerTest.hit.length);
    }

    @Test
    public void testGetShipType() {
        assertEquals("Destroyer", DestroyerTest.getShipType());
    }

    @Test
    public void testGetLength() {
        assertEquals(2, DestroyerTest.getLength());
    }

    @Test
    public void testGetBowRow() {
        DestroyerTest.setBowRow(0);
        assertEquals(0, DestroyerTest.getBowRow());
    }

    @Test
    public void testGetBowColumn() {
        DestroyerTest.setBowColumn(1);
        assertEquals(1, DestroyerTest.getBowColumn());
    }

    @Test
    public void testSetBowColumn() {
        DestroyerTest.setBowColumn(1);
        assertEquals(1, DestroyerTest.bowColumn);
    }

    @Test
    public void testSetBowRow() {
        DestroyerTest.setBowRow(0);
        assertEquals(0, DestroyerTest.bowRow);
    }

    @Test
    public void testIsHorizontal() {
        DestroyerTest.setHorizontal(true);
        assertTrue(DestroyerTest.isHorizontal());
    }

    @Test
    public void testSetHorizontal() {
        DestroyerTest.setHorizontal(false);
        assertFalse(DestroyerTest.horizontal);
    }

    @Test
    public void testPlaceShipAt() {
        DestroyerTest.placeShipAt(7,0,true,ocean);
        assertEquals(7, DestroyerTest.getBowRow());
        assertEquals(0, DestroyerTest.getBowColumn());
        assertTrue(DestroyerTest.horizontal);
    }

    @Test
    public void testOkToPlaceShipAt() {
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 0,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 1,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 2,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 3,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 4,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 5,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 6,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 7,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(0, 8,true,ocean));
        assertFalse(DestroyerTest.okToPlaceShipAt(0, 9,true,ocean));
        assertTrue(DestroyerTest.okToPlaceShipAt(8, 0,false,ocean));
        assertFalse(DestroyerTest.okToPlaceShipAt(9, 0,false,ocean));
        assertFalse(DestroyerTest.okToPlaceShipAt(9, 9,false,ocean));

        DestroyerTest.placeShipAt(3, 5, false, ocean);
        assertTrue(DestroyerTest.okToPlaceShipAt(3, 3, false, ocean));
        assertFalse(DestroyerTest.okToPlaceShipAt(2, 4, false, ocean));
    }

    @Test
    public void testShootAt() {
        DestroyerTest.placeShipAt(7,0,true,ocean);
        assertFalse(DestroyerTest.shootAt(0,0));
        assertTrue(DestroyerTest.shootAt(7,0));
        assertTrue(DestroyerTest.shootAt(7,1));
        assertFalse(DestroyerTest.shootAt(7,1));
    }

    @Test
    public void testIsSunk() {
        DestroyerTest.placeShipAt(7,0,true,ocean);
        DestroyerTest.shootAt(7,0);
        assertFalse(DestroyerTest.isSunk());
        DestroyerTest.shootAt(7,1);
        assertTrue(DestroyerTest.isSunk());
    }

    @Test
    public void testToString(){
        DestroyerTest.placeShipAt(7,0,true,ocean);

        DestroyerTest.shootAt(7,0);
        assertEquals("S", DestroyerTest.toString());
        DestroyerTest.shootAt(7,1);
        assertEquals("x", DestroyerTest.toString());
    }
}