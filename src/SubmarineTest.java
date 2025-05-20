// SÍ

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubmarineTest {
    static Submarine SubmarineTest;
    static Ocean ocean;

    @BeforeEach
    void init() {
        SubmarineTest = new Submarine();
        ocean = new Ocean();
    }

    @Test
    public void testSubmarine(){
        assertEquals(1, SubmarineTest.length);
        assertEquals(1, SubmarineTest.hit.length);
    }

    @Test
    public void testGetShipType() {
        assertEquals("Submarine", SubmarineTest.getShipType());
    }

    @Test
    public void testGetLength() {
        assertEquals(1, SubmarineTest.getLength());
    }

    @Test
    public void testGetBowRow() {
        SubmarineTest.setBowRow(0);
        assertEquals(0, SubmarineTest.getBowRow());
    }

    @Test
    public void testGetBowColumn() {
        SubmarineTest.setBowColumn(1);
        assertEquals(1, SubmarineTest.getBowColumn());
    }

    @Test
    public void testSetBowColumn() {
        SubmarineTest.setBowColumn(1);
        assertEquals(1, SubmarineTest.bowColumn);
    }

    @Test
    public void testSetBowRow() {
        SubmarineTest.setBowRow(0);
        assertEquals(0, SubmarineTest.bowRow);
    }

    @Test
    public void testIsHorizontal() {
        SubmarineTest.setHorizontal(true);
        assertTrue(SubmarineTest.isHorizontal());
    }

    @Test
    public void testSetHorizontal() {
        SubmarineTest.setHorizontal(false);
        assertFalse(SubmarineTest.horizontal);
    }

    @Test
    public void testPlaceShipAt() {
        SubmarineTest.placeShipAt(7,0,true,ocean);
        assertEquals(7, SubmarineTest.getBowRow());
        assertEquals(0, SubmarineTest.getBowColumn());
        assertTrue(SubmarineTest.horizontal);
    }

    @Test
    public void testOkToPlaceShipAt() {
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 0,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 1,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 2,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 3,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 4,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 5,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 6,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 7,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 8,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(0, 9,true,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(9, 0,false,ocean));
        assertTrue(SubmarineTest.okToPlaceShipAt(9, 9,false,ocean));

        SubmarineTest.placeShipAt(3, 5, false, ocean);
        assertTrue(SubmarineTest.okToPlaceShipAt(3, 3, false, ocean));
        assertFalse(SubmarineTest.okToPlaceShipAt(2, 4, false, ocean));
    }


    @Test
    public void testShootAt() {
        SubmarineTest.placeShipAt(7, 0, true, ocean);
        assertFalse(SubmarineTest.shootAt(0, 0), "Shooting at (0, 0) should miss.");
        assertTrue(SubmarineTest.shootAt(7, 0), "Shooting at (7, 0) should hit.");
        assertFalse(SubmarineTest.shootAt(7, 0), "Shooting at (7, 0) again should miss.");
        assertFalse(SubmarineTest.shootAt(8, 0), "Shooting at (8, 0) should miss (assuming no ship there).");
    }

    @Test
    public void testIsSunk() {
        SubmarineTest.placeShipAt(7,0,true,ocean);
        assertFalse(SubmarineTest.isSunk());
        SubmarineTest.shootAt(7,0);
        assertTrue(SubmarineTest.isSunk());
    }

    @Test
    public void testToString(){
        SubmarineTest.placeShipAt(7,0,true,ocean);
        assertEquals("S", SubmarineTest.toString());
        SubmarineTest.shootAt(7,0);
        assertEquals("x", SubmarineTest.toString());
    }
}