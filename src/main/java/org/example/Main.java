package org.example;
 
import com.google.gson.Gson;

import java.util.Map;
import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class Main {
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Map<String, String> data) {
        Gson gson = new Gson();

        RequestInput res = gson.fromJson(input.getBody(), RequestInput.class);

        String output = String.format("{ \"response\": %s }", data.toString());

        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Content-Type", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(responseHeaders);
stem.out.println("Done");
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
}
