package com.example.app;

import com.example.app.itunes.ItunesProxy;
import com.example.app.itunes.ItunesResponse;
import com.example.app.itunes.ItunesResult;
import com.example.app.sampleshawnmendesserver.SampleServerShawnMendesRequest;
import com.example.app.sampleshawnmendesserver.SampleServerShawnMendesResponse;
import com.example.app.sampleshawnmendesserver.SampleShawnMendesServerProxy;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class AppApplication {
	@Autowired
    ItunesProxy itunesClient;
    @Autowired
    SampleShawnMendesServerProxy sampleShawnMendesServerClient;
    //Logger log = getLogger(AppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@EventListener(ApplicationStartedEvent.class)
	public void run(){
        try {
            //ItunesResponse response = itunesClient.makeSearchRequest("shawnmendes", 5);
            log.info(sampleShawnMendesServerClient.fetchAllSongs("id1"));
            sampleShawnMendesServerClient.deleteByPathVariableId("0");
            //log.info(sampleShawnMendesServerClient.addSongs(new SampleServerShawnMendesRequest("In my Blood")));
            //log.info(sampleShawnMendesServerClient.addSongs(new SampleServerShawnMendesRequest("Stitches")));
            log.info(sampleShawnMendesServerClient.fetchAllSongs("id2"));
        } catch (FeignException.FeignClientException feignException) {
            //System.out.println("client exception: " + feignException.status());
            log.error("client exception: " + feignException.status());
        }catch (FeignException.FeignServerException feignException) {
            //System.out.println("server exception: " + feignException.status());
            log.error("server exception: " + feignException.status());
        }catch (RetryableException retryableException) {
            //System.out.println("retryable exception " + retryableException.getMessage());
            log.error("retryable exception " + retryableException.getMessage());
        }catch (FeignException feignException) {
            log.error(feignException.status());
            //System.out.println(feignException.getMessage());
            //System.out.println(feignException.status());
        };

	}

}
