package server;

/**
 * Created by Tiago Andrade on 01/05/2016.
 *
 */

import server.InsulinDoseCalculator;
import javax.xml.ws.Endpoint;

public class InsulinDoseCalculatorEndpoint {
    public static void main(String[] args){
        InsulinDoseCalculator insuline = new InsulinDoseCalculator();
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/InsulinDoseCalculator", insuline);
    }
}
