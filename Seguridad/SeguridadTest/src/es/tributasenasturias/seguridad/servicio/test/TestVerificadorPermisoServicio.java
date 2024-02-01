package es.tributasenasturias.seguridad.servicio.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import org.junit.Before;
import org.junit.Test;

import es.tributasenasturias.seguridad.servicio.InfoPermisosCertificado;
import es.tributasenasturias.seguridad.servicio.PropertyConfigurator;
import es.tributasenasturias.seguridad.servicio.SeguridadException;
import es.tributasenasturias.seguridad.servicio.SeguridadFactory;
import es.tributasenasturias.seguridad.servicio.VerificadorPermisoServicio;

public class TestVerificadorPermisoServicio {

	String certificado="";
	@Before
	public void setUp() throws Exception {
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
		BufferedWriter bu=null;
		try
		{
			bu = new BufferedWriter (new FileWriter("c:/temp/dato.txt"));
			bu.write("sec.permisos.endpoint.endpointServicioAutenticacion=http://bus.desa.epst.pa:7001/WSAutenticacionPA/ProxyServices/PXAutenticacionEPST");
			bu.write ("\n");
			bu.write ("sec.permisos.endpoint.endpointLanzador=http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador");
			bu.write ("\n");
			bu.write ("sec.permisos.bd.proc.procBDPermisoServicio=INTERNET.permisoServicio");
			bu.write ("\n");
			bu.write ("sec.permisos.bd.esquemaBD=PSEUDOREAL");
		}
		finally
		{
			if (bu!=null)
			{
				try{bu.close();} catch (Exception ex){};
			}
		}
		try
		{
			bu = new BufferedWriter (new FileWriter("c:/temp/datoMal.txt"));
			bu.write("sec.permisos.endpoint.endpointServicioAutenticacion=http://bus.desa.epst.pa:7001/WSAutenticacionPA/ProxyServices/PXAutenticacionEPST");
			bu.write ("\n");
			bu.write ("sec.permisos.endpoint.endpointLanzador=http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador");
			bu.write ("\n");
			bu.write ("sec.permisos.bd.proc.procBDPermisoServicio=INTERNET.permisoServicio");
			bu.write ("\n");
			bu.write ("sec.permisos.bd.esquemaBD=INTEGAC");
		}
		finally
		{
			if (bu!=null)
			{
				try{bu.close();} catch (Exception ex){};
			}
		}
	}

	@Test
	public void testTienePermisos() throws Exception{
		PropertyConfigurator prop = new PropertyConfigurator(new File ("c:/temp/dato.txt"));
		VerificadorPermisoServicio verif = SeguridadFactory.newVerificadorPermisoServicio(prop);
		InfoPermisosCertificado in = SeguridadFactory.newInfoPermisosCertificado();
		in = verif.tienePermisosCertificado(certificado, "CERTIFDEUD");
		assertTrue(in.isCertificadoAutorizado());
	}
	@Test
	public void testNoTienePermisos() throws Exception{
		PropertyConfigurator prop = new PropertyConfigurator(new File ("c:/temp/dato.txt"));
		VerificadorPermisoServicio verif = SeguridadFactory.newVerificadorPermisoServicio(prop);
		InfoPermisosCertificado in = SeguridadFactory.newInfoPermisosCertificado();
		in = verif.tienePermisosCertificado(certificado, "CERTIFDEED");
		assertFalse(in.isCertificadoAutorizado());
	}
	@Test (expected=SeguridadException.class)
	public void testPropiedadesMalConfigurado() throws Exception{
		PropertyConfigurator prop = new PropertyConfigurator(new File ("c:/temp/datoMal.txt"));
		VerificadorPermisoServicio verif = SeguridadFactory.newVerificadorPermisoServicio(prop);
		verif.tienePermisosCertificado(certificado, "CERTIFDEED");
	}
	@Test
	public void testPropiedadesParametro() throws Exception{
		PropertyConfigurator prop = new PropertyConfigurator("http://bus.desa.epst.pa:7001/WSAutenticacionPA/ProxyServices/PXAutenticacionEPST",
															 "http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXLanzador",
															 "INTERNET.permisoServicio",
															 "PSEUDOREAL");
		VerificadorPermisoServicio verif = SeguridadFactory.newVerificadorPermisoServicio(prop);
		verif.tienePermisosCertificado(certificado, "CERTIFDEUD");
	}

}
