package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HanoiSystem {

	private static int [] towers; 
	
	private static BufferedReader br;	
	private static BufferedWriter bw;
	private static final String TEST_FILE = "data/test";
	private static final String TEST_OUTPUT_FILE = "data/testOutput.txt";

	public static void main(String[] args) throws IOException 
	{	
		towers = new int [3];
		//towers[0] = 3;
		
		br = new BufferedReader(new FileReader(TEST_FILE) );
		bw = new BufferedWriter(new FileWriter(TEST_OUTPUT_FILE));
		
		
		importData();
		
		//printMovements();
		//move(towers[0], 0, 1, 2);
		
	}
	
	/*  Si disks (n) = 0 -> NO  SE HACE NADA
	 *  Si disks = 1 -> Pasar de A ([0]) a C ([2])
	 *  Si disks > 1:
	 *  	 -> Mover disks-1 a B ([1])  
	 *		 -> Mover el disk restante a C ([2]) 
	 *		 -> Mover disk-1 (que sería como otra torre de tamaño disks-1) a C ([2]) encima del disk restante.
	 */
	
	public static void move( int disks, int towerA, int towerB, int towerC ) throws IOException
	{
		
		if(disks == 1)
		{
			towers[towerA] = towers[towerA]-1; 
			towers[towerC] = towers[towerC]+1;
			//printMovements();
			bw.write( printMovements());
		}
		else
		{
			move(disks-1, towerA, towerC, towerB);
			
			move(disks-1, towerA, towerB, towerC);
			
			move(disks-1, towerB, towerC, towerA);
		}
	}
	
	public static String printMovements()
	{
		String move = "";
		for(int i = 0; i < towers.length; i++)
		{
			move += towers[i]+" ";
		}
		move+="\n";
		
		return move;
	}
	
	public static void importData() throws IOException
	{
		
		String line = br.readLine();
		int m = 0;
		
		while(line != null)
		{
			try
			{ 
				m = Integer.parseInt(line);
			
				for(int i = 1; i <= m; i++)
				{

					line = br.readLine();

					towers[0] = Integer.parseInt(line);
					towers[1] = 0;
					towers[2] = 0;
					bw.write(printMovements());
					move(towers[0], 0, 1, 2);

					bw.write("\n");
				}

			}catch(NumberFormatException ex){
				line = br.readLine();
			}
			
		}
		br.close();
		bw.close();
	}
	
}
