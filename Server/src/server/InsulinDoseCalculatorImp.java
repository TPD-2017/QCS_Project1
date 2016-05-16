package server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "server.InsulinDoseCalculator",
portName = "InsulinDoseCalculatorPort",
serviceName = "InsulinDoseCalculator")
public class InsulinDoseCalculatorImp implements InsulinDoseCalculator {

    @Override
    public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity){

        if(carbohydrateAmount < 60 || carbohydrateAmount > 120){
            return -1;
        }
        if(carbohydrateToInsulinRatio < 10 || carbohydrateToInsulinRatio > 15){
            return -1;
        }
        if(preMealBloodSugar < 120 || preMealBloodSugar > 250){
            return -1;
        }
        if(targetBloodSugar < 80 || targetBloodSugar > 120){
            return -1;
        }
        if(personalSensitivity < 15 || personalSensitivity > 100){
            return -1;
        }
        if(preMealBloodSugar < targetBloodSugar){
            return 0;
        }

        double carbohydrateDose = (double)carbohydrateAmount / (double)carbohydrateToInsulinRatio / (double)personalSensitivity * 50.0;
        double highBloodSugarDose = ((double)preMealBloodSugar - (double)targetBloodSugar) / (double)personalSensitivity;

        double numberOfUnits = carbohydrateDose + highBloodSugarDose;
        return (int)Math.round(numberOfUnits);
    }

    @Override
    public int backgroundInsulinDose(int bodyWeight){
        if(bodyWeight < 40 || bodyWeight > 130) return -1;
        double back_ins = (0.55 * (double) bodyWeight)*0.5;
        return (int)Math.round(back_ins);
    }

    @Override
    public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples){
        if(physicalActivityLevel < 0 || physicalActivityLevel > 10){
            return -1;
        }
        for (int x : physicalActivitySamples){
            if(x < 0 || x > 10){
                return -1;
            }
        }
        for (int x : bloodSugarDropSamples){
            if(x < 15 || x > 100){
                return -1;
            }
        }

        LinearRegression lr = new LinearRegression(physicalActivitySamples, bloodSugarDropSamples);
        double alpha = lr.intercept();
        double beta = lr.slope();
        return (int)Math.round(alpha + beta * (double) physicalActivityLevel);
    }
}
