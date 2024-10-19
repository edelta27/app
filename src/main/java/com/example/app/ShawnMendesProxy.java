package com.example.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shawnmendes-client", url = "https://itunes.apple.com")
public interface ShawnMendesProxy {

    // GET https://itunes.apple.com/search?term=shawnmendes&limit=1
    @GetMapping("/search")
    String makeSearchRequest(
            @RequestParam("term") String term,
            @RequestParam("term") Integer limit
    );
}
