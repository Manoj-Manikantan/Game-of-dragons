package game;

/**
 * Dragon class
 * @author Manoj Manikantan Muralidharan (SDC 108)
 * Date : 8/12/19
 */

public abstract class Dragon 
{
	private int nAttackRank;
	private int nDefenseRank;
	private int nHitPoints;
	private int nInitiative;
	private int nTurns;
	public int nOriginalHitPoints; /* To reset the hit points to its default value */
	public int nOriginalTurns; /* To reset the no of turns its default value */
	public DragonSize nSize;
	public DragonColor nColor; 
	public String name;
	
	/* To hold the color of the dragon */
	enum DragonColor
	{
		RED, GREEN, WHITE, BLACK;
	}
	
	/* To hold the size of the dragon */
	enum DragonSize
	{
		SMALL, MEDIUM, LARGE;
	}

	public Dragon(DragonSize nSize)
	{
		this.nSize = nSize;
		switch(nSize)
		{
			case SMALL:
			{
				nHitPoints = getValueBetween(51,70);
				nInitiative = getValueBetween(40,60);
				nAttackRank = getValueBetween(36,50);
				nDefenseRank = getValueBetween(10,19);
				nTurns = 3;
				break;
			}
			case MEDIUM:
			{
				nHitPoints = getValueBetween(71,85);
				nInitiative = getValueBetween(20,40);
				nAttackRank = getValueBetween(51,60);
				nDefenseRank = getValueBetween(20,29);
				nTurns = 2;
				break;
			}
			case LARGE:
			{
				nHitPoints = getValueBetween(86,100);
				nInitiative = getValueBetween(0,10);
				nAttackRank = getValueBetween(61,70);
				nDefenseRank = getValueBetween(29,35);
				nTurns = 1;
				break;
			}
		}
		nOriginalTurns = nTurns;
		nOriginalHitPoints = nHitPoints;
	}
	
	/**
	 * This method returns a random number between the lower and upper limit
	 * @param nLower - lower limit value
	 * @param nUpper - upper limit value
	 * @return - an integer number
	 */
	public int getValueBetween(int nLower, int nUpper)
	{
		return (int) (nLower + Math.random() * (nUpper-nLower));
	}
	
	public boolean isDead() 
	{
		return (nHitPoints <= 0);
	}
	
	public void resurrect() 
	{
		nHitPoints = nOriginalHitPoints;
	}
	
	/**
	 * Prints the hit points, defense rank, attack rank and initiative  
	 */
	public String toString() 
	{
		return "Hit points : " + this.nHitPoints + " Defense Rank : " + 
     	this.nDefenseRank + " Attack Rank : " + this.nAttackRank + " Initiative : " + this.nInitiative;
	}
	
	/**
	 * This method is to check whether an attack happened or the dragon dodged that attack based on the size 
	 * @return - returns true or false based on the size of the dragon
	 */
	public boolean didAttackHappen()
	{
		switch(nSize)
		{
		case SMALL:
			return (getValueBetween(0, 100) > 30);
		case MEDIUM:
			return (getValueBetween(0, 100) > 20);
		case LARGE:
			return true;
		default:
			return false;
		}
	}
	
	abstract void doAttack(Dragon obOther);
	
	public int getnInitiative() 
	{
		return nInitiative;
	}

	public int getNumAttacksPerTurn() 
	{
		return nTurns;
	}
	
	public int getAttack() 
	{
		return nAttackRank;
	}
	
	public int getDefense()
	{
		return nDefenseRank;
	}

	public int getHitPoints()
	{
		return nHitPoints;
	}
	
	public DragonSize getnSize() 
	{
		return nSize;
	}
	
	public void setnAttackRank(int nAttackRank) 
	{
		this.nAttackRank = nAttackRank;
	}

	public void setnSize(DragonSize nSize) 
	{
		this.nSize = nSize;
	}

	public void setnDefenseRank(int nDefenseRank) 
	{
		this.nDefenseRank = nDefenseRank;
	}

	public void setnHitPoints(int nHitPoints) 
	{
		this.nHitPoints = nHitPoints;
	}

	public void setnInitiative(int nInitiative) 
	{
		this.nInitiative = nInitiative;
	}

	public int getnTurns() 
	{
		return nTurns;
	}

	public void setnTurns(int nTurns) 
	{
		this.nTurns = nTurns;
	}
}
