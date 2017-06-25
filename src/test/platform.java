package test;





import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import core.ImageProcesser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;





public class platform extends Application {



	private final Desktop desktop = Desktop.getDesktop();

	

	private static String FILEPATH;

	

	VBox rootPane = new VBox();

	

	VBox menuPane = new VBox();

	

	BorderPane homePage = new BorderPane();

	



	//默认字符集方式创建字符转换器对象
			ImageProcesser processer = new ImageProcesser();
			//ImageProcesser(char[] charset);  指定字符集创建对象

	@Override
	public void start(Stage stage) throws Exception {

			homePage.setPadding(new Insets(0,0,0,0));

			rootPane.setPadding(new Insets(0,0,0,0));

			rootPane.setSpacing(0);

			

			//菜单栏

			MenuBar menuBar = new MenuBar();

			Menu menuFile = new Menu("菜单");

			//菜单目录

			MenuItem open = new MenuItem("导入本地文件");

			open.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));

			

			

			open.setOnAction((t) -> {

				//等待添加函数

				try{

						FileChooser fileChooser = new FileChooser();

						fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("图片","*.jpg"),

								new FileChooser.ExtensionFilter("所有文件", "*.*"));	

						fileChooser.setTitle("选择本地文件");

						fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));//初始化路径，为当前项目路径

						File file = fileChooser.showOpenDialog(stage);

						if (file != null) {
							
							
//							openFile(file);
							
							
							FileChooser fileChooser1 = new FileChooser();

						    fileChooser1.setTitle("保存文件");

						    fileChooser1.setInitialDirectory(new File(System.getProperty("user.dir")));//初始化路径，为当前项目路径

						    fileChooser1.getExtensionFilters().addAll(

						    		new FileChooser.ExtensionFilter("txt", "*.txt"),

						    		new FileChooser.ExtensionFilter("所有文件", "*.*")

						    		);

						    File file1 = fileChooser1.showSaveDialog(stage);

						    if (file1 != null) {

						        try {

						        	processer.toBitmapConvert(file).saveAsTxt(file1.getName());
						        	openFile(file1);


						        } catch (Exception ex) {

						             System.out.println(ex.getMessage());

						        }
						    }
						

					}

					}catch(Exception e){

						

						System.out.println("错误"+ "打开文件失败，请重试");

					}

			    //vbox.setVisible(false);

			});

			

			MenuItem save = new MenuItem("保存");

			save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

			

			

			save.setOnAction((t) -> {

				try{

					File file = new File(FILEPATH);

					if (file != null) {

				        try {

//				        	writeObjectToFile(students,FILEPATH);

	

				        } catch (Exception ex) {

				             System.out.println(ex.getMessage());

				        }

				    }

				}catch(NullPointerException e){

					System.out.println("错误,请打开一个文件");

				}

				

			});

			

			MenuItem saveAs = new MenuItem("另保存");

			saveAs.setAccelerator(KeyCombination.keyCombination("Alt+S"));

			saveAs.setOnAction((t) -> {

				//等待添加函数

				FileChooser fileChooser1 = new FileChooser();

			    fileChooser1.setTitle("保存文件");

			    fileChooser1.setInitialDirectory(new File(System.getProperty("user.dir")));//初始化路径，为当前项目路径

			    fileChooser1.getExtensionFilters().addAll(

			    		new FileChooser.ExtensionFilter("DAT", "*.dat"),

			    		new FileChooser.ExtensionFilter("所有文件", "*.*")

			    		);

			    File file = fileChooser1.showSaveDialog(stage);

			    if (file != null) {

			        try {

//			        	writeObjectToFile(students,file.getAbsolutePath());



			        } catch (Exception ex) {

			             System.out.println(ex.getMessage());

			        }

			    }

			    //vbox.setVisible(false);

			});

			menuFile.getItems().addAll(open,save,saveAs);

			

			Menu menuMessage = new Menu("安全小贴士");


	

			Menu menuHelp = new Menu("帮助");

			MenuItem help = new MenuItem("帮助文档");

			menuHelp.getItems().add(help);

			help.setOnAction((e)->{

				

				try {

					desktop.open(new File("help.txt"));



				} catch (Exception e1) {

					// TODO 自动生成的 catch 块


				}//打开帮助文档

			});

			menuBar.getMenus().addAll(menuFile, menuMessage, menuHelp);

			menuBar.setMinHeight(30);
	

			stage.setTitle("字符画");



	        Scene scene = new Scene(rootPane, 1000, 750);

	        

	        ((VBox) scene.getRoot()).getChildren().addAll(menuBar,homePage);

	        stage.setScene(scene);


	        stage.setResizable(false);//不可改变大小

//	        stage.initStyle(StageStyle.TRANSPARENT);//去掉窗口边框

	        stage.show();

		

	}

	

//	信息弹窗，可以弹出提示，错误等

	public static void messageWindow(String title,String message) {

		Stage messageWindow=new Stage();

		Label messageLabel = new Label(message);

		messageLabel.setAlignment(Pos.CENTER);

		Scene messageScene=new Scene(messageLabel,300,100);

		messageWindow.setTitle(title);

		messageWindow.setScene(messageScene);

		messageWindow.show();

		

	}

	public void openFile(File file){

		

		

		

		try {

			String exportFilePath=file.getAbsolutePath();

			desktop.open(file);//打开文件

			} catch (IOException ex) {

				platform.messageWindow("错误", "打开文件失败");

			}

	

	

}




	



}