package com.lozovskyi.shop.Task_4.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputUtil {
	private static final String STRING_REGEX = ".+";
	private static final String INTEGER_REGEX = "[0-9]+";
	private static final String YES_NO_REGEX = "y|n";
	private static final String DATE_REGEX = "([1-2]\\d{3})\\-\\d{2}\\-\\d{2}";
	private static final String NOT_VALID_INPUT_MESSAGE = "Your input %s was not valid!";
	private static BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
	private static String input;

	private InputUtil() {
		throw new UnsupportedOperationException();
	}

	public static int getInteger() {
		input = "";
		while (!isMatches(INTEGER_REGEX, input)) {
			try {
				input = in.readLine();
			} catch (IOException e) {
				System.out.format(NOT_VALID_INPUT_MESSAGE, input);
			}
		}
		return Integer.valueOf(input);
	}

	public static LocalDate getLocalDate() {
		input = "";
		while (!isMatches(DATE_REGEX, input)) {
			try {
				input = in.readLine();
			} catch (IOException e) {
				System.out.format(NOT_VALID_INPUT_MESSAGE, input);
			}
		}
		return LocalDate.parse(input);
	}

	public static String getYesOrNo() {
		input = "";
		while (!isMatches(YES_NO_REGEX, input)) {
			try {
				input = in.readLine();
			} catch (IOException e) {
				System.out.format(NOT_VALID_INPUT_MESSAGE, input);
			}
		}
		return input;
	}

	public static String getString() {
		input = "";
		while (!isMatches(STRING_REGEX, input)) {
			try {
				input = in.readLine();
			} catch (IOException e) {
				System.out.format(NOT_VALID_INPUT_MESSAGE, input);
			}
		}
		return input;
	}

	private static boolean isMatches(String forPattern, String forMatcher) {
		Pattern pattern = Pattern.compile(forPattern);
		Matcher matcher = pattern.matcher(forMatcher);
		return matcher.matches();
	}
}

