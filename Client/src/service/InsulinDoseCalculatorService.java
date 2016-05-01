
package service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "InsulinDoseCalculatorService", targetNamespace = "http://server/", wsdlLocation = "http://localhost:8080/InsulinDoseCalculator?wsdl")
public class InsulinDoseCalculatorService
    extends Service
{

    private final static URL INSULINDOSECALCULATORSERVICE_WSDL_LOCATION;
    private final static WebServiceException INSULINDOSECALCULATORSERVICE_EXCEPTION;
    private final static QName INSULINDOSECALCULATORSERVICE_QNAME = new QName("http://server/", "InsulinDoseCalculatorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/InsulinDoseCalculator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INSULINDOSECALCULATORSERVICE_WSDL_LOCATION = url;
        INSULINDOSECALCULATORSERVICE_EXCEPTION = e;
    }

    public InsulinDoseCalculatorService() {
        super(__getWsdlLocation(), INSULINDOSECALCULATORSERVICE_QNAME);
    }

    public InsulinDoseCalculatorService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INSULINDOSECALCULATORSERVICE_QNAME, features);
    }

    public InsulinDoseCalculatorService(URL wsdlLocation) {
        super(wsdlLocation, INSULINDOSECALCULATORSERVICE_QNAME);
    }

    public InsulinDoseCalculatorService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INSULINDOSECALCULATORSERVICE_QNAME, features);
    }

    public InsulinDoseCalculatorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InsulinDoseCalculatorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns InsulinDoseCalculator
     */
    @WebEndpoint(name = "InsulinDoseCalculatorPort")
    public InsulinDoseCalculator getInsulinDoseCalculatorPort() {
        return super.getPort(new QName("http://server/", "InsulinDoseCalculatorPort"), InsulinDoseCalculator.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InsulinDoseCalculator
     */
    @WebEndpoint(name = "InsulinDoseCalculatorPort")
    public InsulinDoseCalculator getInsulinDoseCalculatorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server/", "InsulinDoseCalculatorPort"), InsulinDoseCalculator.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INSULINDOSECALCULATORSERVICE_EXCEPTION!= null) {
            throw INSULINDOSECALCULATORSERVICE_EXCEPTION;
        }
        return INSULINDOSECALCULATORSERVICE_WSDL_LOCATION;
    }

}
