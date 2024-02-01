package es.tributasenasturias.services.lanzador.client.test;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import es.tributasenasturias.services.lanzador.client.LanzadorFactory;
import es.tributasenasturias.services.lanzador.client.ParamType;
import es.tributasenasturias.services.lanzador.client.ProcedimientoAlmacenado;
import es.tributasenasturias.services.lanzador.client.TLanzador;
import es.tributasenasturias.services.lanzador.client.response.RespuestaLanzador;

public class TestPrAlmacenado {

	ProcedimientoAlmacenado proc;
	@Before
	public void setUp() throws Exception {
		proc=new ProcedimientoAlmacenado("INTERNET_DOCUMENTOSV2.consultaDocumento","PSEUDOREAL");
		proc.param("0080900222522", ParamType.CADENA);
		proc.param ("P",ParamType.CADENA);
		proc.param ("XML",ParamType.CADENA);
		proc.param("P", ParamType.CADENA);
	}

	@Test
	public void testEjecucionCorrecta() throws Exception {
		TLanzador tlanz= LanzadorFactory.newTLanzador("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador?wsdl");
		String res=tlanz.ejecutar(proc);
		RespuestaLanzador respues= new RespuestaLanzador(res);
		System.out.println (res);
		Assert.assertTrue (!respues.esErronea());
	}

	@Test
	public void testEjecucionIncorrecta() throws Exception {
		try
		{
		TLanzador tlanz= LanzadorFactory.newTLanzador("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador2?wsdl");
		tlanz.ejecutar(proc);
		Assert.assertTrue(false);
		}
		catch (Exception ex)
		{
			Assert.assertTrue(true);
		}
	}
}
