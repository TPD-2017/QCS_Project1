package server;

/**
 * Created by Tiago Andrade on 01/05/2016.
 *
 */

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class InsulinDoseCalculator {

    /***
     *
     * @param total_ch2o_in_meal    total grams of carbohydrates in the meal
     * @param total_ch2o_proc_unit_rai  total grams of carbohydrates processed by 1 unit of rapid acting insulin
     * @param actual_bloodsuger_level   actual blood sugar level measured before the meal
     * @param target_bloddsugar     target blood sugar before the meal
     * @param ind_sensivity         individual sensitivity
     * @return                      (int)the number of units of rapid acting insulin needed after a meal (i.e., bolus insulin replacement dose).
     */
    @WebMethod
    public int mealtimeInsulinDose(double total_ch2o_in_meal, double total_ch2o_proc_unit_rai, double actual_bloodsuger_level, double target_bloddsugar, double ind_sensivity){

        double ch20_dose = total_ch2o_in_meal / total_ch2o_proc_unit_rai; //ERRO!!!

        double high_blood_sugar_dose = (actual_bloodsuger_level - target_bloddsugar) / ind_sensivity;

        double numb_units_rai = high_blood_sugar_dose + ch20_dose;

        return (int)Math.round(numb_units_rai);
    }

    /***
     *
     * @param weight Weight in kilograms (between 40kg and 130kg).
     * @return      Background insulin dose.
     */
    @WebMethod
    public int backgroundInsulinDose(double weight){
        double back_ins = (0.55 * weight)*0.5;
        return (int)Math.round(back_ins);
    }

    @WebMethod
    public int personalSensitivityToInsulin(double self_act_lvl, double[] ksomeday, double[] kdropssugar){
        LinearRegression lr = new LinearRegression(ksomeday, kdropssugar);
        double alpha = lr.intercept();
        double beta = lr.slope();
        return (int)Math.round(alpha + beta * self_act_lvl);
    }
}
