package ticTacToe;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	public static final int NO_PLAYER = 0;
	public static final int PLAYER_X = 1; //COMPUTE PLAYER
	public static final int PLAYER_O = 2; //HUMAN PLAYER
	private int[][] board = new int[3][3];
	public Point computeMove;
	
	public boolean isGameOver() {
		return hasPlayerWon(PLAYER_X) || hasPlayerWon(PLAYER_O) || getAvailableCells().isEmpty(); 
	}

	public List<Point> getAvailableCells() {
		List<Point> availableCells = new ArrayList<Point>();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(board[i][j] == NO_PLAYER) {
					availableCells.add(new Point(i, j));
				}
			}
		}
		return availableCells;
	}

	public boolean hasPlayerWon(int player) {
		
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player
				||
				board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == player) {
			return true;
		}
		
		for(int i=0; i<3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == player
					||
					board[0][i] == board[1][i] && board[2][i] == board[1][i] && board[2][i] == player) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean placeMove(Point point, int player) {
		if(board[point.getX()][point.getY()] == NO_PLAYER) {
			board[point.getX()][point.getY()] = player;
			return true;
		}
		return false;
	}
	
	public void displayBoard() {
		
		System.out.println("0 1 2");
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				String value = "_";
				
				if(board[i][j] == PLAYER_X) {
					value = "X";
				}
				if(board[i][j] == PLAYER_O) {
					value = "O";
				}
				
				System.out.print(value + " ");
			}
			
			System.out.println(i);
		}
		
		System.out.println();
	}
	
	/*
	 * Two parameters:
	 * First. the depth of exploration
	 * Second. the turn of the player for which we evaluate the state (computer has X, human has O)
	 */
	public int minmax(int depth, int turn) {
		
		if(hasPlayerWon(PLAYER_X)) {
			return 1;
		}
		if(hasPlayerWon(PLAYER_O)) {
			return -1;
		}
		
		List<Point> availableCells = getAvailableCells();
		
		if(availableCells.isEmpty()) {
			return 0; //game is over
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		/*
		 * Iterate in the list of available cells, to evaluate each possibility to play
		 * We maximise for X that is the computer, and minimise for O that is human player
		 */
		for (int i=0; i<availableCells.size(); i++) {
			Point point = availableCells.get(i);
			
			if(turn == PLAYER_X) {
				placeMove(point, PLAYER_X);
				
				int currentScore = minmax(depth + 1, PLAYER_O);
				max = Math.max(currentScore, max);
				
				if(depth == 0) {
					System.out.println("Compute score for position: " + point + " = " + currentScore);
				}
				
				if(currentScore >= 0) {
					if(depth == 0) {
						computeMove = point;
					}
				}
				
				if(currentScore == 1) {
					board[point.getX()][point.getY()] = NO_PLAYER;
					break;
				}
				
				if(i == availableCells.size() -1 && max < 0) {
					if(depth == 0) {
						computeMove = point;
					}
				}
			} else if(turn == PLAYER_O) {
				placeMove(point, PLAYER_O);
				
				int currentState = minmax(depth + 1, PLAYER_X);
				
				min = Math.min(currentState,  min);
				
				if(min == -1) {
					board[point.getX()][point.getY()] = NO_PLAYER;
					break;
				}
			}
			
			board[point.getX()][point.getY()] = NO_PLAYER;
		}
		
		return turn == PLAYER_X ? max : min;
	}

}
