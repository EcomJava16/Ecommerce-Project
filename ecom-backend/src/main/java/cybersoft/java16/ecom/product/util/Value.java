package cybersoft.java16.ecom.product.util;

public class Value {
	// ---- Model Product ---- //
	// CODE
	public static final int CODE_SIZE_MIN = 5;
	public static final int CODE_SIZE_MAX = 100;
	
	// PRICE
	public static final String PRICE_DECIMAL_MIN = "0.0";
	public static final String PRICE_DECIMAL_MAX = "1000000000";	
	public static final int PRICE_DIGITS_FRACTION = 2;
	public static final int PRICE_DIGITS_INTEGER = 10;
	public static final String PRICE_COLUMN_DEFAULT = "0";
	
	// STOCK
	public static final int STOCK_MIN = 0;
	public static final int STOCK_MAX = 999;
	public static final int STOCK_DIGITS_FRACTION = 0;
	public static final int STOCK_DIGITS_INTEGER = 3;
	public static final String STOCK_COLUMN_DEFAULT = "0";
	
}
