
package com.spring.util;

import java.math.BigDecimal;


public final class Constants {
	private Constants() {
		// Can't construct this
	}

	public static final String USERID = "USERID";
	
	public static final String USERNAME = "USERNAME";
	
	public static final String  FAILED_RECORD_FILE="Failed records file";
	
	public static final String  PASSED_RECORD_FILE="Passed records file";
	
	public static final String REQUEST_MESSAGE_KEY = "messages";

	public static final String REQUEST_ERROR_KEY = "errors";

	public static final String PIPE_DELIMITER = "|";

	public static final String COMMA_DELIMITER = ",";

	public static final String PERIOD = ".";

	public static final String COLON = ":";

	public static final String NEWLINE = "\n";

	public static final String QUOTE = "\"";

	public static final String EMPTY_STRING = "";

	public static final char DELIMITER = 127;

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100).setScale(4);

	public static final String PUNCTUATION_TO_STRIP = " - ";

	public static final String PUNCTUATION_REPLACEMENT = " ";

	public static final String REQUEST_MENUS_KEY = "menus";

	public static final String COMMA_DELIMITER_SPACE = ", ";

	public static final int DEFAULT_SELECT_VALUE = -1;

	public static final String IMAGE_FILENAME_TO_STRIP = " ";

	public static final String IMAGE_FILENAME_REPLACEMENT = "-";

	public static final int NON_ADMIN_MAX_REPORT_ROW_LIMIT = 350;

	public static final int ADMIN_MAX_REPORT_ROW_LIMIT = 9999;

	public static final String CSV_CARRIAGE_RETURN = "\n";

	public static final String PARAM_FORMAT_HTML = "html";

	public static final String PARAM_FORMAT_CSV = "csv";

	public static final String BIRT_TABLE_PREFIX = "table";

	public static final String HTML_NEWLINE = "<BR>";

	public static final String HTML_NEWLINE_1 = "<BR/>";

	public static final String HTTP_URL_HEADER = "http://";

	public static final String TEXT_DELIMITER = "\"";

	public static final long BULK_IMPORT_MAX_FILE_SIZE = 1048576L;

	public static final int HTML_CONTENT_MAX_CHARS = 4000;

	public static final int HTML_CONTENT_MAX_NOTE_CHARS = 500000;

	public static final int FEDEX_DEFAULT_DISPATCHDATE_OFFSET = 1;

	public static final String FS_HELPER_USER_NAME = "fsHelper";

	public static final String SLASH_DELIMITER = "/";

	public static final String ENCODING = "UTF-8";

	public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm";

	public static final byte[] UTF8_BOM = new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF };

	public static final String HORIZONTAL_ROW = "<hr/>";

	public static final String YES = "YES";

	public static final String NO = "NO";

	public static final String DOLLAR_DELIMETER = "$";
	
	public static final String ESN_HEXADECIMAL_REGEX_PATTERN="[a-fA-F0-9]{8}";	
	
	public static final String MEID_HEXADECIMAL_REGEX_PATTERN  ="[a-fA-F0-9]{14,15}";
	
	
}
