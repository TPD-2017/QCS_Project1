package server;

/**
 * Created by Tiago Andrade on 01/05/2016.
 *
 */

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface InsulinDoseCalculator {

    @WebMethod
    int mealtimeInsulinDose(double total_ch2o_in_meal, double total_ch2o_proc_unit_rai, double actual_bloodsuger_level, double target_bloddsugar, double ind_sensivity);
    @WebMethod
    int backgroundInsulinDose(double weight);
    @WebMethod
    int personalSensitivityToInsulin(double self_act_lvl, double[] ksomeday, double[] kdropssugar);

}
