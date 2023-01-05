package com.its.crawlingtest.config.auth.dto;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.client.registration.ClientRegistration.Builder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

//
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
//
public enum OAuth2Provider {
    GOOGLE {
        @Override
        public Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId,
                    ClientAuthenticationMethod.CLIENT_SECRET_BASIC, DEFAULT_REDIRECT_URL);
            builder.scope("openid", "profile", "email");
            builder.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth");
            builder.tokenUri("https://www.googleapis.com/oauth2/v4/token");
            builder.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs");
            builder.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo");
            builder.userNameAttributeName(IdTokenClaimNames.SUB);
            builder.clientName("Google");
            return builder;
        }

        private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";

        protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method,
                                                              String redirectUri) {
            ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
            builder.clientAuthenticationMethod(method);
            builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
            builder.redirectUri(redirectUri);
            return builder;
        }

    };

    public abstract ClientRegistration.Builder getBuilder(String registrationId);


    }
//        private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";
//
//
//    }
////}