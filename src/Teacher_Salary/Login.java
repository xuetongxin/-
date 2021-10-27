package Teacher_Salary;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login extends OperationData {
    Label Account_Label = new Label("�˻�:"); // �����û�����ǩ
    Label Passwd_Label = new Label("����:"); // ���������ǩ
    TextField Account_TextField = new TextField(); // �����û��������
    PasswordField Passwd_TextField = new PasswordField(); // ������������� ���벻����
    final BorderPane borderPane = new BorderPane();
    final GridPane gridpane = new GridPane();
    private final MenuBar menuBar = new MenuBar();
    private Stage window;
    private final StackPane stackpane = new StackPane();
    private final Button Bt_Login = new Button("��¼"); // ���õ�¼��ť
    private final Button Bt_SingUp = new Button("ע��"); // ����ע�ᰴť

    public void start(Stage stage) {
        window = stage;
        Menu menu = new Menu("help");
        MenuItem menuItem_SignUp = new MenuItem("Sign Up");
        MenuItem menuItem_About = new MenuItem("About");
        MenuItem menuItem_Exit = new MenuItem("Exit");
        menu.getItems().addAll(menuItem_SignUp, menuItem_About, menuItem_Exit);
        menuBar.getMenus().add(menu);
        menuItem_SignUp.setOnAction(e -> new Register().start(window));
        menuItem_About.setOnAction(e -> About_Menu());
        menuItem_Exit.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit system?");
            alert.showAndWait();
            if (alert.getResult().getButtonData().isDefaultButton())
                stage.close();
        });
        imageView.setFitHeight(1080);
        imageView.setFitWidth(1980); // ����ͼƬ����
        Account_TextField.setPromptText("8~15���֡���ĸ ���ܴ��ڷ���");
        Passwd_TextField.setPromptText("8~15���֡���ĸ �ܴ��ڷ���");   //�ı�����ʾ��
        Passwd_TextField.setPrefColumnCount(20);    // ���ı�����
        Account_Label.setStyle("-fx-font-family: '�����п�' ;-fx-font-size: 20;-fx-text-fill: 'white'");
        Passwd_Label.setStyle("-fx-font-family: '�����п�' ;-fx-font-size: 20;-fx-text-fill: 'white'");
        Bt_Login.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family: '�����п�';-fx-border-color: #ffc0c0");
        Bt_SingUp.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family:'�����п�';-fx-border-color: pink");
        Register.Panel_Layout(Bt_Login, Account_Label, Passwd_Label, Account_TextField, Passwd_TextField, gridpane);

        Bt_Login.setOnAction(e -> new Choice().start(window));
        //Bt_Login.setOnAction(e -> Judgement(stage));

        borderPane.setTop(menuBar);
        borderPane.setCenter(gridpane);
        stackpane.getChildren().addAll(imageView, borderPane);

        window.setScene(new Scene(stackpane, 550, 500));
        window.setMinHeight(500);
        window.setMinWidth(500);
        window.setTitle("��¼");
        window.getIcons().add(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\t.png"));
        window.show();
    }

    void Judgement(Stage stage) {

        if ((Account_TextField.getText().matches("") || Passwd_TextField.getText().matches(""))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "�˻���������Ϊ��");
            alert.showAndWait();
            System.out.println("�˻���������Ϊ��");
        } else if (!(Account_TextField.getLength() >= 8 && Account_TextField.getLength() <= 15 && Passwd_TextField.getLength() >= 8 && Passwd_TextField.getLength() <= 15)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "�˻��������볤��С��8|����15");
            alert.showAndWait();
            System.out.println("�˻��������볤��С��8|����15");
        } else
            Login_System(stage);
    }

    private void Login_System(Stage stage) {
        OperationData operationData = new OperationData();
        try {
            if (operationData.Select_User_Account(Account_TextField) && operationData.Select_User_Password(Account_TextField, Passwd_TextField)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Welcome to Teacher Salary Management System ");
                alert.showAndWait();
                new Choice().start(stage);
                super.Login_Log(Account_TextField.getText());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void About_Menu() {
        Group group = new Group();
        Stage stage = new Stage();
        Text text = new Text();
        text.setX(5);
        text.setY(20);
        text.setText("""
                ��ʦ���ʹ���ϵͳ:\s
                ϵͳ�汾\t1.0
                Դ��ɴ�������վ��ȡ
                https://github.com/xuetongxin/IJ_WorkSpace.git""");
        group.getChildren().addAll(text);
        stage.setScene(new Scene(group, 400, 100));
        stage.setResizable(false);
        stage.showAndWait();
    }

}