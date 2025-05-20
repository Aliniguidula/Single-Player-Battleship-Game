//SÍ

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleshipTest {
    static Battleship battleshipTest;
    static Ocean ocean;

    @BeforeEach
    void init() {
        battleshipTest = new Battleship();
        ocean = new Ocean();
    }

    @Test
    public void testBattleship(){
        assertEquals(4, battleshipTest.length);
        assertEquals(4, battleshipTest.hit.length);
    }

    @Test
    public void testGetShipType() {
        assertEquals("Battleship", battleshipTest.getShipType());
    }

    @Test
    public void testGetLength() {
        assertEquals(4, battleshipTest.getLength());
    }

    @Test
    public void testGetBowRow() {
        battleshipTest.setBowRow(0);
        assertEquals(0, battleshipTest.getBowRow());
    }

    @Test
    public void testGetBowColumn() {
        battleshipTest.setBowColumn(1);
        assertEquals(1, battleshipTest.getBowColumn());
    }

    @Test
    public void testSetBowColumn() {
        battleshipTest.setBowColumn(1);
        assertEquals(1, battleshipTest.bowColumn);
    }

    @Test
    public void testSetBowRow() {
        battleshipTest.setBowRow(0);
        assertEquals(0, battleshipTest.bowRow);
    }

    @Test
    public void testIsHorizontal() {
        battleshipTest.setHorizontal(true);
        assertTrue(battleshipTest.isHorizontal());
    }

    @Test
    public void testSetHorizontal() {
        battleshipTest.setHorizontal(false);
        assertFalse(battleshipTest.horizontal);
    }

    @Test
    public void testPlaceShipAt() {
        battleshipTest.placeShipAt(7,0,true,ocean);
        assertEquals(7, battleshipTest.getBowRow());
        assertEquals(0, battleshipTest.getBowColumn());
        assertTrue(battleshipTest.horizontal);
    }

    @Test
    public void testOkToPlaceShipAt() {
        assertTrue(battleshipTest.okToPlaceShipAt(0, 0,true,ocean));
        assertTrue(battleshipTest.okToPlaceShipAt(0, 1,true,ocean));
        assertTrue(battleshipTest.okToPlaceShipAt(0, 2,true,ocean));
        assertTrue(battleshipTest.okToPlaceShipAt(0, 3,true,ocean));
        assertTrue(battleshipTest.okToPlaceShipAt(0, 4,true,ocean));
        assertTrue(battleshipTest.okToPlaceShipAt(0, 5,true,ocean));
        assertTrue(battleshipTest.okToPlaceShipAt(0, 6,true,ocean));
        assertFalse(battleshipTest.okToPlaceShipAt(0, 7,true,ocean));
        assertFalse(battleshipTest.okToPlaceShipAt(0, 8,true,ocean));
        assertFalse(battleshipTest.okToPlaceShipAt(0, 9,true,ocean));
        assertTrue(battleshipTest.okToPlaceShipAt(6, 0,false,ocean));
        assertFalse(battleshipTest.okToPlaceShipAt(7, 0,false,ocean));
        assertFalse(battleshipTest.okToPlaceShipAt(7, 9,false,ocean));

        battleshipTest.placeShipAt(3, 5, false, ocean);
        assertTrue(battleshipTest.okToPlaceShipAt(3, 3, false, ocean));
        assertFalse(battleshipTest.okToPlaceShipAt(2, 4, false, ocean));
    }

    @Test
    public void testShootAt() {
        battleshipTest.placeShipAt(7,0,true,ocean);
        assertFalse(battleshipTest.shootAt(0,0));
        assertTrue(battleshipTest.shootAt(7,0));
        assertTrue(battleshipTest.shootAt(7,1));
        assertTrue(battleshipTest.shootAt(7,2));
        assertTrue(battleshipTest.shootAt(7,3));
        assertFalse(battleshipTest.shootAt(7,3));
    }

    @Test
    public void testIsSunk() {
        battleshipTest.placeShipAt(7,0,true,ocean);
        battleshipTest.shootAt(7,0);
        assertFalse(battleshipTest.isSunk());
        battleshipTest.shootAt(7,1);
        assertFalse(battleshipTest.isSunk());
        battleshipTest.shootAt(7,2);
        assertFalse(battleshipTest.isSunk());
        battleshipTest.shootAt(7,3);
        assertTrue(battleshipTest.isSunk());
    }

    @Test
    public void testToString(){
        battleshipTest.placeShipAt(7,0,true,ocean);

        battleshipTest.shootAt(7,0);
        assertEquals("S", battleshipTest.toString());
        battleshipTest.shootAt(7,1);
        assertEquals("S", battleshipTest.toString());
        battleshipTest.shootAt(7,2);
        assertEquals("S", battleshipTest.toString());
        battleshipTest.shootAt(7,3);
        assertEquals("x", battleshipTest.toString());
    }
}