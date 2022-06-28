//5054 GEORGIOS MPALANOS


import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class Board
{
	private int[] arrayCards;
	private int numberOfPairs;
	private int cardsLeft;
	private int arrayCardSize;
	private HashMap<Integer,Integer> positionOfCards;
	private HashMap<Integer,Boolean> stateOfCards;
	private Random randomNumberGenerator = new Random();
	
	public Board(int pairs)
	{
		numberOfPairs = pairs;
		cardsLeft = pairs*2;
		arrayCardSize = pairs*2;
		
		arrayCards = new int[arrayCardSize];
		
		arrayCards = doArray(numberOfPairs);
		arrayRandomizer(arrayCards);
		
		//keys are the positions on the table and values are the cards
		positionOfCards = positionAssignedToCard(arrayCards);
		//keys are the cards and values true/false , depending on if they are on the table or not.
		stateOfCards = setStateOfCards(arrayCards);
	
		
	}

	/* Returns an Array that is made up from a list */
	private int[] doArray(int numberOfPairs)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<numberOfPairs; i++)
		{
			list.add(i);
			list.add(i);
		}
		return listToArray(list);
	}
	
	/*Converts an ArrayList to an Array*/
	private int[] listToArray(ArrayList<Integer> myList)
	{
		int[] myArray = new int[myList.size()];
		for(int i=0; i<myList.size(); i++)
		{
			myArray[i] = myList.get(i);
		}
		return myArray;
	}
	
	/*Returns a random position that has not been set before
	all the used positions are stored in the positionsAlreadySet ArrayList*/
	private int setRandomPosition(ArrayList<Integer> positionAlreadySet)
	{
		int position = randomNumberGenerator.nextInt(arrayCardSize);
		while(positionAlreadySet.contains(position))
		{
			position = randomNumberGenerator.nextInt(arrayCardSize);
		}
		positionAlreadySet.add(position);
		return position;
	}
	
	/*Randomizes the given Array*/
	private void arrayRandomizer(int[] myArray)
	{
		ArrayList<Integer> positionAlreadySet = new ArrayList<Integer>();
		
		for(int i=0; i<(arrayCardSize/2); i++)
		{
			int r = setRandomPosition(positionAlreadySet);
			int q = setRandomPosition(positionAlreadySet);
			
			int temp = myArray[r];
			myArray[r] = myArray[q];
			myArray[q] = temp;	
		}	
	}
	
	/*Positions on board assigned to cards*/
	private HashMap<Integer,Integer> positionAssignedToCard(int[] myArray)
	{
		HashMap<Integer,Integer> myMap = new HashMap<Integer,Integer>();
		for(int i=0; i<myArray.length; i++)
		{
			myMap.put(i,myArray[i]);
		}
		return myMap;
	}
	
	/*If a card(key) is on the table it has value: true else false*/
	private HashMap<Integer,Boolean> setStateOfCards(int[] myArray)
	{
		HashMap<Integer,Boolean> myMap = new HashMap<Integer,Boolean>();
		
		for(int i=0; i<myArray.length; i++)
		{
			myMap.put(myArray[i],true);
		}
		return myMap;
	}
	
	//Prints the board
	public void print()
	{
		for(int i=0; i<arrayCardSize; i++)
		{
			System.out.printf("%3d ",i);
		}
		System.out.println();
		for(int i=0; i<arrayCardSize*4; i++)
		{
			System.out.print("-");
		}
		System.out.println();
		for(int i=0; i<arrayCardSize; i++)
		{
			//If the card is on the table print *
			if(stateOfCards.get(arrayCards[i]))
			{
				System.out.print("  "+"*"+" ");
			}else{
				System.out.print("  "+" "+" ");
			}
		}
	}
	
	//Prints the selected cards for some seconds
	public void flash(int firstPosition,int secondPosition)
	{
		for(int i =0; i<arrayCardSize; i++)
		{
			System.out.printf("%3d ",i);
		}
		System.out.println();
		for(int i=0; i<arrayCardSize*4; i++)
		{
			System.out.print("-");
		}
		System.out.println();
		for(int i=0; i<arrayCardSize; i++)
		{
			if(stateOfCards.get(arrayCards[i]))
			{
				if(i==firstPosition || i == secondPosition)
				{
					System.out.print("  "+positionOfCards.get(i)+" ");
					delay(1);
				}else{
					System.out.print("  "+"*"+" ");
					delay(1);
				}
			}else{
				System.out.print("  "+" "+" ");
				delay(1);
			}
		}
		delay(10);
		System.out.print("\r");
		for(int i=0; i<arrayCardSize; i++)
		{
			if(stateOfCards.get(arrayCards[i]))
			{
				System.out.print("  "+"*"+" ");
			}else{
				System.out.print("  "+" "+" ");
			}
		}
		System.out.println();
	}
	
	public boolean openPositions(int firstPosition,int secondPosition)
	{
		if(firstPosition != secondPosition)
		{
			if(stateOfCards.get(positionOfCards.get(firstPosition)) && stateOfCards.get(positionOfCards.get(secondPosition)))
			{
				if(positionOfCards.get(firstPosition)==positionOfCards.get(secondPosition))
				{
					System.out.println();
					System.out.print("Found pair "+"("+positionOfCards.get(firstPosition)+","+positionOfCards.get(secondPosition)+")");
					stateOfCards.put(positionOfCards.get(firstPosition),false);
					stateOfCards.put(positionOfCards.get(secondPosition),false);
					positionOfCards.remove(firstPosition);
					positionOfCards.remove(secondPosition);
					System.out.println();
					System.out.println();
					print();
					System.out.println();
					return true;
				}else{
					flash(firstPosition,secondPosition);
					return false;
				}
			}return false;
		}return false;
	}
	
	public int getRandomPosition()
	{
		int r = randomNumberGenerator.nextInt(arrayCardSize);
		while(!positionOfCards.containsKey(r))
		{
			r = randomNumberGenerator.nextInt(arrayCardSize);
		}
		return r;
	}
	
	public int getRandomPosition(int position)
	{
		int r = randomNumberGenerator.nextInt(arrayCardSize);
		while(!positionOfCards.containsKey(r) || r==position)
		{
			r = randomNumberGenerator.nextInt(arrayCardSize);
		}
		return r;
	}
	
	public Boolean containsCard(int position)
	{
		if(stateOfCards.get(arrayCards[position]))
		{
			return true;
		}
		return false;
	}
	
	public int getCard(int position)
	{
		return positionOfCards.get(position);
	}
	
	public Boolean allPairsFound()
	{
		for(int i=0; i<arrayCardSize; i++)
		{
			if(containsCard(i))
			{
				return false;
			}
		}
		return true;
	}
	
	private void delay(int sec){
		try {
			Thread.currentThread().sleep(1000*sec);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	//Only used for testing the main method.
	public int getNumberOfPairs()
	{
		return numberOfPairs;
	}
	
	public static void main(String[] args)
	{
		Board myBoard = new Board(3);
		myBoard.print();
		System.out.println();
		
		for(int i =0; i< myBoard.getNumberOfPairs()*2; i++)
		{
			System.out.print(myBoard.getCard(i)+"  ");
		}
		
		System.out.println();
		int r = myBoard.getRandomPosition();
		int q = myBoard.getRandomPosition(r);
		myBoard.flash(r,q);
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("Select 2 potisions that is a match ");
		int m = input.nextInt();
		int a = input.nextInt();
		System.out.println();
		myBoard.openPositions(m,a);
		
		System.out.println("Select 2 potisions that is not a match ");
		int n = input.nextInt();
		int b = input.nextInt();
		myBoard.openPositions(n,b);
		
		if(!myBoard.containsCard(m))
		{
			System.out.print("Position "+m+" doesnt contain a card");
			System.out.println();
		}
		if(myBoard.containsCard(b))
		{
			System.out.print("Position "+b+" contains a card");
			System.out.println();
		}
		System.out.println();
		
		System.out.println("Select 2 potisions that is a match ");
		m = input.nextInt();
		a = input.nextInt();
		myBoard.openPositions(m,a);
		
		int z = myBoard.getRandomPosition();
		int w = myBoard.getRandomPosition(z);
		myBoard.openPositions(z,w);
		if(myBoard.allPairsFound())
		{
			System.out.println("All pairs are found");
		}
	}
	
}