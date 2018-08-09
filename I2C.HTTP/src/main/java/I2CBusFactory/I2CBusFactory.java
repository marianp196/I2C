/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I2CBusFactory;

import I2CBus.BusI2C;
import I2CBus.IBusI2C;
import com.squareup.okhttp.OkHttpClient;
import factory.IBusI2CFactory;

/**
 *
 * @author marian
 */
public class I2CBusFactory implements IBusI2CFactory{

    public I2CBusFactory(String baseUrl) {
        this._baseUrl = baseUrl;
    }
    
    @Override
    public IBusI2C Create() throws Exception {
        OkHttpClient http = new OkHttpClient();
        return new BusI2C(http, _baseUrl);
    }
    
    public String _baseUrl;

}
