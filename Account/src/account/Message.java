package account;

import org.eclipse.equinox.internal.app.Messages;
import org.eclipse.osgi.util.NLS;

public class Message extends NLS {
	private static final String BUNDLE_NAME = "account.messages"; //$NON-NLS-1$
	
	
	 static {
		    NLS.initializeMessages(BUNDLE_NAME, Message.class);
	}
	
	//title
	public static String title;
	public static String titlefile;
	public static String titleAction;
	public static String inputContent;
	
	//bank
	public static String woribank;
	public static String sinhanbank;
	public static String hanabank;
	public static String kiupbank;
	public static String nonghyup;
	public static String saemayul;
	public static String scbank;
	public static String suhyup;
	public static String yuchaeguk;
	public static String kbbank;
	
	//card
	public static String woricard;
	public static String sinhancard;
	public static String BCcard;
	public static String IBKcard;
	public static String hanacard;
	
	//wallet
	public static String Mywallet;
	
	//catagory
	public static String allView;
	public static String wallet;
	public static String bank;
	public static String sinyoungcard;
	public static String Balance;
	
	//menu
	public static String account;
	public static String major;
	public static String minor;
	public static String content;
	public static String incom_outcom;
	public static String detail;
	public static String selfWrite;
	public static String addAccount;
	public static String ChoseType;
	public static String ChoseWallet;
	public static String ChoseSinyoungCard;
	public static String SelfWrite2;
	public static String ChoseBank;
	
	//Dialog
	public static String addAccountCatagory;
	public static String addContent;
	public static String addpurchaseCatagory;
	
	//addpurchaseCatagory 
	public static String addpurchaseLabel;
	public static String add;
	public static String remove;
	public static String modify;
	public static String addpurchaseCatagoryList;
	public static String purchaseCatagoryList;
	
}
