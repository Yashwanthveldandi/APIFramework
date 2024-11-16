package com.pages;

import io.restassured.response.Response; // To capture responses
import static io.restassured.RestAssured.given; // This is needed for the 'given()' method

import io.restassured.http.ContentType;

public class CommonMethods {

	// The GET method is used to request data from a specified resource. It does not
	// alter the state of the resource.
	public void get(String URI, String endPoint) {
		Response response = given().baseUri(URI).when().get(endPoint).then().extract().response();

		System.out.println("Status Code: " + response.statusCode());
		System.out.println("Response Body: " + response.body().asString());

	}

	// The POST method is used to send data to the server to create a new resource.
	public void post(String URI, String endPoint, String requestBody) {
		given().baseUri(URI).contentType(ContentType.JSON).body(requestBody).when().post(endPoint).then()
				.statusCode(201).log().body();
	}

	// The PUT method is used to update an existing resource or create a new
	// resource if it does not exist.
	public void put(String URI, String endPoint, String requestBody) {
		given().baseUri(URI).contentType(ContentType.JSON).body(requestBody).when().put(endPoint).then().statusCode(200)
				.log().body();
	}

	// The PATCH method is used to make partial changes to an existing resource.
	public void patch(String URI, String endPoint, String requestBody) {
		given().baseUri(URI).contentType(ContentType.JSON).body(requestBody).when().patch(endPoint).then()
				.statusCode(200).log().body();
	}

	// The DELETE method is used to delete a specified resource.
	public void delete(String URI, String endPoint) {
		given().baseUri(URI).when().delete(endPoint).then().statusCode(200).log().body();
	}

	// The HEAD method is used to retrieve the headers of a resource, similar to
	// GET, but without the response body.
	public void head(String URI, String endPoint) {
		given().baseUri(URI).when().head(endPoint).then().statusCode(200).log().headers();
	}

	// The OPTIONS method is used to retrieve the allowed methods for a specific
	// resource.
	public void options(String URI, String endPoint) {
		given().baseUri(URI).when().options(endPoint).then().statusCode(200).log().headers();
	}

	// Bearer token authentication

	// The GET method is used to request data from a specified resource. It does not
	// alter the state of the resource.
	public void get(String URI, String endPoint, String YOUR_ACCESS_TOKEN) {
		Response response = given().baseUri(URI).auth().oauth2(YOUR_ACCESS_TOKEN).when().get(endPoint).then().extract()
				.response();

		System.out.println("Status Code: " + response.statusCode());
		System.out.println("Response Body: " + response.body().asString());

	}

	// The POST method is used to send data to the server to create a new resource.
	public void post(String URI, String endPoint, String requestBody, String YOUR_ACCESS_TOKEN) {
		given().baseUri(URI).auth().oauth2(YOUR_ACCESS_TOKEN).contentType(ContentType.JSON).body(requestBody).when()
				.post(endPoint).then().statusCode(201).log().body();
	}

	// The PUT method is used to update an existing resource or create a new
	// resource if it does not exist.
	public void put(String URI, String endPoint, String requestBody, String YOUR_ACCESS_TOKEN) {
		given().baseUri(URI).auth().oauth2(YOUR_ACCESS_TOKEN).contentType(ContentType.JSON).body(requestBody).when()
				.put(endPoint).then().statusCode(200).log().body();
	}

	// The PATCH method is used to make partial changes to an existing resource.
	public void patch(String URI, String endPoint, String requestBody, String YOUR_ACCESS_TOKEN) {
		given().baseUri(URI).auth().oauth2(YOUR_ACCESS_TOKEN).contentType(ContentType.JSON).body(requestBody).when()
				.patch(endPoint).then().statusCode(200).log().body();
	}

	// The DELETE method is used to delete a specified resource.
	public void delete(String URI, String endPoint, String YOUR_ACCESS_TOKEN) {
		given().baseUri(URI).auth().oauth2(YOUR_ACCESS_TOKEN).when().delete(endPoint).then().statusCode(200).log()
				.body();
	}

	// The HEAD method is used to retrieve the headers of a resource, similar to
	// GET, but without the response body.
	public void head(String URI, String endPoint, String YOUR_ACCESS_TOKEN) {
		given().baseUri(URI).auth().oauth2(YOUR_ACCESS_TOKEN).when().head(endPoint).then().statusCode(200).log()
				.headers();
	}

	// The OPTIONS method is used to retrieve the allowed methods for a specific
	// resource.
	public void options(String URI, String endPoint, String YOUR_ACCESS_TOKEN) {
		given().baseUri(URI).auth().oauth2(YOUR_ACCESS_TOKEN).when().options(endPoint).then().statusCode(200).log()
				.headers();
	}

	// headers and params

	// The GET method is used to request data from a specified resource. It does not
	// alter the state of the resource.
	public void get(String URI, String endPoint, String headerKey, String headerValue, String paramKey,
			int paramValue) {
		Response response = given().baseUri(URI).header(headerKey, headerValue).param(paramKey, paramValue).when()
				.get(endPoint).then().extract().response();

		System.out.println("Status Code: " + response.statusCode());
		System.out.println("Response Body: " + response.body().asString());

	}

	// The POST method is used to send data to the server to create a new resource.
	public void post(String URI, String endPoint, String requestBody, String headerKey, String headerValue,
			String paramKey, int paramValue) {
		given().baseUri(URI).header(headerKey, headerValue).param(paramKey, paramValue).contentType(ContentType.JSON)
				.body(requestBody).when().post(endPoint).then().statusCode(201).log().body();
	}

	// The PUT method is used to update an existing resource or create a new
	// resource if it does not exist.
	public void put(String URI, String endPoint, String requestBody, String headerKey, String headerValue,
			String paramKey, int paramValue) {
		given().baseUri(URI).header(headerKey, headerValue).param(paramKey, paramValue).contentType(ContentType.JSON)
				.body(requestBody).when().put(endPoint).then().statusCode(200).log().body();
	}

	// The PATCH method is used to make partial changes to an existing resource.
	public void patch(String URI, String endPoint, String requestBody, String headerKey, String headerValue,
			String paramKey, int paramValue) {
		given().baseUri(URI).header(headerKey, headerValue).param(paramKey, paramValue).contentType(ContentType.JSON)
				.body(requestBody).when().patch(endPoint).then().statusCode(200).log().body();
	}

	// The DELETE method is used to delete a specified resource.
	public void delete(String URI, String endPoint, String headerKey, String headerValue, String paramKey,
			int paramValue) {
		given().baseUri(URI).header(headerKey, headerValue).param(paramKey, paramValue).when().delete(endPoint).then()
				.statusCode(200).log().body();
	}

	// The HEAD method is used to retrieve the headers of a resource, similar to
	// GET, but without the response body.
	public void head(String URI, String endPoint, String headerKey, String headerValue, String paramKey,
			int paramValue) {
		given().baseUri(URI).header(headerKey, headerValue).param(paramKey, paramValue).when().head(endPoint).then()
				.statusCode(200).log().headers();
	}

	// The OPTIONS method is used to retrieve the allowed methods for a specific
	// resource.
	public void options(String URI, String endPoint, String headerKey, String headerValue, String paramKey,
			int paramValue) {
		given().baseUri(URI).header(headerKey, headerValue).param(paramKey, paramValue).when().options(endPoint).then()
				.statusCode(200).log().headers();
	}

}
