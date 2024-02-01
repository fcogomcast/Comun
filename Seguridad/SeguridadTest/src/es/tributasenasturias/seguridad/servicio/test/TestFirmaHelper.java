package es.tributasenasturias.seguridad.servicio.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.tributasenasturias.seguridad.servicio.FirmaHelper;
import es.tributasenasturias.seguridad.servicio.SeguridadFactory;

public class TestFirmaHelper {

	FirmaHelper frm;
	String xml = "<xml><datoXML id='datoFirma'>Dato contenido en el xml</datoXML></xml>";
	String xmlFirmado="<MENSAJE> "+"\n" +
"            <EJECUCION FECHA='?' HORA='?' ID='143' TIPOEJE='?'> "+"\n"+
"               <REGISTRO>"+"\n"+
"                  <NIF>?</NIF>"+"\n"+
"                  <NOMBRE>?</NOMBRE>"+"\n"+
"                  <IMPORTEPAGO/>"+"\n"+
"               </REGISTRO>"+"\n"+
"               <REGISTRO>"+"\n"+
"                  <NIF>20</NIF>"+"\n"+
"                  <NOMBRE>?</NOMBRE>"+"\n"+
"                  <IMPORTEPAGO/>"+"\n"+
"               </REGISTRO>"+"\n"+
"               <REGISTRO>"+"\n"+
"                  <NIF>?</NIF>"+"\n"+
"                  <NOMBRE>?</NOMBRE>"+"\n"+
"                  <IMPORTEPAGO/>"+"\n"+
"               </REGISTRO>"+"\n"+
"               <REGISTRO>"+"\n"+
"                  <NIF>?</NIF>"+"\n"+
"                  <NOMBRE>?</NOMBRE>"+"\n"+
"                  <IMPORTEPAGO/>"+"\n"+
"               </REGISTRO>"+"\n"+
"            </EJECUCION>"+"\n"+
"         <ds:Signature xmlns:ds='http://www.w3.org/2000/09/xmldsig#'><ds:SignedInfo><ds:CanonicalizationMethod Algorithm='http://www.w3.org/2001/10/xml-exc-c14n#WithComments'/><ds:SignatureMethod Algorithm='http://www.w3.org/2000/09/xmldsig#rsa-sha1/><ds:Reference URI='#143'><ds:Transforms><ds:Transform Algorithm='http://www.w3.org/2000/09/xmldsig#enveloped-signature'/></ds:Transforms><ds:DigestMethod Algorithm='http://www.w3.org/2000/09/xmldsig#sha1'/><ds:DigestValue>vbNvKLxUpxaYimd1y+9Lzxqs4kQ=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>wro5xlR98bp6hhRFG4rHIwDWFOry8jRfXp6/CK5liUvfb4Tgyi4qhImh20d86a3g53726jtpKrIy"+"\n"+
"N7e8MFnIDoKw+FqCNJ57vZjfF8JGb/GlwKpOtK2uIXv5/KyaBkKxdkXczBI5vgmDUpRduLg2uIw8"+"\n"+
"l0AeSe/7N0C7QIEUp5U=</ds:SignatureValue><ds:KeyInfo><ds:KeyValue><ds:RSAKeyValue><ds:Modulus>1kXlBn9suo/W1DGHShNentZRGZv656ZZKA8oHrWZoMXfE9Y9RqRspPFgX22FxOoD7ESFkma2ojZP"+"\n"+
"o0NrM/+F6vbjjBnwKsI9MZNhhDq5E6k4X20YK9GirU3qIveRQTBpZjVxE+jHoi6+vW1UW0Joll5y"+"\n"+
"1uYtyBjCDVyhlsFer4k=</ds:Modulus><ds:Exponent>AQAB</ds:Exponent></ds:RSAKeyValue></ds:KeyValue><ds:X509Data><ds:X509Certificate>MIID4DCCA0mgAwIBAgIEPK10ezANBgkqhkiG9w0BAQUFADA2MQswCQYDVQQGEwJFUzENMAsGA1UE"+"\n"+
"ChMERk5NVDEYMBYGA1UECxMPRk5NVCBDbGFzZSAyIENBMB4XDTA5MDQyMTA5NTYwOFoXDTEzMDQy"+"\n"+
"MTA5NTYwOFowgYExCzAJBgNVBAYTAkVTMQ0wCwYDVQQKEwRGTk1UMRgwFgYDVQQLEw9GTk1UIENs"+"\n"+
"YXNlIDIgQ0ExETAPBgNVBAsTCFB1YmxpY29zMRIwEAYDVQQLEwk1MDAwNzAwMTUxIjAgBgNVBAMT"+"\n"+
"GXd3dy50cmlidXRhc2VuYXN0dXJpYXMuZXMwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBANZF"+"\n"+
"5QZ/bLqP1tQxh0oTXp7WURmb+uemWSgPKB61maDF3xPWPUakbKTxYF9thcTqA+xEhZJmtqI2T6ND"+"\n"+
"azP/her244wZ8CrCPTGTYYQ6uROpOF9tGCvRoq1N6iL3kUEwaWY1cRPox6Iuvr1tVFtCaJZectbm"+"\n"+
"LcgYwg1coZbBXq+JAgMBAAGjggGtMIIBqTCBsQYDVR0RBIGpMIGmpIGIMIGFMRgwFgYJKwYBBAGs"+"\n"+
"ZgEPEwlRMzMwMDMxMkoxPzA9BgkrBgEEAaxmAQ4TMFNFUlZJQ0lPUyBUUklCVVRBUklPUyBERUwg"+"\n"+
"UFJJTkNJUEFETyBERSBBU1RVUklBUzEoMCYGCSsGAQQBrGYBCBMZd3d3LnRyaWJ1dGFzZW5hc3R1"+"\n"+
"cmlhcy5lc4IZd3d3LnRyaWJ1dGFzZW5hc3R1cmlhcy5lczAJBgNVHRMEAjAAMCsGA1UdEAQkMCKA"+"\n"+
"DzIwMDkwNDIxMDk1NjA4WoEPMjAxMzA0MjEwOTU2MDhaMAsGA1UdDwQEAwIFoDARBglghkgBhvhC"+"\n"+
"AQEEBAMCBkAwHQYDVR0OBBYEFNu5KCjO812xCx1p6VaB6ubg0nIVMB8GA1UdIwQYMBaAFECadkSX"+"\n"+
"dAfErBTLHo1POkV8MNdhMFsGA1UdHwRUMFIwUKBOoEykSjBIMQswCQYDVQQGEwJFUzENMAsGA1UE"+"\n"+
"ChMERk5NVDEYMBYGA1UECxMPRk5NVCBDbGFzZSAyIENBMRAwDgYDVQQDEwdDUkw1Nzc2MA0GCSqG"+"\n"+
"SIb3DQEBBQUAA4GBADUFCnXLAVO+T4CAtbcDSWzhRVmpxHKJyEzI1HmkPsDGp+6D2DFuu4T7maLa"+"\n"+
"unbsQ+sAEVjGmFvOXyNHghvfcahjlKPxWmeLnZ2/YAwhgTOZqD+4mZAx5YWAw+SOtja+OhOCs8BO"+"\n"+
"zovdGWNnUOzKysoDM76C3GsjRJzKyvzAQFEq</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></MENSAJE>";
	@Before
	public void setUp() throws Exception {
		frm = SeguridadFactory.newFirmaHelper ("http://bus.desa.epst.pa:7001/WSInternos/ProxyServices/PXFirmaDigital");
	}

	@Test
	public void testFirmaXML() throws Exception {
		String firmado=frm.firmaXML(xml, "tributas", "datoFirma", "xml", "");
		Assert.assertTrue(frm.esValido(firmado));
	}
	
	@Test
	public void testFirmaXMLNoValida() throws Exception {
		String firmado=frm.firmaXML(xml, "tributas", "datoFirma", "xml", "");
		Assert.assertTrue(frm.esValido(firmado));
	}
	@Test
	public void testValida() throws Exception {
		Assert.assertTrue(frm.esValido(xmlFirmado));
	}
}
