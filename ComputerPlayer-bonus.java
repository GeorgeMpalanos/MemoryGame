//5054 GEORGIOS MPALANOS

class ComputerPlayer
{
	private String pcName;
	private int[] myArray;
	private int[] mySecondArray;
	private int pcPoints = 0;
	
	public ComputerPlayer(String name, int pairs)
	{
		this.pcName = name;
		myArray = new int[pairs];
		mySecondArray = new int[pairs];
		for(int i=0; i< pairs; i++)
		{
			myArray[i] = -1;
			mySecondArray[i] = -1;
		}
		/* myArray stores card's first position and mySecondArray stores
		card's second position*/
		
	}
	
	public void play(Board game)
	{
		if(!game.allPairsFound())
		{
			boolean goOn = true;
			for(int i=0; i<myArray.length; i++)
			{
				if(mySecondArray[i] != -1 && game.containsCard(mySecondArray[i]))
				{
					System.out.println("Computer player "+pcName+" selected positions :"+myArray[i]
					+" "+mySecondArray[i]);
					game.openPositions(myArray[i],mySecondArray[i]);
					this.pcPoints ++;
					
					goOn = false;
				}
			}
			
			
			if(goOn)
			{
				int firstRandomPosition = game.getRandomPosition();
				int firstRandomCard = game.getCard(firstRandomPosition);
				while(firstRandomPosition == myArray[firstRandomCard])
				{
					firstRandomPosition = game.getRandomPosition();
					firstRandomCard = game.getCard(firstRandomPosition);
				}
				if(myArray[firstRandomCard] != -1 && mySecondArray[firstRandomCard] == -1)
				{
					mySecondArray[firstRandomCard] = firstRandomPosition;
					
					int secondPosition = game.getRandomPosition(firstRandomPosition);
					int secondRandomCard = game.getCard(secondPosition);
					System.out.println("Computer player "+pcName+" selected positions :"+firstRandomPosition
					+" "+secondPosition);
					if(game.openPositions(secondPosition,firstRandomPosition))
					{
						this.pcPoints ++;
					}else{myArray[secondRandomCard] = secondPosition;}
				}
				if(myArray[firstRandomCard] == -1){
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