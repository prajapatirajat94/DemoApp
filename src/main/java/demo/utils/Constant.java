package demo.utils;

import java.util.ArrayList;
import java.util.List;

public class Constant {

public static final int Time_Out= 20;
public static final String Get_Login_Title ="Account Login";
public static final String Get_Accountpage_Title ="My Account";
public static final int Account_Section = 4;
public static List<String> getAccountSectionlist() {
	List<String>accountsList = new ArrayList<String>();
	accountsList.add("My Account");
	accountsList.add("My Orders");
	accountsList.add("My Affiliate Account");
	accountsList.add("Newsletter");
	return accountsList;
}
}
