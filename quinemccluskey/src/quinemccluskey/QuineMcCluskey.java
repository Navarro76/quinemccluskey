package quinemccluskey;

public class QuineMcCluskey {
	
	private static int[][] tabla;
	private static int[][] adyacencias;
	private static int[][] combinaciones;
	private static int filas;
	private static int columnas;
	private static int numCombinaciones = 0;
	private static int numeroDeMarcas = 0;
	
	public static int[][] run(int[][] arreglo){
		int p = 0;
		setTabla(arreglo);
		
		tabla = cantidadDeUnos(tabla);					//tabla 	
		tabla = agruparPorNumeroDeUnos(tabla);			
		//mostrar(tabla);
		combinaciones = obtenerAdyacencias(tabla);
	    setAdyacencias(combinaciones);
	    marcarCombinacionesRepetidas();
	    eliminarCombinacionesRepetidas();
	    mostrar(adyacencias);
	    // mostrar(adyacencias);
		
	   
		    adyacencias = cantidadDeUnos(adyacencias);		//Combinaciones
		    adyacencias = agruparPorNumeroDeUnos( adyacencias);
		    //mostrar(adyacencias);
		    combinaciones = obtenerAdyacencias(adyacencias);
		    setAdyacencias(combinaciones);
			//mostrar(adyacencias);
			marcarCombinacionesRepetidas();
			//mostrar(adyacencias);
			eliminarCombinacionesRepetidas();
			mostrar(adyacencias);

	 

		
		System.out.println();
		System.out.println("filas :" + filas + ", columnas :" + columnas );
		
		
		return tabla;
	}
	
	private static void setTabla(int[][] arreglo){
		
		tabla = new int[arreglo.length][arreglo[0].length + 1];
		columnas = arreglo[0].length;
		filas = arreglo.length;
		
		for (int y = 0;  y < arreglo.length; y++) {
			for (int x = 0; x < arreglo[y].length; x++){
				tabla[y][x] = arreglo[y][x];
			}
		}	
	}
	
	private static void setAdyacencias(int[][] tablaLocal){
		adyacencias = new int[numCombinaciones][columnas + 1];
		
		for (int y = 0;  y < numCombinaciones; y++) {
			for (int x = 0; x < tablaLocal[y].length; x++){
				adyacencias[y][x] = tablaLocal[y][x];
			}
		}
	}
	
	private static int[][] cantidadDeUnos(int[][] tablaLocal){
		int numBits;
		
		for (int y = 0;  y < tablaLocal.length ; y++) {
			numBits = 0;
			for (int x = 0; x < tablaLocal[y].length ; x++){
				if (tablaLocal[y][x] == 1){
					numBits++;
				}
			}
			tablaLocal[y][tablaLocal[y].length - 1] = numBits;
		}
		return tablaLocal;
	}
	
	private static int[][] agruparPorNumeroDeUnos(int[][] tablaLocal){
		int temporal[] = new int[columnas + 1];
		
		for(int k = filas - 2; k >= 0; k--){
			for (int i = 0; i < filas - 2; i++){
				if (tablaLocal[i][columnas] > tablaLocal[i + 1][columnas]){
					for(int n = 0;  n <= columnas; n++){
						temporal[n] = tablaLocal[i][n];
						tablaLocal[i][n] = tablaLocal[i + 1][n];
						tablaLocal[i + 1][n] = temporal[n];
					}
				}
			}
		}
		return tablaLocal;
	}
	
