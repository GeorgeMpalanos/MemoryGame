

class ComputerPlayer
{
	private String pcName;
	private int[] myArray;
	private int pcPoints = 0;
	
	public ComputerPlayer(String name, int pairs)
	{
		this.pcName = name;
		myArray = new int[pairs];
		for(int i=0; i< pairs; i++)
		{
			myArray[i] = -1;
		}
		
		/*The positions of myArray are meant to be the cards.
		The value on each position of myArray is supposed to be
		-1 if it's the card's first appearance or a number that 
		gives the card's last position*/
		
	}
	
	public void play(Board game)
	{
		if(!game.allPairsFound()){
			int firstRandomPosition = game.getRandomPosition();
			int firstRandomCard = game.getCard(firstRandomPosition);
			while(firstRandomPosition == myArray[firstRandomCard])
			{
				firstRandomPosition = game.getRandomPosition();
				firstRandomCard = game.getCard(firstRandomPosition);
			}
			if(myArray[firstRandomCard] != -1)
			{
				int secondPosition = myArray[firstRandomCard];
				System.out.println("Computer player "+pcName+" selected positions :"+firstRandomPosition
			+" "+secondPosition);
				if(game.openPositions(secondPosition,firstRandomPosition))
				{
					this.pcPoints ++;
				}
			}else if(myArray[firstRandomCard] == -1){
				int secondPosition = game.getRandomPosition(firstRandomPosition);
				int secondRandomCard = game.getCard(secondPosition);
				System.out.println("Computer player "+pcName+" selected positions :"+firstRandomPosition
			+" "+secondPosition);
				if(game.openPositions(secondPosition,firstRandomPosition))
				{
					this.pcPoints ++;
				}else{
					myArray[firstRandomCard] = firstRandomPosition;
					myArray[secondRandomCard] = secondPosition;
				}
			}
		}
		
	}
	
	public int getPcPoints()
	{
		return pcPoints;
	}
	
	public String toString()
	{
		return pcName;
	}
	
	public static void main(String args[])
	{
		Board game = new Board(3);
		ComputerPlayer myPc = new ComputerPlayer("Hal",3);
		while(!game.allPairsFound())
		{
			myPc.play(game);
		}
	}
}
