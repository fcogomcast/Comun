/**
 * 
 */
package es.tributasenasturias.services.lanzador.client.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.tributasenasturias.services.lanzador.client.LanzadorFactory;
import es.tributasenasturias.services.lanzador.client.ParamType;
import es.tributasenasturias.services.lanzador.client.TLanzador;
import es.tributasenasturias.services.lanzador.client.TParam;
import es.tributasenasturias.services.lanzador.client.TPeticion;
import es.tributasenasturias.services.lanzador.client.response.RespuestaLanzador;

/**
 * @author crubencvs
 *
 */
public class TestTLanzador {

	TLanzador lanzador;
	TPeticion peticion;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		lanzador = LanzadorFactory.newTLanzador("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzadorMasivo");
		peticion = LanzadorFactory.newTPeticion("INTERNET_DOCUMENTOSV2.consultaDocumento", "PSEUDOREAL");
		TParam param = LanzadorFactory.newTParam(ParamType.CADENA, "0080900222522", null);
		peticion.addParam(param);
		param= LanzadorFactory.newTParam(ParamType.CADENA, "P", null);
		peticion.addParam(param);
		param= LanzadorFactory.newTParam(ParamType.CADENA, "XML", null);
		peticion.addParam(param);
		param= LanzadorFactory.newTParam(ParamType.CADENA, "P", null);
		peticion.addParam(param);
	}

	/**
	 * Test method for {@link es.tributasenasturias.services.lanzador.client.TLanzador#execute(java.lang.String, es.tributasenasturias.services.lanzador.client.TPeticion, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExecute() throws Exception{
		String resu=lanzador.execute(peticion.getEsquema(), peticion.toXml(), "", "", "", "");
		assertTrue(resu.length()>0);
		RespuestaLanzador res = new RespuestaLanzador (resu);
		assertTrue (res.getEstructura("CADE_CADENA").getFila(1).getCampo("STRING_CADE")!=null);
	}
	
	@Test
	public void testProcedimientoNoExiste() throws Exception{
		peticion.setProcName("INTERNET_DOCUMENTOSV2.consultaDocumento3");
		String resu=lanzador.execute(peticion.getEsquema(), peticion.toXml(), "", "", "", "");
		assertTrue(resu.length()>0);
		RespuestaLanzador res=new RespuestaLanzador(resu);
		assertTrue(res.esErronea());
	}
	@Test
	public void testProcedimientoVacio() throws Exception{
		peticion.setProcName("");
		String resu=lanzador.execute(peticion.getEsquema(), peticion.toXml(), "", "", "", "");
		assertTrue(resu.length()>0);
		RespuestaLanzador res = new RespuestaLanzador(resu);
		assertTrue(res.esErronea());
	}
	@Test
	public void testProcedimientoNulo() throws Exception{
		peticion.setProcName(null);
		String resu=lanzador.execute(peticion.getEsquema(), peticion.toXml(), "", "", "", "");
		assertTrue(resu.length()>0);
		RespuestaLanzador res = new RespuestaLanzador(resu);
		assertTrue(res.esErronea());
	}

}
