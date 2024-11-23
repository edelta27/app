package com.example.app.sampleshawnmendesserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleShawnMendesServerProxy {

    // GET http://localhost:8080/shanw/songs
    @GetMapping("/shawn/songs")
    SampleServerShawnMendesResponse fetchAllSongs(@RequestHeader String requestId);

    @PostMapping ("/shawn/songs")
    SampleServerShawnMendesResponse addSongs(@RequestBody SampleServerShawnMendesRequest request);

    @DeleteMapping("/shawn/songs/{songId}")
    void deleteByPathVariableId(@PathVariable String songId);
    @DeleteMapping("/shawn/songs")
    void deleteByIdUsingQueryParam(@RequestParam(name = "id") String songId);

}
