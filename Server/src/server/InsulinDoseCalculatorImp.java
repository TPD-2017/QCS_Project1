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
        double back_ins = (0.55 * (double) bodyWeight)*0.5;
        return (int)Math.round(back_ins);
    }

    @Override
    public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples){
        LinearRegression lr = new LinearRegression(physicalActivitySamples, bloodSugarDropSamples);
        double alpha = lr.intercept();
        double beta = lr.slope();
        return (int)Math.round(alpha + beta * (double) physicalActivityLevel);
    }
}