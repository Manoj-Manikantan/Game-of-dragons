package game;

/**
 * Black class
 * @author Manoj Manikantan Muralidharan (SDC 108)
 * Date : 8/12/19
 */

public class Black extends Dragon 
{
	/* Takes in id and size to create the name of the dragon */
	public Black(int id, DragonSize nSize)
	{
		super(nSize);
		name = nSize.name() + "Black" + id;
	}
	
	/**
	 * This method carries out 3 steps 
	 * 1) Checks if attack has happened (i.e) w.r.t to the size of the dragon
	 * 2) Calculate attack ranking based on the color of the dragon
	 * 3) Calculate final hit points and set it to current dragons hit point.
	 * */
	public void doAttack(Dragon obOther)
	{
		if (!didAttackHappen())
			return;
		
		int nFinalOtherDragonAttack = obOther.getAttack(); /* Other dragons attack points */
		int nFinalMyDragonDefense = this.getDefense(); /* My dragons defense points */
		int nFinalAttackPoints;
		
		if ((obOther.nColor) == DragonColor.WHITE)
		{
			nFinalOtherDragonAttack *= 0.75;
		}
		
		/*
		 *  Other dragons attack points - my dragons defense points 
		 *  Then, finally my dragons hit point is set to 
		 *  (my dragons hit point - calculated final attack points)
		 *  */
		nFinalAttackPoints = nFinalOtherDragonAttack - nFinalMyDragonDefense;
		this.setnHitPoints(this.getHitPoints() - nFinalAttackPoints);
	}
}
