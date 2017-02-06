package auth;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class LoginManager {

	  public static boolean login( String username,String password) {
	    try {
	      //此处指定了使用配置文件的“Sample”验证模块，对应的实现类为SampleLoginModule
	      LoginContext lc = new LoginContext("Sample", new SampleCallbackHandler(
	          username, password));
	      lc.login();// 如果验证失败会抛出异常
	      Subject sb = lc.getSubject();
	      System.out.println("hahahhha");
	      return true;
	    } catch (LoginException e) {
	      e.printStackTrace();
	      return false;
	    } catch (SecurityException e) {
	      e.printStackTrace();
	      return false;
	    }
	  }
	  
	  public static void main(String[] args) {
		System.out.println(login("111", "22222"));
	}

	}