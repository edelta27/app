package com.example.app;

import feign.FeignException;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class AppApplication {

	@Autowired
	ShawnMendesProxy shawnMendesClient;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void makeRequestToShawnMendesEndpoint(){
        try {
            ShawnMendesResponse response = shawnMendesClient.makeSearchRequest("shawnmendes", 5);
            //System.out.println(response);
            List<ShawnMendesResult> results = response.results();
            results.forEach(
                    shawnMendesResult -> System.out.println(shawnMendesResult.trackName())
            );
        } catch (FeignException.FeignClientException feignException) {
            System.out.println("client exception: " + feignException.status());
        }catch (FeignException.FeignServerException feignException) {
            System.out.println("server exception: " + feignException.status());
        }catch (RetryableException retryableException) {
            System.out.println("retryable exception " + retryableException.getMessage());
        }catch (FeignException feignException) {
            System.out.println(feignException.getMessage());
            System.out.println(feignException.status());
        };

	}

}
