package game;
import java.util.ArrayList;
import java.util.stream.*;

/**
 * GameTest class
 * @author Manoj Manikantan Muralidharan (SDC 108)
 * Date : 8/12/19
 */

public class GameTest 
{
		public static void main(String[] args) 
		{
			/* Creating an ArrayList of dragons with dragon class */
			ArrayList<Dragon> Dragons = new ArrayList<Dragon>();
			
			/* Creating all 36 dragons */
			for (Dragon.DragonSize Size : Dragon.DragonSize.values()) 
			{
				for(int i = 1; i <= 3; i++)
				{
					Dragons.add(new Red(i, Size));
					Dragons.add(new Green(i, Size));
					Dragons.add(new White(i, Size));
					Dragons.add(new Black(i, Size));
				}
			}
			
			/* Printing out names and stats of all 36 dragons */
			for(int i = 0; i <= Dragons.size() - 1; i++)
			{
				System.out.println(Dragons.get(i).name);
				System.out.println(Dragons.get(i).toString());
			}
			System.out.println();
			
			/* To store the count of each battle won by each dragon */
			int[] BattlesWon = new int[Dragons.size()]; 
			
			/* A loop from 0 to 35 */
			for (int i = 0; i < Dragons.size(); i++) 
			{
				/* A loop from 1 + 1 to 35 */
			    for (int j = i + 1; j < Dragons.size(); j++) 
			    {
			    	/* Will return the winner between these two dragons */
			        Dragon roundWinner = battleRound(Dragons.get(i), Dragons.get(j));
			        if (roundWinner.name == Dragons.get(i).name)
			            BattlesWon[i] += 1; /* if first dragon (i.e) my current dragon wins  */
			        else 
			            BattlesWon[j] += 1; /* if second dragon (i.e) other dragon wins  */
			        Dragons.get(i).resurrect(); /* both dragons resurrected at the end of each battle */
			        Dragons.get(j).resurrect();
			    }
			}
			
			/* Sorting BattlesWon based in descending order along with dragon name
			 * And create an array with the dragons that have won the most 
			 * @Source : Stack overflow
			 *  */
			int[] sortedIndices = IntStream.range(0, BattlesWon.length)
	                .boxed().sorted((i, j) -> BattlesWon[j] - BattlesWon[i])
	                .mapToInt(ele -> ele).toArray();
			
			/* Printing the winners */
			System.out.println("The RESULTS are ");
			for(int dragonIndex: sortedIndices)
			{
				System.out.print(Dragons.get(dragonIndex).name + " Won : " + BattlesWon[dragonIndex]);
				System.out.println();
			}	
		}
		
		/**
		 * This method carries out 3 steps 
		 * 1) Checks which dragon attacks first based on Initiative
		 * 2) A loop which keeps getting executed until one dragon is dead
		 * 3) Sets nTurns to the original value for the next dragon battle  
		 * @param obDragon1 - i dragon
		 * @param obDragon2 - j dragon
		 * @return - the winner dragon object
		 */
		public static Dragon battleRound(Dragon obDragon1, Dragon obDragon2) 
		{
		    Boolean isFirstDragonAttacking;
		    
		    // Picking dragon that attacks first
		    if (obDragon1.getnInitiative() > obDragon2.getnInitiative())
		        isFirstDragonAttacking = true;
		    else if ((obDragon2.getnInitiative() > obDragon1.getnInitiative()))
		        isFirstDragonAttacking = false;
		    else 
		    {
		        isFirstDragonAttacking = (obDragon1.getValueBetween(0,100) > 50);
		    }

		    while (true) 
		    {
		    	/* if simulateAttack returns true, a dragon is dead */
		        if (simulateAttack(obDragon1, obDragon2, isFirstDragonAttacking))
		        {
		        	return isFirstDragonAttacking ? obDragon1 : obDragon2;
		        }
		        	
		        /* 
		         * let the other dragon attack now 
		         * Value is changed from true to false (or) false to true
		         * For each dragon to hit once per turn  
		         * */
			    isFirstDragonAttacking = !isFirstDragonAttacking;
			    
		        /* 
		         * There can be a case when none of the dragon is dead even after all the turns
		         * when getnTurns of both the dragons are 0
		         * reset to original value of nAttacksPerTurn 
		         * */
		        if (obDragon1.getnTurns() == 0 && obDragon2.getnTurns() == 0)
		        {
		        	obDragon1.setnTurns(obDragon1.nOriginalTurns);
		        	obDragon2.setnTurns(obDragon2.nOriginalTurns);
		        }       	
		    }
		}

		/**
		 * This method returns true when either of the dragon is dead
		 * @param obDragon1 - i dragon
		 * @param obDragon2 - j dragon
		 * @param isFirstDragonAttacking - true or false depending on which dragon is attacking
		 * @return - True if a dragon is dead
		 */		
		public static boolean simulateAttack(Dragon obDragon1, Dragon obDragon2, Boolean isFirstDragonAttacking) 
		{
		    if (isFirstDragonAttacking) 
		    {
		        // No attacks take place if no attacks left this turn
		        if (obDragon1.getnTurns() == 0)
		            return false;

		        /* obDragon1 is attacking obDragon2
		         * doAttack method will get called depending on the subclass dragon (i.e) dragon color
		         *  */
		        obDragon2.doAttack(obDragon1);
		        obDragon1.setnTurns(obDragon1.getnTurns()-1); /* value gets decremented per each turn */
		        return obDragon2.isDead();
		    } else 
		    {
		        // No attacks take place if no attacks left this turn
		        if (obDragon2.getnTurns() == 0)
		            return false;

		        /* obDragon1 is attacking obDragon2 */
		        obDragon1.doAttack(obDragon2);
		        obDragon2.setnTurns(obDragon2.getnTurns()-1);
		        return obDragon1.isDead();
		    }
		}
}

