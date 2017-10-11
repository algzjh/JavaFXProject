package model;
	
import common.FileHandler;
import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

//import javafx.scene.Scene;
/*
Life-cycle:
1.Constructs an instance of the specified Application class
2.Calls the init() method
3.Calls the start(javafx.stage.Stage) method
4.Waits for the application to finish, which happens when either of the following occur:
    the application calls Platform.exit()
    the last window has been closed and the implicitExit attribute on Platform is true
5.Calls the stop() method
 */

public class Main extends Application {
	
	public Main() {
		checkRun();
	}
	
	//退出的时候自动将联系组和联系人的信息写入本地缓存
	private void checkRun(){
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				try {
					FileHandler.writeGroupsCache();
					FileHandler.writePersonsCache();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	@Override
	public void start(Stage loginStage) {
		try {
			LoginController loginController =  new LoginController();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);  //Launch a standalone application. 
	}
}

/*
服务器端功能要求：
（1）能够验证客户身份，接收客户端的备份通讯录的请求，能够实时备份和更新客户的通讯录。   
（2）加密存储每个用户的通讯录 
（3）能实现后台管理
客户端功能要求：
（1）能登陆连接到服务器，回应：连接成功/失败。
（2）能备份本机通讯录
（3）能实时更新本机通讯录
（4）能查询本机通讯录
*/
