package Teacher_Salary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class Login extends Application {
    ImageView imageView = new ImageView(
            new Image("file:/home/ximeng/IdeaProjects/IJ_WorkSpace/out/production/IJ_WorkSpace/Teacher_Salary/image/bg.jpg"));
    Label Account_Label = new Label("账户:"); // 设置用户名标签
    Label Passwd_Label = new Label("密码:"); // 设置密码标签
    TextField Account_TextField = new TextField(); // 设置用户名填充域
    PasswordField Passwd_TextField = new PasswordField(); // 设置密码填充域 密码不回显
    final BorderPane borderPane = new BorderPane();
    final GridPane gridpane = new GridPane();
    private final StackPane stackpane = new StackPane();
    private final HBox hbox = new HBox(10);
    private final Button Bt_Login = new Button("登录"); // 设置登录按钮
    private final Button Bt_SingUp = new Button("注册"); // 设置注册按钮
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        window = stage;

        Account_TextField.setPromptText("8~15数字、字母 不能存在符号");
        Passwd_TextField.setPromptText("8~15数字、字母 能存在符号");   //文本域提示语
        Passwd_TextField.setPrefColumnCount(20);    // 首文本长度

        Account_Label.setStyle("-fx-font-family: '华文行楷' ;-fx-font-size: 20;-fx-text-fill: 'white'");
        Passwd_Label.setStyle("-fx-font-family: '华文行楷' ;-fx-font-size: 20;-fx-text-fill: 'white'");

        imageView.setFitHeight(1080);
        imageView.setFitWidth(1980); // 背景图片属性

        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        Register.Panel_Layout(Bt_Login, Account_Label, Passwd_Label, Account_TextField, Passwd_TextField, gridpane);

        Bt_Login.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family: '华文行楷';-fx-border-color: #ffc0c0");
        Bt_SingUp.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family:'华文行楷';-fx-border-color: pink");

        Bt_Login.setOnAction(e -> new Choice().start(window));
        //Bt_Login.setOnAction(e-> Judgement(stage));
        Bt_SingUp.setOnAction(e -> new Register().start(window));

        hbox.getChildren().add(Bt_SingUp);
        borderPane.setTop(hbox);
        borderPane.setCenter(gridpane);
        stackpane.getChildren().addAll(imageView, borderPane);

        window.setScene(new Scene(stackpane, 500, 500));
        window.setMinHeight(500);
        window.setMinWidth(500);
        window.setTitle("登录");
        window.getIcons().add(new Image("file:/home/ximeng/IdeaProjects/IJ_WorkSpace/out/production/IJ_WorkSpace/Teacher_Salary/image/t.png"));
        window.show();
    }

    void Judgement(Stage stage) {

        if ((Account_TextField.getText().matches("") || Passwd_TextField.getText().matches(""))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "账户或者密码为空");
            alert.showAndWait();
            System.out.println("账户或者密码为空");
        } else if (!(Account_TextField.getLength() >= 8 && Account_TextField.getLength() <= 15 && Passwd_TextField.getLength() >= 8 && Passwd_TextField.getLength() <= 15)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "账户或者密码长度小于8|大于15");
            alert.showAndWait();
            System.out.println("账户或者密码长度小于8|大于15");
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
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}