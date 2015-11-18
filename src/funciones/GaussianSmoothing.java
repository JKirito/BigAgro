package funciones;

import java.util.ArrayList;
import java.util.List;

import entities.CeldaWritable;
import entities.CeldaWritableConRinde;

// http://homepages.inf.ed.ac.uk/rbf/HIPR2/gsmooth.htm
public class GaussianSmoothing implements IFilter{
	private Integer columna;
	private Integer fila;
	private String campaña;
	private String producto;
	private double rend;
	/**
	 *
	 */
	private Integer[][] kernel = new Integer[][]{
								{1,  4,  7,  4, 1},
								{4, 16, 26, 16, 4},
								{7, 26, 41, 26, 7},
								{4, 16, 26, 16, 4},
								{1,  4,  7,  4, 1},
								};

	/**
	 * Tamaño del kernel: NO todas las celdas, sino el alto ( o ancho, da lo mismo, es cuadrado)
	 */
	private final int KERNEL_SIZE = 5;
	/**
	 * Al ser un cuadrado, la posición del centro es la misma tanto para fila como columna: la mitad del alto del kernel
	 */
	private final int KERNEL_MIDDLE_POS = KERNEL_SIZE / 2;

	/**
	 * Suma de todos los valores del kernel. Es publico para poder usarlo en el Reducer, necesito dividir
	 */
	public static final int KERNEL_VALUES_SUM = 273;


	public GaussianSmoothing(CeldaWritable celda, double rend) {
		this.columna = celda.getColumna().get();
		this.fila = celda.getFila().get();
		this.producto = celda.getProdCamp().getProducto().toString();
		this.campaña = celda.getProdCamp().getCampaña().toString();
		this.rend = rend;
	}

	@Override
	public List<CeldaWritableConRinde> getSmoothingCells() {
		ArrayList<CeldaWritableConRinde> celdas = new ArrayList<CeldaWritableConRinde>();

		CeldaWritable celda = null;
		CeldaWritableConRinde celdaRend = null;

		//El kernel es una matriz de 5x5. Tengo que emitir 25 celdas
		// i = fila del kernel
		// j = columna del kernel
		//System.out.println("-------------------------");
		int a = 0;
		for (int i = 0; i < KERNEL_SIZE; i++)
		{
			for (int j = 0; j < KERNEL_SIZE; j++)
			{
				int posi = i - KERNEL_MIDDLE_POS;
				int posj = j - KERNEL_MIDDLE_POS;

				a++;

				//System.out.println(a+": celda : "+posi+","+posj+ " * "+i+","+j + "("+this.kernel[i][j]+")");
				celda = new CeldaWritable(this.fila+posi, this.columna+posj, this.producto, this.campaña);
				celdaRend = new CeldaWritableConRinde(celda, this.rend*this.kernel[i][j]);

				// El valor de la celda que emite un proporcional de su rinde al resto, emite como negativo SU rinde (cenlda central)
				// Me permite que en el resultado final pueda saber cuáles celdas existen sólo porque sus vecinos existen
				// Esto es SÓLO PARA NO RELLENAR AGUJEROS!!!
//				if (i == j && j == KERNEL_MIDDLE_POS)
//				{
//					double rendTemp = celdaRend.getRend().get() * -1;
//					celdaRend.setRend(new DoubleWritable(rendTemp));
//				}
				celdas.add(celdaRend);
			}
		}
		//System.out.println("-------------------------se reportaron: "+a);

		return celdas;
	}



}
