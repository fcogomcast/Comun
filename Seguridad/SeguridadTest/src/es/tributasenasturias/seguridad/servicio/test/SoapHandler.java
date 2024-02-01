/**
 * 
 */
package es.tributasenasturias.seguridad.servicio.test;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * @author crubencvs
 *
 */
public class SoapHandler implements SOAPHandler<javax.xml.ws.handler.soap.SOAPMessageContext> {

	/* (non-Javadoc)
	 * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders()
	 */
	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return Collections.emptySet();
	}

	/* (non-Javadoc)
	 * @see javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			context.getMessage().writeTo(System.out);
			System.out.println();
		} catch (SOAPException e) {
			System.err.println ("Error");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println ("Error");
			e.printStackTrace();
		}
		return true;
	}

	

}
