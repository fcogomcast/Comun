package es.tributasenasturias.services.lanzador.client.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.tributasenasturias.services.lanzador.client.LanzadorFactory;
import es.tributasenasturias.services.lanzador.client.ParamType;
import es.tributasenasturias.services.lanzador.client.ProcedimientoAlmacenado;
import es.tributasenasturias.services.lanzador.client.TLanzador;
import es.tributasenasturias.services.lanzador.client.response.RespuestaLanzador;

public class TestRespuesta {

	ProcedimientoAlmacenado proc;
	@Before
	public void setUp() throws Exception {
		proc=new ProcedimientoAlmacenado("INTERNET_DOCUMENTOSV2.consultaDocumento","PSEUDOREAL");
		proc.param("0080900222522", ParamType.CADENA);
		proc.param ("P",ParamType.CADENA);
		proc.param ("XML",ParamType.CLOB);
		proc.param("P", ParamType.CADENA);
	}

	@Test
	public void testGetValue() throws Exception{
		TLanzador tlanz= LanzadorFactory.newTLanzador("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador?wsdl");
		String res=tlanz.ejecutar(proc);
		RespuestaLanzador respues= new RespuestaLanzador(res);
		assertTrue("00".equals(respues.getValue("CADE_CADENA", 1, "STRING_CADE")));
		assertNull(respues.getValue("PDF_PDF", 1, "pdf"));
	}
	@Test
	public void testNumeroFilas() throws Exception{
		ProcedimientoAlmacenado pr = new ProcedimientoAlmacenado("lanza_canu","PSEUDOREAL");
		pr.param("100", ParamType.NUMERO);
		TLanzador tlanz= LanzadorFactory.newTLanzador("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador?wsdl");
		String res=tlanz.ejecutar(pr);
		RespuestaLanzador respues= new RespuestaLanzador(res);
		respues.getNumFilasEstructura("CANU_CADENAS_NUMEROS");
		assertEquals(100, respues.getNumFilasEstructura("CANU_CADENAS_NUMEROS"));
	}

}
