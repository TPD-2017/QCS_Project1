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

    public int getMostfreqent() {
        return mostfreqent;
    }

    private int mostfreqent = -1;

    public void setTechnical_details(String technical_details) {
        this.technical_details += technical_details;
    }

    private String technical_details="";

    public Voter(){
        results = new HashMap<>();

        webservices = new ArrayList<>();

        webservices.add(new WebServiceHandler("http://qcsproject1-qcsproject.rhcloud.com/InsulinDoseCalculator/?wsdl","InsulinDoseCalculator", "InsulinDoseCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://qcsa1-beardsdei.rhcloud.com/qcsa1/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://insulincalculator-aybareon.rhcloud.com:80/InsulinCalculatorTomcat/InsulinCalculator?wsdl","InsulinCalculator", "InsulinCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://webservice-sqdcourse.rhcloud.com/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/", this));
        //webservices.add(new WebServiceHandler("http://qcs07.dei.uc.pt:8080/InsulinCalculator?wsdl","InsulinCalculatorService", "InsulinCalculatorPort", "http://server/", this));
        //webservices.add(new WebServiceHandler("http://sgd46.dei.uc.pt:8088/InsulinDoseCalculator?wsdl","InsulindDoseCalculator", "InsulinDoseCalculatorPort", "http://server/", this));
        webservices.add(new WebServiceHandler("http://qcsa1-ran1234.rhcloud.com/server/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/", this));
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

    public void calculateInsulinDose(int bodyWeight){
        technical_details="Number of webservers: 3\n Results: ";
        this.results.clear();
        //WebServiceHandler webservice = webservices.get(0);
        //new Thread(webservice::calculateInsulinDose).start();
        for(WebServiceHandler x: webservices){
            new Thread(()-> x.calculateInsulinDose(bodyWeight)).start();
        }
    }

    public void mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity){
        technical_details="Number of webservers: 3\n Results: ";
        this.results.clear();
        //WebServiceHandler webservice = webservices.get(0);
        //new Thread(webservice::calculateInsulinDose).start();
        for(WebServiceHandler x: webservices){
            new Thread(() -> x.mealtimeInsulinDose(carbohydrateAmount, carbohydrateToInsulinRatio, preMealBloodSugar, targetBloodSugar, personalSensitivity)).start();
        }
    }

    public void personalSensitivityToInsulin(int physicalActivityLevel, ArrayList<Integer> physicalActivitySamples, ArrayList<Integer> bloodSugarDropSamples){
        technical_details="Number of webservers: 3\n Results: ";
        this.results.clear();
        //WebServiceHandler webservice = webservices.get(0);
        //new Thread(webservice::calculateInsulinDose).start();
        for(WebServiceHandler x: webservices){
            new Thread(()->x.personalSensitivityToInsulin(physicalActivityLevel, physicalActivitySamples, bloodSugarDropSamples)).start();
        }
    }

    public void majority(){
        System.out.println("Doing the majority");
        int max = -1;
        int mostFrequent = -1;

        System.out.println("Size: "+results.size());
        for(Map.Entry<Integer, Integer> x: results.entrySet()){
            System.out.println(x.getKey());
            //Metade do numero total de webservices testados
            if (x.getValue() > max && x.getValue() >= (results.size() / 2)) {
                mostFrequent = x.getKey();
                max = x.getValue();
            }
        }
        mostfreqent = mostFrequent;
        technical_details += "\n Result of the majotiry: "+mostFrequent+"\n";
        System.out.println("Resultado do votador: " + mostFrequent);
    }

    public String technicalDetail(){
        return technical_details;
    }

}
