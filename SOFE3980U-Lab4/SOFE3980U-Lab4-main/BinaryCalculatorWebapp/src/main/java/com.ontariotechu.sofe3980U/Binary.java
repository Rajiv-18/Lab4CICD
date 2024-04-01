package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
	private String number = "0";  // string containing the binary value '0' or '1'

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	 */
	public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				number = "0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number = number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code

		if (this.number == "") { // replace empty strings with a single zero
			this.number = "0";
		}

	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}

	/**
	 * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		//initial variable
		int carry = 0;
		String num3 = "";  // the binary value of the sum
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) // loop until all digits are processed
		{
			int sum = carry; // previous carry
			if (ind1 >= 0) { // if num1 has a digit to add
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if (ind2 >= 0) { // if num2 has a digit to add
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry = sum / 2; // the new carry
			sum = sum % 2;  // the resultant digit
			num3 = ((sum == 0) ? "0" : "1") + num3; //convert sum to string and append it to num3
		}
		Binary result = new Binary(num3);  // create a binary object with the calculated value.
		return result;

	}

	/**
	 * ORing two binary numbers
	 * @param num1 First binary num
	 * @param num2 Second binary num
	 * @return Binary result of the operation
	 */
	public static Binary or(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;

		char num1digit;
		char num2digit;

		String num3 = "";  // the binary value of the OR in reverse

		// loop until all digits are processed
		while (ind1 >= 0 || ind2 >= 0) {
			// assigning appropriate values to digit variables
			num1digit = (ind1 >= 0) ? num1.number.charAt(ind1) : '0'; //if no digits remain, then fill with 0s
			num2digit = (ind2 >= 0) ? num2.number.charAt(ind2) : '0';

			if ((num1digit == '1') || (num2digit == '1')) {
				num3 += '1';
			} else {
				num3 += '0';
			}
			ind1--;
			ind2--;
		}
		// reversing the string so its in the correct order
		String answer = new StringBuffer(num3).reverse().toString();
		Binary result = new Binary(answer);
		return result;
	}

	/**
	 * ANDing two binary numbers
	 * @param num1 First binary num
	 * @param num2 Second binary num
	 * @return Binary result of the operation
	 */
	public static Binary and(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;

		char num1digit;
		char num2digit;

		String num3 = "";  // the binary value of the AND in reverse

		// loop until all digits are processed
		while (ind1 >= 0 || ind2 >= 0) {
			// assigning appropriate values to digit variables
			num1digit = (ind1 >= 0) ? num1.number.charAt(ind1) : '0'; //if no digits remain, then fill with 0s
			num2digit = (ind2 >= 0) ? num2.number.charAt(ind2) : '0';

			if ((num1digit == '0') || (num2digit == '0')) {
				num3 += '0';
			} else {
				num3 += '1';
			}
			ind1--;
			ind2--;
		}
		// reversing the string so its in the correct order
		String answer = new StringBuffer(num3).reverse().toString();
		Binary result = new Binary(answer);
		return result;
	}

	/**
	 * Multiplying two binary numbers
	 * @param num1 First binary num
	 * @param num2 Second binary num
	 * @return Binary result of the operation
	 */
	public static Binary multiply(Binary num1, Binary num2) {
		// initialize the result as 0
		Binary result = new Binary("0");

		// loop through each digit of num2
		for (int i = num2.number.length() - 1; i >= 0; i--) {
			int digit = num2.number.charAt(i) - '0';

			// multiply num1 by current digit of num2
			Binary partialProduct = (digit == 0) ? new Binary("0") : new Binary(num1.number);

			// shift partial product to left based on position in num2
			for (int j = 0; j < num2.number.length() - 1 - i; j++) {
				partialProduct.number += "0";
			}

			// add partial product to result
			result = add(result, partialProduct);
		}
		return result;
	}
}