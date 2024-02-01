package es.tributasenasturias.seguridad.servicio.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({TestComprobadorIdentidadCertificado.class,TestPermisoEjecucion.class})
public class SeguridadServicioSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for es.tributasenasturias.seguridad.servicio.test");
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}

}
