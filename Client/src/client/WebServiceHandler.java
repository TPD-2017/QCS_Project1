package client;

import service.InsulinDoseCalculator;
import service.InsulinDoseCalculator_Service;
import java.net.MalformedURLException;
import java.net.URL;
import javax.print.attribute.standard.MediaSize;
import javax.xml.namespace.QName;

public class WebServiceHandler {

    private String URL;
    private String SERVICE_NAME;
    private String PORT;
    private String NAMESPACE;

    private InsulinDoseCalculator_Service service;
    private InsulinDoseCalculator proxy;

    public WebServiceHandler(String URL, String SERVICE_NAME, String PORT, String NAMESPACE) {
        this.URL = URL;
        this.SERVICE_NAME = SERVICE_NAME;
        this.PORT = PORT;
        this.NAMESPACE = NAMESPACE;

    }

    public String getURL() {
        return URL;
    }

    public InsulinDoseCalculator getProxy() throws Exception {

        if (service == null || proxy == null) {
            try{
                System.out.println("URL: "+URL + "\tService name: "+ SERVICE_NAME + "\tPORT: "+PORT + "\tNAMESPACE: "+ NAMESPACE);
                service = new InsulinDoseCalculator_Service(new URL(URL), new QName(NAMESPACE, SERVICE_NAME));
                proxy = service.getInsulinDoseCalculatorPort(NAMESPACE, PORT);
            } catch(Exception e){
                throw new MalformedURLException(e.getMessage());
            }
        }

        return proxy;
    }
}
