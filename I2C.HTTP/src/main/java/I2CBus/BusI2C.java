/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I2CBus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.util.Collection;

/**
 *
 * @author marian
 */
public class BusI2C implements IBusI2C{

    public BusI2C(OkHttpClient http, String baseUrl) {
        _http = http;
        _baseUrl = baseUrl;      
    }
    
     @Override
    public void Write(int device, int register, byte value) throws Exception {
        I2CDto dto = new I2CDto();        
        dto.value = value;        
        
         Response response = _http.newCall(createPost(
                 getUrl(_baseUrl, device, register), dto)).execute();
         
         if(response.code() == 405)
             throw new DeviceNotFoundException();
         if(! response.isSuccessful())
            throw new Exception(response.body().string());
    }

    @Override
    public byte Read(int device, int register) throws Exception {
        Response response = _http.newCall(createGet(getUrl(_baseUrl, device, register))).execute();
        
         if(response.code() == 405)
             throw new DeviceNotFoundException();
         if(! response.isSuccessful())
            throw new Exception(response.message());
         
         if(response.body().string() == null || response.body().string() == "")
             throw new Exception("no content");
        
        I2CDto dto = new ObjectMapper().readValue(response.body().string(), I2CDto.class);
        return dto.value;
    }

    @Override
    public Collection<Integer> Adresses() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Request createGet(String url){
         Request request = new Request.Builder()
        .url(url)
        .get()
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .addHeader("Cache-Control", "no-cache")
        .build();

        return request;
    }
    
    private Request createPost(String url, Object body) throws JsonProcessingException{
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody bodyObj = RequestBody.create(mediaType, new ObjectMapper().writeValueAsString(body));
        
        Request request = new Request.Builder()
        .url(url)
        .post(bodyObj)
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .addHeader("Cache-Control", "no-cache")
        .build();

        return request;
    }
    
    private  String getUrl(String baseUrl, int device, int register){
        String result = baseUrl;
        
        if(!result.endsWith("/"))
            result+= "/";
        
        return result + "device/" + String.valueOf(device) 
                + "/regsiter/" + String.valueOf(register);
    }
    
    
    private OkHttpClient _http;
    private String _baseUrl;
}
