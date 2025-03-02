package com.currencyconverter;

import java.util.Scanner;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {
	private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

	private static boolean isValidCurrencyCode(String currency) {
		return currency.matches("^[A-Z]{3}$");
	}

	@SuppressWarnings("deprecation")
	public static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
		URL url = new URL(API_URL + baseCurrency);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		Scanner scanner = new Scanner(connection.getInputStream());
		StringBuilder response = new StringBuilder();
		while (scanner.hasNext()) {
			response.append(scanner.nextLine());
		}
		scanner.close();

		JSONObject jsonResponse = new JSONObject(response.toString());

		if (!jsonResponse.getJSONObject("rates").has(targetCurrency)) {
			throw new IOException("Invalid target currency: " + targetCurrency);
		}

		return jsonResponse.getJSONObject("rates").getDouble(targetCurrency);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the Currency Converter! ");

		System.out.print("Enter base currency (e.g., INR, USD, EUR, GBP, JPY, AUD, AED): ");
		String baseCurrency = scanner.next().toUpperCase();
		while (!isValidCurrencyCode(baseCurrency)) {
			System.out.print("Invalid currency code!\nPlease enter a valid 3-letter code (e.g.,  INR, USD, EUR, GBP, JPY, AUD, AED): ");
			baseCurrency = scanner.next().toUpperCase();
		}

		System.out.print("Enter target currency (e.g., INR, USD, EUR, GBP, JPY, AUD, AED): ");
		String targetCurrency = scanner.next().toUpperCase();
		while (!isValidCurrencyCode(targetCurrency)) {
			System.out.print("Invalid currency code!\nPlease enter a valid 3-letter code (e.g.,  INR, USD, EUR, GBP, JPY, AUD, AED): ");
			targetCurrency = scanner.next().toUpperCase();
		}

		System.out.print("Enter amount in " + baseCurrency + ": ");
		while (!scanner.hasNextDouble()) {
			System.out.print("Invalid amount! Please enter a valid number: ");
			scanner.next();
		}
		double amount = scanner.nextDouble();

		try {
			double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
			double convertedAmount = amount * exchangeRate;

			System.out.printf("Converted Amount: %.2f %s\n", convertedAmount, targetCurrency);
		} catch (IOException e) {
			System.err.println(
					"ERROR: Unable to fetch exchange rates, Please try again later or check that the currency code is entered correctly.");

			System.err.println("Technical Details: " + e.getMessage());
		}

		scanner.close();
	}
}
