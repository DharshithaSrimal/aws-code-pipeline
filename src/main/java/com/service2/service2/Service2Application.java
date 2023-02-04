package com.service2.service2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

@SpringBootApplication
public class Service2Application {


	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Map<String, String> data) {
        Gson gson = new Gson();

        RequestInput res = gson.fromJson(input.getBody(), RequestInput.class);

        String output = String.format("{ \"response\": %s }", data.toString());

        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Content-Type", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(responseHeaders);

        return response
                .withStatusCode(200)
                .withBody(output);
    }

    public APIGatewayProxyResponseEvent searchRestaurant(final APIGatewayProxyRequestEvent input, final Context context) {

        Map<String, String> data = new HashMap<>();
        data.put("name", "Salt n Pepper");
        data.put("address", "122 ddjd l");
        data.put("distance", "1.5km");

        return handleRequest(input, data);
    }

    public APIGatewayProxyResponseEvent buyFood(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> data = new HashMap<>();
        data.put("message", "food bought");

        return handleRequest(input, data);
    }

    public APIGatewayProxyResponseEvent trackFood(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> data = new HashMap<>();
        data.put("message", "food 1km away");

        return handleRequest(input, data);
    }
	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}

}
