import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * This class manages the game state by keeping track of what entity is
 * contained in each position on the game board.
 *
 * @author Alicia
 *
 */
public class Ocean implements OceanInterface {

	/**
	 * A 10x10 2D array of Ships, which can be used to quickly determine which ship
	 * is in any given location.
	 */
	protected Ship[][] ships;

	/**
	 * The total number of shots fired by the user
	 */
	protected int shotsFired;

	/**
	 * The number of times a shot hit a ship. If the user shoots the same part of a
	 * ship more than once, every hit is counted, even though the additional "hits"
	 * don't do the user any good.
	 */
	protected int hitCount;

	/**
	 * The number of ships totally sunk.
	 *
	 */
	protected int shipsSunk;

	protected HashSet<int[]> coordinatesHit;

	/**
	 * Creates an "empty" ocean, filling every space in the ships
	 * array with EmptySea objects. Should also initialize the other
	 * instance variables appropriately.
	 */
	public Ocean() {
		this.ships =  new Ship[10][10];
		for (int row = 0; row < 10; row++){
			for (int col = 0; col < 10; col++){
				this.ships[row][col] = new EmptySea();
			}
		}
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		this.coordinatesHit = new HashSet<>();
	}

	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 * Larger ships must be placed before smaller ones to avoid cases
	 * where it may be impossible to place the larger ships.
	 *
	 * @see java.util.Random
	 */
	public void placeAllShipsRandomly() {
		Random random = new Random();
		Ship[] allShips = new Ship[]{
				new Battleship(),
				new Cruiser(), new Cruiser(),
				new Destroyer(), new Destroyer(), new Destroyer(),
				new Submarine(), new Submarine(), new Submarine(), new Submarine()
		};
		for (Ship ship: allShips){
			int row, col;
			boolean horizontal;
			do {
				row = random.nextInt(10);
				col = random.nextInt(10);
				horizontal = random.nextBoolean();
			} while (!ship.okToPlaceShipAt(row, col, horizontal, this));
			ship.placeShipAt(row, col, horizontal, this);
		}
	}

	/**
	 * Checks if this coordinate is not empty; that is, if this
	 * coordinate does not contain an EmptySea reference.
	 *
	 * @param row the row (0 to 9) in which to check for a floating ship
	 * @param column the column (0 to 9) in which to check for a floating ship
	 * @return {@literal true} if the given location contains a ship, and
	 *         {@literal false} otherwise.
	 */
	public boolean isOccupied(int row, int column) {
		return !(this.ships[row][column].getShipType()).equals("empty");
	}

	/**
	 * Fires a shot at this coordinate. This will update the number of shots that have been
	 * fired (and potentially the number of hits, as well). If a location contains
	 * a real, not sunk ship, this method should return {@literal true}
	 * every time the user shoots at that location. If the ship has been sunk,
	 * additional shots at this location should return {@literal false}.
	 *
	 * @param row the row (0 to 9) in which to shoot
	 * @param column the column (0 to 9) in which to shoot
	 * @return {@literal true} if the given location contains an afloat ship (not an
	 *         EmptySea), {@literal false} if it does not.
	 */
	public boolean shootAt(int row, int column) {
		this.shotsFired++;
		coordinatesHit.add(new int[]{row, column});
		Ship ship = this.ships[row][column];
		if (isOccupied(row, column) && !ship.isSunk()){
			this.hitCount++;
			if (!ship.shootAt(row, column)){
				this.shipsSunk++;
			}
			return true;
		}
		return false;
	}

	/**
	 * @return the number of shots fired in this game.
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * @return the number of hits recorded in this game.
	 */
	public int getHitCount() {
		return this.hitCount;
	}

	/**
	 * @return the number of ships sunk in this game.
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * @return the HashSet of coordinates have been hit
	 */
	public HashSet<int[]> getCoordinatesHit() {
		return this.coordinatesHit;
	}

	/**
	 * @return {@literal true} if all ships have been sunk,
	 *         otherwise {@literal false}.
	 */
	public boolean isGameOver() {
		if (getShipsSunk() == 10){
			return true;
		}
		return false;
	}

	/**
	 * Provides access to the grid of ships in this Ocean. The methods in the Ship
	 * class that take an Ocean parameter must be able to read and even modify the
	 * contents of this array. While it is generally undesirable to allow methods in
	 * one class to directly access instance variables in another class, in this
	 * case there is no clear and elegant alternatives.
	 *
	 * @return the 10x10 array of ships.
	 */
	public Ship[][] getShipArray() {
		return this.ships;
	}

	/**
	 * Prints the ocean. To aid the user, row numbers should be displayed along the left
	 * edge of the array, and column numbers should be displayed along the top. Numbers
	 * should be 0 to 9, not 1 to 10. The top left corner square should be 0, 0.
	 *
	 * Use 'S' to indicate a location that you have fired upon and hit a (real) ship
	 * '-' to indicate a location that you have fired upon and found nothing there
	 * 'x' to indicate a location containing a sunken ship
	 * '.' (a period) to indicate a location that you have never fired upon.
	 * This is the only method in Ocean that has any printing capability, and it should never
	 * be called from within the Ocean class except for the purposes of debugging.
	 *
	 * This is the only method in Ocean that has any printing capability, and it
	 * should never be called from within the Ocean class except for the purposes of
	 * debugging.
	 *
	 */
	public void print() {
		System.out.print("    ");
		for (int col = 0; col < ships[0].length; col++) {
			System.out.printf("%4d", col);
		}
		System.out.println();

		for (int row = 0; row < ships.length; row++) {
			System.out.printf("%4d", row);
			for (int col = 0; col < ships[row].length; col++) {
				char displayChar = getDisplayChar(row, col);
				System.out.printf("%4s", displayChar);
			}
			System.out.println();
		}

		printStats();
	}

	private char getDisplayChar(int row, int col) {
		boolean hasHit = coordinatesHit.stream().anyMatch(coords -> coords[0] == row && coords[1] == col);
		if (hasHit) {
			return isOccupied(row, col) ? (isSunk(row, col) ? 'x' : 'S') : '-';
		}
		return '.';
	}

	private boolean isSunk(int row, int col) {
		return ships[row][col].isSunk();
	}

	private void printStats() {
		System.out.printf("    Ships sunk ----------------------- %d/10 \n\n", getShipsSunk());
		System.out.printf("Shots fired: %d\n", getShotsFired());
		System.out.printf("Hit count: %d\n", getHitCount());
	}

}