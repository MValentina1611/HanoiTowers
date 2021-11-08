package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HanoiSystem {

	private static ArrayList<Integer> towerA;
	private static ArrayList<Integer> towerB;
	private static ArrayList<Integer> towerC;
	
	private static BufferedReader br;	
	private static BufferedWriter bw;
	private static final String TEST_FILE = "data/test";
	private static final String TEST_OUTPUT_FILE = "data/testOutput.txt";

	public static void main(String[] args) throws IOException 
	{	
		
		br = new BufferedReader(new FileReader(TEST_FILE) );
		bw = new BufferedWriter(new FileWriter(TEST_OUTPUT_FILE));
		
		towerA = new ArrayList<Integer>();
		towerB = new ArrayList<Integer>();
		towerC = new ArrayList<Integer>();
		importData();
		
	}
	
	/*  Si disks (n) = 0 -> NO  SE HACE NADA
	 *  Si disks = 1 -> Pasar de A ([0]) a C ([2])
	 *  Si disks > 1:
	 *  	 -> Mover disks-1 a B ([1])  
	 *		 -> Mover el disk restante a C ([2]) 
	 *		 -> Mover disk-1 (que sería como otra torre de tamaño disks-1) a C ([2]) encima del disk restante.
	 *
	 * CAMBIAMOS LA FORMA DE EJECUTAR LA ESTRATEGIA: 
	 * 	NECESITAMOS MANEJAR LA CANTIDAD QUE SE SUMA Y QUE SE QUITA EN CADA TORRE 
	 * 	CON LOS APUNTADORES "towerA,..." SÓLO SEÑALAMOS A UNA POSICIÓN DEL ARREGLO.
	 * 	EN REALIDAD NO HAY NIGUN CAMBIO PARA UNA CASILLA DISTINTA A "0" Y "2" 
	 * 	ENTONCES SEGUIREMOS TENIENDO TORRES A B Y C PERO SERÁN ESTRUCTURAS SEPARADAS 
	 * 	TAMBIÉN NECESITAMOS AÑADIR EL CASO DE QUE SEA 0 PARA QUE NO ENTRE AL ELSE Y DEN NÚMEROS NEGATIVOS
	 */
	
	//Cambiamos el nombre para que tenga logica
	public static void playHanoi( int disks, ArrayList<Integer> origin, ArrayList<Integer> destination, ArrayList<Integer> aux) throws IOException
	{
		
		if(disks == 0)//No hacemos nada
		{
			bw.write( printMovements());
			return;
		}
		else if(disks == 1 )// Pasamos de la A a la C
		{	
			bw.write(printMovements());
			moveDisks(origin, destination);
			bw.write( printMovements());
		}
		else // Intercambiamos los movimientos
		{
			playHanoi(disks-1, origin, aux, destination);
			moveDisks(origin,destination );
			playHanoi(disks-1, aux, destination, origin);
			
		}
		
	}
	
	public static String printMovements()
	{
		String move = "";
		move += towerA.size()+ " " + towerB.size() +" "+ towerC.size();
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

					assignDiskQuantity(Integer.parseInt(line));
					
					playHanoi(towerA.size(), towerA, towerC, towerB);

					bw.write("\n");
					resetTowers();
				}

			}catch(NumberFormatException ex){
				line = br.readLine();
			}
			
		}
		br.close();
		bw.close();
	}
	
	//Necesitamos fijar la cantidad de disks para cada juego esa cantidad será el size de towerA
	
	public static void assignDiskQuantity(int disks)
	{
		for(int i = 0; i < disks; i++)
		{
			towerA.add(i);
		}
	}
	
	//Necesitamos que mueva (sumer y reste) los discos de una torre a otra
	public static void moveDisks(ArrayList<Integer> towerToDecrease,ArrayList<Integer>  towerToIncrease )
	{
		towerToIncrease.add(1);
		towerToDecrease.remove(towerToDecrease.size()-1);
		
	}
	
	//Necesitamos "reacomodar los discos para cada caso"
	
	public static void resetTowers()
	{
		towerA.clear();
		towerB.clear();
		towerC.clear();
	}
}
