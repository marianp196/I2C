/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import I2CBus.IBusI2C;

/**
 *
 * @author marian
 */
public interface IBusI2CFactory {
    IBusI2C Create() throws Exception;
}
