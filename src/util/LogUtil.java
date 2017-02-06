package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 日志工具类
 * @author zhuqingwei
 *
 */
public class LogUtil {
	private final static String FILE_PATH = "log.txt";
	private final static File logFile   = new File(FILE_PATH);
	private static void makeFile(){
		if(!logFile.exists()){
			try {
				logFile.createNewFile();
				clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void print(String str) {
		makeFile();
		FileWriter w = null;
		try {
			w = new FileWriter(logFile, true);
			w.append(str);
			System.out.println(str);
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e) {
					 e.printStackTrace();
				}
			}
		}
	}

	public static void println(String str) {
		print(str);
		print("\n");
	}
	public static void error(Class cls,String msg){
		println(cls.getName()+",error:"+msg);
	}
	public static void info(Class cls,String msg){
		println(cls.getName()+",info:"+msg);
	}
	public static void warn(Class cls,String msg){
		println(cls.getName()+",warn:"+msg);
	}
	
	public static void clear() {
		FileWriter w = null;
		try {
			w = new FileWriter(logFile, false);
			w.write("");
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e) {
					 e.printStackTrace();
				}
			}
		}
	}
	
}
