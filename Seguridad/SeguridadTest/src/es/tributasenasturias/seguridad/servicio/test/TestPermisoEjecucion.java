/**
 * 
 */
package es.tributasenasturias.seguridad.servicio.test;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

import es.tributasenasturias.seguridad.servicio.DatosPermisosServicio;
import es.tributasenasturias.seguridad.servicio.InfoCertificado;
import es.tributasenasturias.seguridad.servicio.VerificadorCertificado;
import es.tributasenasturias.seguridad.servicio.PermisoEjecucion;
import es.tributasenasturias.seguridad.servicio.SeguridadFactory;

/**
 * @author crubencvs
 *
 */
public class TestPermisoEjecucion {

	/**
	 * @throws java.lang.Exception
	 */
	VerificadorCertificado comp;
	String certificado;
	String id;
	@Before
	public void setUp() throws Exception {
		comp= SeguridadFactory.newVerificadorCertificado("http://bus.desa.epst.pa:7001/WSAutenticacionPA/ProxyServices/PXAutenticacionEPST");
		certificado="MIID4DCCA0mgAwIBAgIEPK10ezANBgkqhkiG9w0BAQUFADA2MQswCQYDVQQGEwJFUzENMAsGA1UE" + '\n' +
		"ChMERk5NVDEYMBYGA1UECxMPRk5NVCBDbGFzZSAyIENBMB4XDTA5MDQyMTA5NTYwOFoXDTEzMDQy" + '\n' +
		"MTA5NTYwOFowgYExCzAJBgNVBAYTAkVTMQ0wCwYDVQQKEwRGTk1UMRgwFgYDVQQLEw9GTk1UIENs" + '\n' +
		"YXNlIDIgQ0ExETAPBgNVBAsTCFB1YmxpY29zMRIwEAYDVQQLEwk1MDAwNzAwMTUxIjAgBgNVBAMT" + '\n' +
		"GXd3dy50cmlidXRhc2VuYXN0dXJpYXMuZXMwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBANZF" + '\n' +
		"5QZ/bLqP1tQxh0oTXp7WURmb+uemWSgPKB61maDF3xPWPUakbKTxYF9thcTqA+xEhZJmtqI2T6ND" + '\n' +
		"azP/her244wZ8CrCPTGTYYQ6uROpOF9tGCvRoq1N6iL3kUEwaWY1cRPox6Iuvr1tVFtCaJZectbm" + '\n' +
		"LcgYwg1coZbBXq+JAgMBAAGjggGtMIIBqTCBsQYDVR0RBIGpMIGmpIGIMIGFMRgwFgYJKwYBBAGs" + '\n' + 
		"ZgEPEwlRMzMwMDMxMkoxPzA9BgkrBgEEAaxmAQ4TMFNFUlZJQ0lPUyBUUklCVVRBUklPUyBERUwg" + '\n' +
		"UFJJTkNJUEFETyBERSBBU1RVUklBUzEoMCYGCSsGAQQBrGYBCBMZd3d3LnRyaWJ1dGFzZW5hc3R1" + '\n' +
		"cmlhcy5lc4IZd3d3LnRyaWJ1dGFzZW5hc3R1cmlhcy5lczAJBgNVHRMEAjAAMCsGA1UdEAQkMCKA" + '\n' +
		"DzIwMDkwNDIxMDk1NjA4WoEPMjAxMzA0MjEwOTU2MDhaMAsGA1UdDwQEAwIFoDARBglghkgBhvhC" + '\n' +
		"AQEEBAMCBkAwHQYDVR0OBBYEFNu5KCjO812xCx1p6VaB6ubg0nIVMB8GA1UdIwQYMBaAFECadkSX" + '\n' +
		"dAfErBTLHo1POkV8MNdhMFsGA1UdHwRUMFIwUKBOoEykSjBIMQswCQYDVQQGEwJFUzENMAsGA1UE" + '\n' +
		"ChMERk5NVDEYMBYGA1UECxMPRk5NVCBDbGFzZSAyIENBMRAwDgYDVQQDEwdDUkw1Nzc2MA0GCSqG" + '\n' +
		"SIb3DQEBBQUAA4GBADUFCnXLAVO+T4CAtbcDSWzhRVmpxHKJyEzI1HmkPsDGp+6D2DFuu4T7maLa" + '\n' +
		"unbsQ+sAEVjGmFvOXyNHghvfcahjlKPxWmeLnZ2/YAwhgTOZqD+4mZAx5YWAw+SOtja+OhOCs8BO" + '\n' +
		"zovdGWNnUOzKysoDM76C3GsjRJzKyvzAQFEq";
		InfoCertificado info = comp.login(certificado);
		if (info.getCif()!=null)
		{
			id = info.getCif();
		}
		else
		{
			id = info.getNifNie();
		}
	}

	@Test
	public void testPermisoEjecucionCorrecto() throws Exception {
		PermisoEjecucion pers=SeguridadFactory.newPermisoEjecucion("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador", "PSEUDOREAL", "INTERNET.permisoServicio");
		DatosPermisosServicio permiso=pers.permisoServicio("CERTIFDEUD", id);
		System.out.println (id);
		assertTrue(DatosPermisosServicio.AutorizacionServicio.AUTORIZADO.equals(permiso.getAutorizacion()));
	}
	@Test
	public void testPermisoEjecucionIncorrecto() throws Exception {
		PermisoEjecucion pers=SeguridadFactory.newPermisoEjecucion("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador", "PSEUDOREAL", "INTERNET.permisoServicio");
		DatosPermisosServicio permiso=pers.permisoServicio("ANCERT_CAL", id);
		assertFalse(DatosPermisosServicio.AutorizacionServicio.AUTORIZADO.equals(permiso.getAutorizacion()));
	}
	@Test
	public void testPermisoEjecucionWithHandlers() throws Exception {
		PermisoEjecucion pers=SeguridadFactory.newPermisoEjecucion("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador", "PSEUDOREAL", "INTERNET.permisoServicio");
		pers.setManejadorMensajes(new SoapHandler());
		DatosPermisosServicio permiso=pers.permisoServicio("CERTIFDEUD", id);
		assertTrue(DatosPermisosServicio.AutorizacionServicio.AUTORIZADO.equals(permiso.getAutorizacion()));
	}

}