	private static int[][] obtenerAdyacencias(int[][] tablaLocal){
		int grupo1 = 0;
		int grupo2 = 0;
		int diferencias  = 0;
		int colDiferente = 0;
		
		columnas = tablaLocal[0].length - 1;
		filas = tablaLocal.length;
		
		System.out.println("columnas : " + columnas + ", filas: " + filas );
		numCombinaciones = 0;
		
		int [][] adyacencia = new int[50][columnas + 1];
		
		
		for(int filaActual = 0; filaActual < filas ; filaActual++){
			grupo1 = tablaLocal[filaActual][columnas];
			grupo2 = grupo1 + 1;
			
			for (int filaSiguiente = filaActual + 1; filaSiguiente < filas ; filaSiguiente++){
				if (tablaLocal[filaSiguiente][columnas] == grupo1 || tablaLocal[filaSiguiente][columnas] == grupo2){
					diferencias = 0;
					
					if(!(tablaLocal[filaSiguiente][columnas] == grupo1 )){
						colDiferente = 0;
						for(int n = 0; n < columnas; n++ ){
						
							if(!(tablaLocal[filaActual][n] == tablaLocal[filaSiguiente][n] )){
								diferencias++;
								colDiferente = n;
							}
						}
						
						/*System.out.println("[i: " + filaActual + ", j: " + filaSiguiente + "]" + 
								"grupo1: " + grupo1 + ", grupo2: " + grupo2 + 
								", Diferencia: " + diferencias + ", numeroDiferencia: " + colDiferente);*/
						
						if (diferencias == 1){
							
							System.out.println("[i: " + filaActual + ", j: " + filaSiguiente + "] " + 
									"grupo1: " + grupo1 + ", grupo2: " + grupo2 + 
									", Diferencia: " + diferencias + ", numeroDiferencia: " + colDiferente);
							
							for (int col = 0; col < columnas; col++){
								
								if (col == colDiferente){
									adyacencia[numCombinaciones][col] = 4;
								} else {
									adyacencia[numCombinaciones][col] = tablaLocal[filaSiguiente][col];
								}
							}
							numCombinaciones++;
						}
					}
				}
			}
		}
		
		System.out.println("numero Minterminos: " + numCombinaciones);
		return adyacencia;
		
		//
		//setAdyacencias(adyacencia);
	}
	
	private static void marcarCombinacionesRepetidas (){
		int grupo1 = 0;
		int grupo2 = 0;
		int diferencias  = 0;
		
		numeroDeMarcas = 0;
		columnas = adyacencias[0].length - 1;
		filas = adyacencias.length;
		
		for(int filaActual = 0; filaActual < filas ; filaActual++){
			grupo1 = adyacencias[filaActual][columnas];
			grupo2 = grupo1 + 1;
			
			for (int filaSiguiente = filaActual + 1; filaSiguiente < filas ; filaSiguiente++){
				
				if (adyacencias[filaSiguiente][columnas] == grupo1 || adyacencias[filaSiguiente][columnas] == grupo2){
					diferencias = 0;
					for(int n = 0; n < columnas; n++ ){
						
						if(!(adyacencias[filaActual][n] == adyacencias[filaSiguiente][n] )){
							diferencias++;
						}
					}
				}
						
				if (diferencias == 0){
					adyacencias[filaSiguiente][columnas] = 5;
					numeroDeMarcas++;
				}
			}
		}
	}
	
	private static int [][] eliminarCombinacionesRepetidas(){
		int[][] tablaLocal = adyacencias;
		int i = 0;
		adyacencias = new int[tablaLocal.length- numeroDeMarcas][tablaLocal[0].length];
		for(int fil = 0 ; fil < tablaLocal.length; fil ++){
				
			if(!(tablaLocal[fil][tablaLocal[0].length - 1] == 5)){
				//System.out.println("fil: " + fil + ", col: " + tablaLocal[0].length);
				for(int col = 0; col < tablaLocal[0].length ; col++){
					adyacencias[i][col] = tablaLocal[fil][col] ;
				}
				i++;
				//System.out.println(i);
			}
			
		}
		return adyacencias;
		
		
	}
	public static void mostrar(int[][] arreglo){
		for (int y = 0;  y < arreglo.length; y++) {
			
			for (int x = 0; x < arreglo[y].length; x++){
			
				if(x % 5 == 0){
					System.out.println();
					System.out.printf("%d :", y);
				}
				
				System.out.printf(" " + arreglo[y][x]);
			}
		}
		System.out.println();
	}
	
	private static int[][] limpiar(){
		for (int y = 0;  y < tabla.length; y++) {
			for (int x = 0; x < tabla[y].length; x++){
				tabla[y][x] = 0;
			}
		}	
		
		return tabla;
	}
	
}
