package Spieldatenverarbeitung;

public class Spieldaten implements ISpieldaten {

	int Leben = 3 ;
	int[] Knoten = new int[36] ;
	int[] Kanten = new int[48];
	int[] Modus = new int[4]; // Hinzugefügt von Mark

	public void setLifes(int lifes){

		if (lifes < 0) 
			System.out.println("Bitte einen Wert zwischen 0 und 3 angeben.");

		else if (lifes > 3)
			System.out.println("Bitte einen Wert zwischen 0 und 3 angeben.");

		else Leben = lifes;

	}

	public int getLifes(){

		return Leben;


	}


	public int SEPMANPos(){

		int a =0;

		return a;
	}
	public int defenderPos(){
		int a =0;

		return a;

	}
	public int tracerPos(){

		int a =0;

		return a;


	}
	public int randomPos(){
		int a =0;

		return a;


	}
	public int powerUp1(){
		int a =0;

		return a;



	}
	public int powerUp2(){

		int a =0;

		return a;

	}
	public int powerUp3(){

		int a =0;

		return a;
		
		
	}
	
	public int powerUp4(){

		int a =0;

		return a;
		
		
	}
	
	public int[] gefahreneKanten(){

		int a[] =null;

		return a;


	}
	public boolean kanteGefahren(int kante){



		return true;

	}
	// Hinzugefügt von Mark
	@Override
	public String[] getRoboterbelegung() {
		// TODO Auto-generated method stub
		return null;
	}
	// Hinzugefügt von Mark
	@Override
	public void setRoboterbelegung(int pos, int wert) {
		// TODO Auto-generated method stub

	}












}
