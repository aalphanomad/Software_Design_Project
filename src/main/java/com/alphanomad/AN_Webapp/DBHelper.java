package com.alphanomad.AN_Webapp;

import static org.asynchttpclient.Dsl.*;

import java.util.concurrent.ExecutionException;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import io.netty.util.concurrent.Future;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author elgoni much of this is based off of
 *         https://www.baeldung.com/async-http-client
 */
public class DBHelper
{
	AsyncHttpClient client;
	String default_address = "http://lamp.ms.wits.ac.za/~s1601745/";

	public DBHelper()
	{
		this.client = asyncHttpClient();

	}

	/**
	 * a general request for the php forms
	 * 
	 * @param endpoint the name of the file e.g signin
	 * @param params   the name of the parameters as an array e.g
	 *                 {"student_num","password"}
	 * @param values   the values that correspond to the params e.g {"1","test"}
	 * @return returns a string with the body of the page e.g
	 *         {"result":1,"name":"Tutor","student_num":"1","role":"0"} if
	 *         unsuccessful it will return the http response code as a string if the
	 *         params and values do not match it will return 400
	 */
	public String php_request(String endpoint, String[] params, String[] values)
	{
		String result = "";
		String full_address = this.default_address + endpoint + ".php?";

		StringBuilder param_string = new StringBuilder();
		try
		{
			for (int x = 0; x < params.length; x++)
			{
				param_string.append(params[x]);
				param_string.append("=");
				param_string.append(values[x]);
				if (x != params.length - 1)
				{
					param_string.append("&");
				}
			}
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return "400";
		}

		BoundRequestBuilder getRequest = client.prepareGet(full_address + param_string);
		ListenableFuture<Object> http_result = getRequest.execute(new AsyncCompletionHandler<Object>()
		{
			@Override
			public Object onCompleted(Response response) throws Exception
			{
				return response;
			}
		});

		try
		{
			Response response = (Response) http_result.get();

			// we only return the body if the request is successful
			if (response.getStatusCode() >= 200 && response.getStatusCode() < 300)
			{
				return response.getResponseBody();
			} else
			{
				return String.valueOf(response.getStatusCode());

			}

		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ("INTERRUPTED");
		} catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ("ERROR");
		}

	}

	/**
	 * a method to convert the json string received from the server into a java
	 * object
	 * 
	 * @param json_string the json to be parsed
	 * @return a JsonObject,
	 * @see ProfileView#make_courses_panel for an example of how to do this
	 */
	public JsonObject parse_json_string(String json_string)
	{
		JsonObject jsonObject = new JsonParser().parse(json_string).getAsJsonObject();

		return jsonObject;
	}

	/**
	 * a method to convert the json string received from the server into a java
	 * object
	 * 
	 * @param json_string the json to be parsed
	 * @return a JsonObject,
	 * @see ProfileView#make_courses_panel for an example of how to do this
	 */
	public JsonArray parse_json_string_arr(String json_string)
	{
		JsonArray jsonArr = new JsonParser().parse(json_string).getAsJsonArray();

		return jsonArr;
	}
}
