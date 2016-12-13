package name.ilab.temp;

import name.ilab.util.aop.AspectInject;

/**
 * Created by cuijfboy on 2016/10/25.
 */

public class Temp {

    @AspectInject
    public void temp(){
        System.out.println("*************** Temp.temp ***************");
    }

}
