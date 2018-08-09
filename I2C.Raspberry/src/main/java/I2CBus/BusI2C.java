/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I2CBus;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import java.io.IOException;
import java.util.Collection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author marian
 */
public class BusI2C implements IBusI2C{

    public BusI2C(I2CBus bus){
        _internBus = bus;
    }
    
    @Override
    public synchronized void  Write(int device, int register, byte value) throws Exception {
        I2CDevice deviceInstance = _internBus.getDevice(device);
        
        try{
             deviceInstance.write(register, value);
        }catch(IOException ex){ // so richtig gut nicht
            throw new DeviceNotFoundException();
        }
       
    }

    @Override
    public synchronized byte Read(int device, int register) throws Exception {
        I2CDevice deviceInstance = _internBus.getDevice(device);
        
        try{
             return (byte)deviceInstance.read(register);
        }catch(IOException ex){ //Do richtig gut nicht
            throw new DeviceNotFoundException();
        }
        
    }

    @Override
    public Collection<Integer> Adresses() throws Exception {
        throw new NotImplementedException();
    }
    
    private I2CBus _internBus;
    
}
