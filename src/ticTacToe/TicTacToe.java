package ticTacToe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	public static final Random RANDOM = new Random();

	public static void main(String[] args) {
		
		Board b = new Board();
		Scanner scanner = new Scanner(System.in);
		b.displayBoard();
		System.out.println("Select who have to start:\n1, Computer (X) / 2. User (O) : ");
		
		int choice = scanner.nextInt();
		
		if( choice != 1 && choice  != 2) {
			System.out.println("Wrong choise: terminated !");
			scanner.close();
			return;
		}
		
		if(choice == Board.PLAYER_X) {
			Point p = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3));
			b.placeMove(p, Board.PLAYER_X);
			b.displayBoard();
		}
		
		while(!b.isGameOver()) {
			boolean moveOk = true;
			
			do{
				if(!moveOk) {
					System.out.println("Cell already filled !");
				}
				
				System.out.println("Your move : ");
				Point userMove = new Point(scanner.nextInt(), scanner.nextInt());
				moveOk = b.placeMove(userMove, Board.PLAYER_O);
			} while(!moveOk);
			
			b.displayBoard();
			
			if(b.isGameOver()) {
				break;
			}
			
			b.minmax(0, Board.PLAYER_X);
			System.out.println("Computer choose position : " + b.computeMove);
			
			b.placeMove(b.computeMove, Board.PLAYER_X);
			b.displayBoard();
			
		}
		
		if(b.hasPlayerWon(Board.PLAYER_X)) {
			System.out.println("You lost !");
		} else if(b.hasPlayerWon(Board.PLAYER_O)) {
			System.out.println("You won !");
		} else
			System.out.println("Draw !");
		
		scanner.close();

	}
	

}
