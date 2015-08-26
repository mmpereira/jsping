package com.awesome.jsping.tasks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextprotTask implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(NextprotTask.class);
	private static final String NEXTPROT_URL = "http://www.nextprot.org/rest/protein/list?query=kinase&format=json";
	private AsyncContext ctx;
	
	public NextprotTask(AsyncContext ctx) {
		this.ctx = ctx;
	}
	
	
	@Override
	public void run() {
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.error("Could not sleep");
		}
		
		try {
			String result = callService();
			PrintWriter writer = ctx.getResponse().getWriter();
			writer.println(result);
			
		} catch (IOException  e) {
			log.error(e.getMessage());
		} finally {
			ctx.complete();
		}
		
	}
	
	private String callService() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createMinimal();
		HttpGet httpGet = new HttpGet(NEXTPROT_URL);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		String responseString;
		try {
			responseString = EntityUtils.toString(response.getEntity());
		} finally {
			response.close();
		}
		
		return responseString;
	}

}
