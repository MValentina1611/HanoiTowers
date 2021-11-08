package ui;

public class HanoiSystem {

	private static int [] towers; 
	
	public static void main(String[] args) 
	{
		towers = new int [3];
		
		
		towers[0] = 2;
		
		printMovements();
		move(towers[0], 0, 1, 2);
		
	}
	
	/* Si disks (n) = 0 -> NO  SE HACE NADA
	 *  Si disks = 1 -> Pasar de A ([0]) a C ([2])
	 *  Si disks > 1:
	 *  	 -> Mover disks-1 a B ([1])  
	 *		 -> Mover el disk restante a C ([2]) 
	 *		 -> Mover disk-1 (que sería como otra torre de tamaño disks-1) a C ([2]) encima del disk restante.
	 */
	
	
	public static void move( int disks, int towerA, int towerB, int towerC )
	{
		//System.out.println("Llamo el metodo");
		//System.out.println(disks);
		if(disks == 1)
		{
			towers[towerA] = towers[towerA]-1; 
			towers[towerC] = towers[towerC]+1;
			printMovements();
		}
		else //if( disks > 1)
		{
			move(disks-1, towerA, towerC, towerB);
			move(disks-1, towerA, towerB, towerC);
			move(disks-1, towerB, towerA, towerC);		
		}
	}
	
	public static void printMovements()
	{
		for(int i = 0; i < towers.length; i++)
		{
			System.out.print(towers[i]);
		}
		System.out.print("\n");
	}
	
	
}
