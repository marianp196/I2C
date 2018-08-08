/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I2CBus;

import java.util.Collection;

/**
 *
 * @author marian
 */
public interface IBusI2C {
    void Write(int device, int register, byte value) throws Exception;
    byte Read(int device, int register) throws Exception;
    
    Collection<Integer> Adresses() throws Exception;
}
