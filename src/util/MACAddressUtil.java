package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Locale;

import com.mysql.jdbc.StringUtils;

import bean.MacInfo;

public class MACAddressUtil {
	private static Formatter formatter = new Formatter();

	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * windows 7 专用 获取MAC地址
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws Exception
	 */
	public static MacInfo getWin7MACAddress() throws Exception {
		// 获取本地IP对象

		InetAddress ia = InetAddress.getLocalHost();
		String ip = ia.getHostAddress();
		System.out.println("ip address:" + ip);
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}
		String macStr = sb.toString().toUpperCase();
		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return new MacInfo(ip, macStr);

	}

	public static MacInfo getWinMACAddress() throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		NetworkInterface ni = NetworkInterface.getByInetAddress(address);
		byte[] mac = ni.getHardwareAddress();
		if (mac == null) {
			mac = ni.getInetAddresses().nextElement().getAddress();
		}
		String sIP = address.getHostAddress();
		String sMAC = "";
		for (int i = 0; i < mac.length; i++) {
			sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i], (i < mac.length - 1) ? "-" : "").toString();
		}
		return new MacInfo(sIP, sMAC);
	}

	public static MacInfo getUnixMACAddress() throws Exception {
		String mac = null;
		String ip = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			// linux下的命令，一般取eth0作为本地主网卡
			process = Runtime.getRuntime().exec("ifconfig eth0");
			// 显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int indexMac = -1;
			int indexIp = -1;
			while ((line = bufferedReader.readLine()) != null) {
				// 寻找标示字符串[hwaddr]
				indexMac = line.toLowerCase().indexOf("hwaddr");
				indexIp = line.toLowerCase().indexOf("inet addr:");

				if (indexMac >= 0) {// 找到了
					// 取出mac地址并去除2边空格
					mac = line.substring(indexMac + "hwaddr".length() + 1).trim();
				}
				if (indexIp >= 0) {// 找到了
					// 取出ip地址并去除2边空格
					ip = line.substring(indexIp + "inet addr:".length() + 1).trim();
				}
				if (indexMac >= 0 && indexIp >= 0) {
					break;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return new MacInfo(ip, mac);
	}

	public static MacInfo getMacInfo() throws Exception {
		String os = getOSName();
		System.out.println(os);
		MacInfo result = null;
		if (os.equals("windows 7")) {
			result = getWin7MACAddress();
		} else if (os.startsWith("windows")) {
			// 本地是windows
			result = getWinMACAddress();
		} else {
			// 本地是非windows系统 一般就是unix
			result = getUnixMACAddress();
		}
		if (StringUtils.isEmptyOrWhitespaceOnly(result.getMac())) {
			throw new Exception("mac 地址不能为空！");
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getMacInfo().getMac());
	}
}
