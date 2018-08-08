/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import I2CBus.BusI2C;
import I2CBus.IBusI2C;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;

/**
 *
 * @author marian
 */
public class BusI2CFactory implements IBusI2CFactory{

    @Override
    public IBusI2C Create() throws Exception {
        if(_i2c == null)
            _i2c = new BusI2C(I2CFactory.getInstance(I2CBus.BUS_1));
        
        return _i2c;
    }
    
    private static IBusI2C _i2c;
    
}
