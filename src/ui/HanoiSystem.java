package ui;

public class HanoiSystem {

	private static int [] towers; 
	private static int disks;
	
	public static void main(String[] args) 
	{
		towers = new int [3];
	}
	
	/* Si disks (n) = 0 -> NO  SE HACE NADA
	 *  Si disks = 1 -> Pasar de A ([0]) a C ([2])
	 *  Si disks > 1:
	 *  	 -> Mover disks-1 a B ([1])  
	 *		 -> Mover el disk restante a C ([2]) 
	 *		 -> Mover disk-1 (que sería como otra torre de tamaño disks-1) a C ([2]) encima del disk restante.
	 *
	 */
	
	
	
	
}
