package client;

import java.util.*;

/**
 * Qualidade e Confiabilidade de Software
 * Daniel Bastos, Pedro Stamm e Tiago Andrade
 */
public class Voter {

    private int precision = 3;

    private Map<Integer, Integer> results;
    private List<WebServiceHandler> webservices;

    Random random = new Random();

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
        int done=0;
        long limitTime;
        List<WebServiceHandler> webservicesList = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();

        webservicesList.add(webservices.get(0));
        webservicesList.add(webservices.get(1));
        webservicesList.add(webservices.get(2));

        technical_details="Number of webservers: 3\n Results: ";
        this.results.clear();
        //WebServiceHandler webservice = webservices.get(0);
        //new Thread(webservice::calculateInsulinDose).start();
        for(WebServiceHandler x: webservicesList){
            threadList.add(new Thread(()-> x.calculateInsulinDose(bodyWeight)));
        }
        for(Thread x: threadList){
            x.start();
        }
        limitTime = System.currentTimeMillis()+4*1000;
        while(System.currentTimeMillis() < limitTime || done==3){
            for(Thread x : threadList){
                if(x.getState()==Thread.State.TERMINATED){
                    threadList.remove(x);
                    if(results.containsKey(-1)){
                        if(results.get(-1)>1){
                            results.put(-1, results.get(-1)-1);
                        } else {
                            results.remove(-1);
                        }
                        Thread n = new Thread(()-> webservices.get(random.nextInt(webservices.size())).calculateInsulinDose(bodyWeight));
                        n.start();
                        threadList.add(n);
                    } else {
                        done++;
                    }
                }
            }
        }
        for(Thread x : threadList){
            if(x.getState() != Thread.State.TERMINATED){
                x.interrupt();
            }
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
        this.mostfreqent = mostFrequent;
        this.technical_details += "\n Result of the majotiry: "+mostFrequent+"\n";
        System.out.println("Resultado do votador: " + mostFrequent);
    }

    public String technicalDetail(){
        return technical_details;
    }

}
