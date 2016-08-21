package nattable;

import org.eclipse.osgi.util.NLS;

public class Message extends NLS {
	private static final String BUNDLE_NAME = "nattable.messages"; //$NON-NLS-1$
	
	
	 static {
		    NLS.initializeMessages(BUNDLE_NAME, Message.class);
	}
	 
	 //Column Row Content
	 public static String columnData;
	 public static String columnitem;
	 public static String columnBigType;
	 public static String columnSmallType;
	 public static String columnPrice;
	 public static String columnCatagory;
	 public static String columnBanlance;
	 public static String columnContent;
	 
	 public static String searchLabel;
	 public static String selectDay;
	 public static String search;
	 public static String today; 
	 
	 
}