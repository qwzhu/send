import java.net.URL;

import util.LogUtil;

public class JniDll {
	/**
	 * findWindow
	 * 
	 * @param str
	 *            参数文件
	 * @return 1： 成功找到 0： 没有找到
	 */
	public static native int findWindow(String str);

	/**
	 * 参看windows api
	 * 
	 * LRESULT SendMessage( __in HWND hWnd, //这个参数不用传
	 * 
	 * __in UINT Msg,
	 * 
	 * __in WPARAM wParam,
	 * 
	 * __in LPARAM lParam );
	 * 
	 * hWnd 参数：窗口句柄。窗口可以是任何类型的屏幕对象。
	 * 
	 * Msg 参数：用于区别其他消息的常量值。
	 * 
	 * wParam 参数：通常是一个与消息有关的常量值，也可能是窗口或控件的句柄。
	 * 
	 * lParam 参数：通常是一个指向内存中数据的指针。
	 */
	public static native int sendMessage(String Msg, int wParam, long lParam);

	public static native int sendTab();
	
	public static native int sendUp();
	
	public static native int sendDown();

	
	
	public static int upload(String phone,String mobile,String company,String name,String address) throws InterruptedException {
		System.loadLibrary("MFCDLL");
		int n = JniDll.findWindow("图片打单系统-生产环境-7.6.1");
		if(n!= 1){
			return -1;
		}
		System.out.println("ret:" + n);
		Thread.sleep(100);

		JniDll.sendMessage(phone, 0x0001, 0);

		Thread.sleep(100);
		JniDll.sendMessage(mobile, 0x0001, 0);
		Thread.sleep(100);

		JniDll.sendMessage(company, 0x0001, 0);
		Thread.sleep(100);

		JniDll.sendMessage(name, 0x0001, 0);

		Thread.sleep(100);

		JniDll.sendMessage(address, 0x0001, 0);
		
        return 1;
	}
	public static void main(String[] args) {
		try {
			String pp = System.getProperty("java.library.path");
			URL ss = JniDll.class.getResource("/");
			System.out.println(ss);
			//System.setProperty(key, value)
System.out.println(pp);
			upload("phone", "mobile", "company", "name", "address");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LogUtil.error(JniDll.class, e.getMessage());
		}
	}


}
