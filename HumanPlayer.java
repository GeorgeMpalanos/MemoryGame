


import java.util.Scanner;


class HumanPlayer
{
	private String humanPlayerName;
	private int humanPlayerPoints = 0;
	
	public HumanPlayer(String humanPlayerName)
	{
		this.humanPlayerName = humanPlayerName;
	}
	
	public void play(Board game)
	{
		Scanner input = new Scanner(System.in);
		System.out.print(humanPlayerName+", select two positions to open: ");
		int firstPosition = input.nextInt();
		int secondPosition = input.nextInt();
		while(firstPosition == secondPosition || !game.containsCard(firstPosition) || !game.containsCard(secondPosition) )
		{
			System.out.println("Selection not valid");
			System.out.println(humanPlayerName+", select two positions to open: ");
			firstPosition = input.nextInt();
			secondPosition = input.nextInt();
		}
			
		if(game.openPositions(firstPosition,secondPosition))
		{
			this.humanPlayerPoints ++;
		}
		
	}
	
	public int getHumanPlayerPoints()
	{
		return humanPlayerPoints;
	}
	
	public String toString()
	{
		return humanPlayerName;
	}
	
}
