package com.softserve.edu.utils;

import org.springframework.social.ServiceProvider;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;

public class OAuth2ConnectionFactory<A> extends ConnectionFactory<A> {

	public OAuth2ConnectionFactory(String providerId,
			ServiceProvider<A> serviceProvider, ApiAdapter<A> apiAdapter) {
		super(providerId, serviceProvider, apiAdapter);
	}

	@Override
	public Connection<A> createConnection(ConnectionData arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
