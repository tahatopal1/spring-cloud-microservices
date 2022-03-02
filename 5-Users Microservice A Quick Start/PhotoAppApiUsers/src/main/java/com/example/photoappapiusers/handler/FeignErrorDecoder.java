package com.example.photoappapiusers.handler;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Autowired
    private Environment env;

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 400:
                return new Exception(response.reason());
            case 404: {
                if (s.contains("getAlbums"))
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), env.getProperty("albums.exceptions.albums-not-found"));
            }
            default:
                return new Exception(response.reason());
        }
    }
}
