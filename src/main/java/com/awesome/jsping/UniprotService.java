package com.awesome.jsping;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class UniprotService {
	
	private static final String UNIPROT_URL = "http://www.uniprot.org/uniprot/P12345.xml";

	public String callService() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createMinimal();
		HttpGet httpGet = new HttpGet(UNIPROT_URL);
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
		return EntityUtils.toString(response.getEntity());
		} finally {
			response.close();
		}
	}

	
}
