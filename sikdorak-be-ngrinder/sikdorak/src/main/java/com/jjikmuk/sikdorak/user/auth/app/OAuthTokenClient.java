package com.jjikmuk.sikdorak.user.auth.app;

import com.jjikmuk.sikdorak.common.config.feignclient.FeignClientHeaderConfiguration;
import com.jjikmuk.sikdorak.common.config.feignclient.FeignClientOAuthErrorConfiguration;
import com.jjikmuk.sikdorak.user.auth.app.response.OAuthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "token-client", url = "${oauth.kakao.service.token_url}", configuration = {
    FeignClientHeaderConfiguration.class,
    FeignClientOAuthErrorConfiguration.class})
public interface OAuthTokenClient {

    @PostMapping("/oauth/token")
    OAuthTokenResponse getAccessToken(@RequestParam(value = "grant_type") String grantType,
        @RequestParam(value = "client_id") String clientId,
        @RequestParam(value = "redirect_uri") String redirectUri,
        @RequestParam(value = "code") String code);
}
