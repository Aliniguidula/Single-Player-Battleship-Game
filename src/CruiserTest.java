// SÍ

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CruiserTest {
    static Cruiser CruiserTest;
    static Ocean ocean;

    @BeforeEach
    void init() {
        CruiserTest = new Cruiser();
        ocean = new Ocean();
    }

    @Test
    public void testCruiser(){
        assertEquals(3, CruiserTest.length);
        assertEquals(3, CruiserTest.hit.length);
    }

    @Test
    public void testGetShipType() {
        assertEquals("Cruiser", CruiserTest.getShipType());
    }

    @Test
    public void testGetLength() {
        assertEquals(3, CruiserTest.getLength());
    }

    @Test
    public void testGetBowRow() {
        CruiserTest.setBowRow(0);
        assertEquals(0, CruiserTest.getBowRow());
    }

    @Test
    public void testGetBowColumn() {
        CruiserTest.setBowColumn(1);
        assertEquals(1, CruiserTest.getBowColumn());
    }

    @Test
    public void testSetBowColumn() {
        CruiserTest.setBowColumn(1);
        assertEquals(1, CruiserTest.bowColumn);
    }

    @Test
    public void testSetBowRow() {
        CruiserTest.setBowRow(0);
        assertEquals(0, CruiserTest.bowRow);
    }

    @Test
    public void testIsHorizontal() {
        CruiserTest.setHorizontal(true);
        assertTrue(CruiserTest.isHorizontal());
    }

    @Test
    public void testSetHorizontal() {
        CruiserTest.setHorizontal(false);
        assertFalse(CruiserTest.horizontal);
    }

    @Test
    public void testPlaceShipAt() {
        CruiserTest.placeShipAt(7,0,true,ocean);
        assertEquals(7, CruiserTest.getBowRow());
        assertEquals(0, CruiserTest.getBowColumn());
        assertTrue(CruiserTest.horizontal);
    }

    @Test
    public void testOkToPlaceShipAt() {
        assertTrue(CruiserTest.okToPlaceShipAt(0, 0,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(0, 1,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(0, 2,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(0, 3,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(0, 4,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(0, 5,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(0, 6,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(0, 7,true,ocean));
        assertFalse(CruiserTest.okToPlaceShipAt(0, 8,true,ocean));
        assertFalse(CruiserTest.okToPlaceShipAt(0, 9,true,ocean));
        assertTrue(CruiserTest.okToPlaceShipAt(7, 0,false,ocean));
        assertFalse(CruiserTest.okToPlaceShipAt(8, 0,false,ocean));
        assertFalse(CruiserTest.okToPlaceShipAt(8, 9,false,ocean));

        CruiserTest.placeShipAt(3, 5, false, ocean);
        assertTrue(CruiserTest.okToPlaceShipAt(3, 3, false, ocean));
        assertFalse(CruiserTest.okToPlaceShipAt(2, 4, false, ocean));
    }

    @Test
    public void testShootAt() {
        CruiserTest.placeShipAt(7,0,true,ocean);
        assertFalse(CruiserTest.shootAt(0,0));
        assertTrue(CruiserTest.shootAt(7,0));
        assertTrue(CruiserTest.shootAt(7,1));
        assertTrue(CruiserTest.shootAt(7,2));
        assertFalse(CruiserTest.shootAt(7,3));
        assertFalse(CruiserTest.shootAt(7,2));
    }

    @Test
    public void testIsSunk() {
        CruiserTest.placeShipAt(7,0,true,ocean);
        CruiserTest.shootAt(7,0);
        assertFalse(CruiserTest.isSunk());
        CruiserTest.shootAt(7,1);
        assertFalse(CruiserTest.isSunk());
        CruiserTest.shootAt(7,2);
        assertTrue(CruiserTest.isSunk());
    }

    @Test
    public void testToString(){
        CruiserTest.placeShipAt(7,0,true,ocean);

        CruiserTest.shootAt(7,0);
        assertEquals("S", CruiserTest.toString());
        CruiserTest.shootAt(7,1);
        assertEquals("S", CruiserTest.toString());
        CruiserTest.shootAt(7,2);
        assertEquals("x", CruiserTest.toString());
    }
}