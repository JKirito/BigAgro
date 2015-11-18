package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.CeldaWritableConRindeAndCantPuntosxCelda;
import funciones.ParseadorOutput;

public class ParseadorOutputTest {

	final String lineaOutput = "2009-2010,soja,-365115,-620933	2010.8090665312488,4";

	@Test
	public void testgetCeldaWritable_Fila() {
		ParseadorOutput po = new ParseadorOutput(lineaOutput);
		CeldaWritableConRindeAndCantPuntosxCelda c = po.getCeldaWritableConRindeAndCantPuntosxCelda();
		assertEquals(c.getCelda().getFila().get(), -620933);
	}

	@Test
	public void testgetCeldaWritable_Columna() {
		ParseadorOutput po = new ParseadorOutput(lineaOutput);
		CeldaWritableConRindeAndCantPuntosxCelda c = po.getCeldaWritableConRindeAndCantPuntosxCelda();
		assertEquals(c.getCelda().getColumna().get(), -365115);
	}

	@Test
	public void testgetCeldaWritable_Producto() {
		ParseadorOutput po = new ParseadorOutput(lineaOutput);
		CeldaWritableConRindeAndCantPuntosxCelda c = po.getCeldaWritableConRindeAndCantPuntosxCelda();
		assertEquals(c.getCelda().getProdCamp().getProducto().toString(), "soja");
	}

	@Test
	public void testgetCeldaWritable_Campaña() {
		ParseadorOutput po = new ParseadorOutput(lineaOutput);
		CeldaWritableConRindeAndCantPuntosxCelda c = po.getCeldaWritableConRindeAndCantPuntosxCelda();
		assertEquals(c.getCelda().getProdCamp().getCampaña().toString(), "2009-2010");
	}

	@Test
	public void testgetCeldaWritable_Ride() {
		ParseadorOutput po = new ParseadorOutput(lineaOutput);
		CeldaWritableConRindeAndCantPuntosxCelda c = po.getCeldaWritableConRindeAndCantPuntosxCelda();
		assertEquals(c.getRindeValueCantxCelda().getRinde().toString(), "2010.8090665312488");
	}

	@Test
	public void testgetCeldaWritable_CantPuntosxCelda() {
		ParseadorOutput po = new ParseadorOutput(lineaOutput);
		CeldaWritableConRindeAndCantPuntosxCelda c = po.getCeldaWritableConRindeAndCantPuntosxCelda();
		assertEquals(c.getRindeValueCantxCelda().getPuntosxCelda().get(), 4);
	}

}
