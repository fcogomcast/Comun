/**
 * 
 */
package es.tributasenasturias.utils.log.test;

import static org.junit.Assert.*;

import org.junit.Test;

import es.tributasenasturias.utils.log.LogFactory;
import es.tributasenasturias.utils.log.Logger;

/**
 * @author crubencvs
 *
 */
public class AperturaFicherosTest {

	/**
	 * Test method for {@link es.tributasenasturias.utils.log.LogFactory#newLogger(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testNewLogger() {
		Logger log = LogFactory.newLogger("INFO","u:/Incidencias/log.txt", "Sesion 1");
		log.info ("Se ha indicado el mensaje");
		assertTrue(log.getLog().getWriters().get(0).getFormateador()!=null);
	}

}
