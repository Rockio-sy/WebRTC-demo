package com.WebRtc.RTC_temp.controller;

import io.livekit.server.AccessToken;
import io.livekit.server.RoomJoin;
import io.livekit.server.RoomName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import java.util.Map;

@RestController
@RequestMapping("/api/rtc")
public class TokenController {

    @Value("${livekit.url}")
    private String liveKitUrl;

    @Value("${livekit.apiKey}")
    private String apiKey;

    @Value("${livekit.apiSecret}")
    private String apiSecret;

    @GetMapping("/token")
    public Map<String, String> token
            (
                    @RequestParam String room,
                    @RequestParam String identity
            ) {
        AccessToken at = new AccessToken(apiKey, apiSecret);
        at.setIdentity(identity);
        at.setName(identity);
        at.addGrants(new RoomJoin(true), new RoomName(room));

        String jwt = at.toJwt();
        return Map.of("url", liveKitUrl, "token", jwt);
    }

}
