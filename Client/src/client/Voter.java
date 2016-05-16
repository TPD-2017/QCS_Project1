package client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Integração de Sistemas
 * Pedro Filipe Dinis Stamm de Matos, 2009116927
 */
public class Voter {

    private int precision = 3;

    private Map<Integer, Integer> results;
    private List<WebServiceHandler> webservices;

    public Voter(){
        results = new HashMap<>();

        webservices = new ArrayList<>();

        webservices.add(new WebServiceHandler("http://qcsproject1-qcsproject.rhcloud.com/InsulinDoseCalculator/?wsdl","InsulinDoseCalculator", "InsulinDoseCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://qcsa1-beardsdei.rhcloud.com/qcsa1/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://insulincalculator-aybareon.rhcloud.com:80/InsulinCalculatorTomcat/InsulinCalculator?wsdl","InsulinCalculator", "InsulinCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://webservice-sqdcourse.rhcloud.com/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://qcs07.dei.uc.pt:8080/InsulinCalculator?wsdl","InsulinCalculatorService", "InsulinCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://sgd46.dei.uc.pt:8088/InsulinDoseCalculator?wsdl","InsulindDoseCalculator", "InsulinDoseCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://qcsa1-ran1234.rhcloud.com/server/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/", this));
    }

    /*public static void main(String args[]){
        new Voter().testVoter();
    }*/

    public void calculateInsulinDose(){
        this.results.clear();
        WebServiceHandler webservice = webservices.get(0);
        //FUCKING 7 HOURS FOR THIS
        //Mas pelo menos descobri como raios correr threads SEM ficar preso ao método run()
        new Thread(webservice::calculateInsulinDose).start();
        for(WebServiceHandler x: webservices){
            new Thread(x::calculateInsulinDose).start();
        }
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Map<Integer, Integer> getResults() {
        return results;
    }
}
