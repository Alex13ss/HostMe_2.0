package com.softserve.edu.utils;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.util.MultiValueMap;

public interface OAuth2Operations {
	String buildAuthorizeUrl(OAuth2Parameters parameters);

	String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters);

	String buildAuthenticateUrl(OAuth2Parameters parameters);

	String buildAuthenticateUrl(GrantType grantType, OAuth2Parameters parameters);

	AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
			MultiValueMap<String, String> additionalParameters);

	AccessGrant exchangeCredentialsForAccess(String username, String password,
			MultiValueMap<String, String> additionalParameters);

	AccessGrant refreshAccess(String refreshToken,
			MultiValueMap<String, String> additionalParameters);

	AccessGrant authenticateClient();

	AccessGrant authenticateClient(String scope);

}
