package cybersoft.java16.ecom.product.util;

public class Value {
	// ---- Model Product ---- //
	// RATE
	public static final byte RATE_MIN = 0;
	public static final byte RATE_MAX = 5;
	
	// REVIEWCOUNT
	public static final short REVIEW_COUNT_MIN = 0;
	public static final short REVIEW_COUNT_MAX = 1000;
	
	// PRICE
	public static final String PRICE_DECIMAL_MIN = "0.0";
	public static final String PRICE_DECIMAL_MAX = "1000000000";	
	public static final int PRICE_DIGITS_FRACTION = 2;
	public static final int PRICE_DIGITS_INTEGER = 10;
	public static final String PRICE_COLUMN_DEFAULT = "0";
	
	// DISCOUNT
	public static final String DISCOUNT_DECIMAL_MIN = "0.0";
	public static final String DISCOUNT_DECIMAL_MAX = "100";	
	public static final int DISCOUNT_DIGITS_FRACTION = 2;
	public static final int DISCOUNT_DIGITS_INTEGER = 2;
	public static final String DISCOUNT_COLUMN_DEFAULT = "0";
	
	// QUANTITY
	public static final short QUANTITY_MIN = 0;
	public static final short QUANTITY_MAX = 1000;
	
	// SEX
	public static final String SEX_DEFAULT = "OTHER";
}
