package com.example.photoappapiusers.client;

import com.example.photoappapiusers.dto.AlbumDto;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

//@FeignClient(name = "albums-ws", fallback = AlbumsFallBack.class) (We'll use 'Fallbackfactory' instead)
//@FeignClient(name = "albums-ws", fallbackFactory = AlbumsFallBackFactory.class) (Maintenance mode - res4j will be used instead)
@FeignClient(name = "albums-ws")
public interface AlbumServiceClient {

    @GetMapping("/users/{id}/albums")
    @Retry(name = "albums-ws")
    @CircuitBreaker(name = "albums-ws", fallbackMethod = "getAlbumsFallback")
    List<AlbumDto> getAlbums(@PathVariable String id);

    default List<AlbumDto> getAlbumsFallback(String id, Throwable exception){
        System.out.println("Param: " + id);
        System.out.println("Exception took place: " + exception.getMessage());
        return new ArrayList<>();
    }

}

/*@Component
class AlbumsFallBack implements AlbumServiceClient{

    @Override
    public List<AlbumDto> getAlbums(String id) {
        return new ArrayList<>();
    }
}*/

/*@Component
class AlbumsFallBackFactory implements FallbackFactory<AlbumServiceClient>{
    @Override
    public AlbumServiceClient create(Throwable cause) {
        return new AlbumsServiceClientFallback(cause);
    }
}

class AlbumsServiceClientFallback implements AlbumServiceClient{

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Throwable cause;

    public AlbumsServiceClientFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<AlbumDto> getAlbums(String id) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404){
            logger.error("404 error took place when getAlbums was called with userId: " + id + ". Error message "
                        + cause.getLocalizedMessage());
        }else{
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }
        return new ArrayList<>();
    }
} MAINTENANCE MODE - RESILIENT4J WILL BE USED INSTEAD */
