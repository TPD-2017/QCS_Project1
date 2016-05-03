package server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Tiago on 02/05/2016.
 */

@WebService(endpointInterface = "server.InsulinDoseCalculator",
portName = "InsulinDoseCalculatorPort",
serviceName = "InsulinDoseCalculator")
public class InsulinDoseCalculatorImp implements InsulinDoseCalculator {

    /***
     *
     * @param carbohydrateAmount    total grams of carbohydrates in the meal
     * @param carbohydrateToInsulinRatio  total grams of carbohydrates processed by 1 unit of rapid acting insulin
     * @param preMealBloodSugar   actual blood sugar level measured before the meal
     * @param targetBloodSugar     target blood sugar before the meal
     * @param personalSensitivity         individual sensitivity
     * @return                      (int)the number of units of rapid acting insulin needed after a meal (i.e., bolus insulin replacement dose).
     */
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

    /***
     *
     * @param bodyWeight Weight in kilograms (between 40kg and 130kg).
     * @return      Background insulin dose.
     */
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
