package kr.co.gda.qr;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class QRService {
	
	public String getUserName() {
		return "BaekYeongJae";
	}
	
	public String getSystemInfo() {
		StringBuffer sf = new StringBuffer();
		String s = System.getProperty("os.name");
		sf.append(s);
		s = System.getProperty("os.version");
		sf.append(","+s);
		return sf.toString();
	}
	
	public String  getNetworkInfo() throws UnknownHostException {
		StringBuffer sf = new StringBuffer();
		InetAddress address = InetAddress.getLocalHost();
		String s = address.getHostName();
		sf.append(s);
		s = address.getHostAddress();
		sf.append(","+s);
		return sf.toString();
	}
	
	// 장치의 이름
	
	// 장치의 OS
	
	// 장치의 IP
	
	// 장치의 GPS
	
	// ... 

}
