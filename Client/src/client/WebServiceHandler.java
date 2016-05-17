package client;

import service.InsulinDoseCalculator;
import service.InsulinDoseCalculator_Service;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WebServiceHandler{

    private String URL;
    private String SERVICE_NAME;
    private String PORT;
    private String NAMESPACE;

    private Voter voter;

    private InsulinDoseCalculator_Service service;
    private InsulinDoseCalculator proxy;

    public WebServiceHandler(String URL, String SERVICE_NAME, String PORT, String NAMESPACE, Voter voter) {
        this.URL = URL;
        this.SERVICE_NAME = SERVICE_NAME;
        this.PORT = PORT;
        this.NAMESPACE = NAMESPACE;
        this.voter=voter;
    }

    public String getURL() {
        return URL;
    }

    public void calculateInsulinDose(int bodyWeight){
        try {
            InsulinDoseCalculator proxy = this.getProxy();
            int singleresult = proxy.backgroundInsulinDose(bodyWeight);
            System.out.println("Single result: " + singleresult);
            this.getVoter().setTechnical_details(singleresult + " ");
            synchronized (this.getVoter().getResults()) {
                Integer freq = this.getVoter().getResults().get(singleresult);
                this.getVoter().getResults().put(singleresult, (freq == null) ? 1 : freq + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Calculating Insulin Dose broke.");
            int singleresult = -1;
            this.getVoter().setTechnical_details(singleresult + " ");
            synchronized (this.getVoter().getResults()) {
                Integer freq = this.getVoter().getResults().get(singleresult);
                this.getVoter().getResults().put(singleresult, (freq == null) ? 1 : freq + 1);
            }
        }
    }

    public void mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity){
        try {
            InsulinDoseCalculator proxy = this.getProxy();
            int singleresult = proxy.mealtimeInsulinDose(carbohydrateAmount, carbohydrateToInsulinRatio, preMealBloodSugar, targetBloodSugar, personalSensitivity);
            System.out.println("Single result: " + singleresult);
            this.getVoter().setTechnical_details(singleresult + " ");
            Integer freq = this.getVoter().getResults().get(singleresult);
            this.getVoter().getResults().put(singleresult, (freq == null) ? 1 : freq + 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Calculating Insulin Dose broke.");
            int singleresult = -1;
            this.getVoter().setTechnical_details(singleresult + " ");
            Integer freq = this.getVoter().getResults().get(singleresult);
            this.getVoter().getResults().put(singleresult, (freq == null) ? 1 : freq + 1);
        }
    }

    public void personalSensitivityToInsulin(int physicalActivityLevel, ArrayList<Integer> physicalActivitySamples, ArrayList<Integer> bloodSugarDropSamples){
        try {
            InsulinDoseCalculator proxy = this.getProxy();
            int singleresult = proxy.personalSensitivityToInsulin(physicalActivityLevel, physicalActivitySamples, bloodSugarDropSamples);
            System.out.println("Single result: " + singleresult);
            this.getVoter().setTechnical_details(singleresult + " ");
            Integer freq = this.getVoter().getResults().get(singleresult);
            this.getVoter().getResults().put(singleresult, (freq == null) ? 1 : freq + 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Calculating Insulin Dose broke.");
            int singleresult = -1;
            this.getVoter().setTechnical_details(singleresult + " ");
            Integer freq = this.getVoter().getResults().get(singleresult);
            this.getVoter().getResults().put(singleresult, (freq == null) ? 1 : freq + 1);
        }
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

    public Voter getVoter() {
        return voter;
    }
}
