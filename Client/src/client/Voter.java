package client;

/**
 * Created by Tiago Andrade on 01/05/2016.
 *
 */

import service.InsulinDoseCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Voter {

    Map<Integer, Integer> results;

    public Voter(){
        results = new HashMap<>();
    }

    public void TestVoter(){

        List<WebServiceHandler> webservices = new ArrayList<>();

        webservices.add(new WebServiceHandler("http://qcsproject1-qcsproject.rhcloud.com/InsulinDoseCalculator/?wsdl","InsulinDoseCalculator", "InsulinDoseCalculatorPort", "http://server/"));
        webservices.add(new WebServiceHandler("http://qcsa1-beardsdei.rhcloud.com/qcsa1/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/"));
        webservices.add(new WebServiceHandler("http://insulincalculator-aybareon.rhcloud.com:80/InsulinCalculatorTomcat/InsulinCalculator?wsdl","InsulinCalculator", "InsulinCalculatorPort", "http://server/"));
        webservices.add(new WebServiceHandler("http://webservice-sqdcourse.rhcloud.com/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/"));
        InsulinDoseCalculator proxy = null;

        for(WebServiceHandler x: webservices){
            try {
                proxy = x.getProxy();
                //System.out.println("Resultado: " + proxy.mealtimeInsulinDose(60, 12, 200, 100, 25));
                //System.out.println("Resultado: " + proxy.backgroundInsulinDose(79));
                ArrayList<Integer> lista1 = new ArrayList<>();
                ArrayList<Integer> lista2 = new ArrayList<>();
                lista1.add(2);
                lista1.add(8);
                lista2.add(32);
                lista2.add(83);
                //System.out.println("Resultado: " + proxy.personalSensitivityToInsulin(6, lista1, lista2));

                //int singleresult = proxy.backgroundInsulinDose(79);
                //int singleresult = proxy.mealtimeInsulinDose(60, 12, 200, 100, 25);
                int singleresult = proxy.personalSensitivityToInsulin(6, lista1, lista2);
                System.out.println("Single result: " + singleresult);
                Integer freq = results.get(singleresult);
                results.put(singleresult, (freq == null) ? 1 : freq + 1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int max = -1;
        int mostFrequent = -1;

        for(Map.Entry<Integer, Integer> x: results.entrySet()){
            //Metade do numero total de webservices testados
            if (x.getValue() > max && x.getValue() >= (results.size() / 2)) {
                mostFrequent = x.getKey();
                max = x.getValue();
            }
        }
        System.out.println("Resultado do votador: " + mostFrequent);
    }
}
