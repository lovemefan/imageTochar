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

	



	//Ĭ���ַ�����ʽ�����ַ�ת��������
			ImageProcesser processer = new ImageProcesser();
			//ImageProcesser(char[] charset);  ָ���ַ�����������

	@Override
	public void start(Stage stage) throws Exception {

			homePage.setPadding(new Insets(0,0,0,0));

			rootPane.setPadding(new Insets(0,0,0,0));

			rootPane.setSpacing(0);

			

			//�˵���

			MenuBar menuBar = new MenuBar();

			Menu menuFile = new Menu("�˵�");

			//�˵�Ŀ¼

			MenuItem open = new MenuItem("���뱾���ļ�");

			open.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));

			

			

			open.setOnAction((t) -> {

				//�ȴ���Ӻ���

				try{

						FileChooser fileChooser = new FileChooser();

						fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ͼƬ","*.jpg"),

								new FileChooser.ExtensionFilter("�����ļ�", "*.*"));	

						fileChooser.setTitle("ѡ�񱾵��ļ�");

						fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));//��ʼ��·����Ϊ��ǰ��Ŀ·��

						File file = fileChooser.showOpenDialog(stage);

						if (file != null) {
							
							
//							openFile(file);
							
							
							FileChooser fileChooser1 = new FileChooser();

						    fileChooser1.setTitle("�����ļ�");

						    fileChooser1.setInitialDirectory(new File(System.getProperty("user.dir")));//��ʼ��·����Ϊ��ǰ��Ŀ·��

						    fileChooser1.getExtensionFilters().addAll(

						    		new FileChooser.ExtensionFilter("txt", "*.txt"),

						    		new FileChooser.ExtensionFilter("�����ļ�", "*.*")

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

						

						System.out.println("����"+ "���ļ�ʧ�ܣ�������");

					}

			    //vbox.setVisible(false);

			});

			

			MenuItem save = new MenuItem("����");

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

					System.out.println("����,���һ���ļ�");

				}

				

			});

			

			MenuItem saveAs = new MenuItem("����");

			saveAs.setAccelerator(KeyCombination.keyCombination("Alt+S"));

			saveAs.setOnAction((t) -> {

				//�ȴ���Ӻ���

				FileChooser fileChooser1 = new FileChooser();

			    fileChooser1.setTitle("�����ļ�");

			    fileChooser1.setInitialDirectory(new File(System.getProperty("user.dir")));//��ʼ��·����Ϊ��ǰ��Ŀ·��

			    fileChooser1.getExtensionFilters().addAll(

			    		new FileChooser.ExtensionFilter("DAT", "*.dat"),

			    		new FileChooser.ExtensionFilter("�����ļ�", "*.*")

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

			

			Menu menuMessage = new Menu("��ȫС��ʿ");


	

			Menu menuHelp = new Menu("����");

			MenuItem help = new MenuItem("�����ĵ�");

			menuHelp.getItems().add(help);

			help.setOnAction((e)->{

				

				try {

					desktop.open(new File("help.txt"));



				} catch (Exception e1) {

					// TODO �Զ����ɵ� catch ��


				}//�򿪰����ĵ�

			});

			menuBar.getMenus().addAll(menuFile, menuMessage, menuHelp);

			menuBar.setMinHeight(30);
	

			stage.setTitle("�ַ���");



	        Scene scene = new Scene(rootPane, 1000, 750);

	        

	        ((VBox) scene.getRoot()).getChildren().addAll(menuBar,homePage);

	        stage.setScene(scene);


	        stage.setResizable(false);//���ɸı��С

//	        stage.initStyle(StageStyle.TRANSPARENT);//ȥ�����ڱ߿�

	        stage.show();

		

	}

	

//	��Ϣ���������Ե�����ʾ�������

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

			desktop.open(file);//���ļ�

			} catch (IOException ex) {

				platform.messageWindow("����", "���ļ�ʧ��");

			}

	

	

}




	



}