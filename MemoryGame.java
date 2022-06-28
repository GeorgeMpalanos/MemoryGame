//5054 GEORGIOS MPALANOS

import java.util.Scanner;

class MemoryGame
{
	public static void main(String[] args)
	{
		//Converts and stores the sizeOfBoard from the command Line argument.
		int sizeOfBoard = Integer.valueOf(args[0]);
		
		Scanner input = new Scanner(System.in);
		System.out.print("Player vs Computer(press 1)"+"\n"+"Player vs Player(press 0)");
		System.out.println();
		int choice = input.nextInt();
		
		Board myBoard = new Board(sizeOfBoard);
		HumanPlayer humanPlayerOne = new HumanPlayer("George");
		
		//Pc vs human
		if(choice == 1)
		{
			ComputerPlayer pc = new ComputerPlayer("Hal",sizeOfBoard);
			while(!myBoard.allPairsFound())
			{
				humanPlayerOne.play(myBoard);
				pc.play(myBoard);
				System.out.println(humanPlayerOne+" points: "+humanPlayerOne.getHumanPlayerPoints()+"   "+pc+" points: "+
				pc.getPcPoints());
			}
			
			if(humanPlayerOne.getHumanPlayerPoints() > pc.getPcPoints())
			{
				System.out.println(humanPlayerOne + " won the game.");
			}else if(humanPlayerOne.getHumanPlayerPoints() < pc.getPcPoints()){
				System.out.println(pc + " won the game.");
			}else{
				System.out.println("It's a tie");
			}
			
		//Human vs human
		}else if(choice == 0){
			HumanPlayer humanPlayerTwo = new HumanPlayer("Bill");
			boolean printNeeded = false;
			while(!myBoard.allPairsFound())
			{
				humanPlayerOne.play(myBoard);
				if(myBoard.allPairsFound())
				{
					printNeeded = true;
					break;
				}
				humanPlayerTwo.play(myBoard);
				System.out.println(humanPlayerOne+" points: "+humanPlayerOne.getHumanPlayerPoints()+"   "+humanPlayerTwo
				+" points: "+humanPlayerTwo.getHumanPlayerPoints());
			}
			
			//If the break happens.
			if(printNeeded)
			{
				System.out.println(humanPlayerOne+" points: "+humanPlayerOne.getHumanPlayerPoints()+"   "+humanPlayerTwo
				+" points: "+humanPlayerTwo.getHumanPlayerPoints());
			}
			
			
			if(humanPlayerOne.getHumanPlayerPoints() > humanPlayerTwo.getHumanPlayerPoints())
			{
				System.out.println(humanPlayerOne + " won the game.");
			}else if(humanPlayerOne.getHumanPlayerPoints() < humanPlayerTwo.getHumanPlayerPoints()){
				System.out.println(humanPlayerTwo + " won the game.");
			}else{
				System.out.println("It's a tie");
			}
			
		}
	}
}