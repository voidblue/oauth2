package com.example.postgretest.Security;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;
import java.util.HashMap;

@EnableAuthorizationServer
@Configuration
public class AuthServerConfigurer extends AuthorizationServerConfigurerAdapter {

    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService user;
    public AuthServerConfigurer(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager =
                authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials",
                        "implicit", "refresh_token")
                .secret("{noop}secret")
                .authorities("ROLE_USER")
                .scopes("all")
                .accessTokenValiditySeconds(60 * 60 * 4)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 7)
                .and()
        ; // 계속 추가 가능
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        HashMap hashMap = new HashMap();
        TokenEnhancerChain chain = new TokenEnhancerChain();

        JwtAccessTokenConverter xxx = new JwtAccessTokenConverter();
        System.out.println("???" + xxx.getAccessTokenConverter().extractAccessToken("access_token", hashMap));
        System.out.println("???" + hashMap.get("user_name"));
        endpoints.authenticationManager(authenticationManager)
                //                .tokenEnhancer(jwtTokenEnhancer())
                .accessTokenConverter(xxx)
                .userDetailsService(user);
    }


    //이건 어디에 쓰이지
//    @Bean
//    public TokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtTokenEnhancer());
//    }
    @Bean
    protected TokenEnhancer jwtTokenEnhancer(){
        TokenEnhancer enhancer = new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                HashMap<String, Object> info = new HashMap<String, Object>();
                ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
                return accessToken;
            }
        };
        return enhancer;
    }

}
