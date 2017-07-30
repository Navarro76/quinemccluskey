package quinemccluskey;

public class Test {

	public static void main(String[] args) {
		
		int[][] datos = {{0, 0, 0, 0},
                	{0, 0, 0, 1},
                	{0, 0, 1, 0},
                	{0, 0, 1, 1},
                	{0, 1, 0, 0},
                	{0, 1, 0, 1},
                	{0, 1, 1, 0},
                	{0, 1, 1, 1},
                	{1, 0, 0, 0},
                	{1, 0, 0, 1},
                	{1, 0, 1, 0},
                	{1, 0, 1, 1},
                	{1, 1, 0, 0},
                	{1, 1, 0, 1},
                	{1, 1, 1, 0},
                	{1, 1, 1, 1}};
		
		int[][] datos2 = {{0, 0, 0, 0},
            	{0, 0, 0, 1},
            	{0, 0, 1, 0},
            	{0, 1, 0, 1},
            	{0, 1, 1, 0},
            	{0, 1, 1, 1},
            	{1, 0, 0, 0},
            	{1, 0, 0, 1},
            	{1, 0, 1, 0},
            	{1, 1, 1, 0}};
		
		//QuineMcCluskey.mostrar(datos);
		
		datos = QuineMcCluskey.run(datos);
		//QuineMcCluskey.mostrar(datos);

	}

}
