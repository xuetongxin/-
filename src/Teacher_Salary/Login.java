package Teacher_Salary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends Application {
    private final StackPane stackpane = new StackPane();
    private final BorderPane borderPane = new BorderPane();
    private final ImageView imageview = new ImageView(
            new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\b.jpg"));
    private final HBox hbox = new HBox(10);
    private final GridPane gridpane = new GridPane();
    private final Button Bt_Login = new Button("登录"); // 设置登录按钮
    private final Button Bt_SingUp = new Button("注册"); // 设置注册按钮
    private final Label Account_Label = new Label("账户:"); // 设置用户名标签
    private final Label Passwd_Label = new Label("密码:"); // 设置密码标签
    private final TextField Account_TextField = new TextField(); // 设置用户名填充域
    private final PasswordField Passwd_TextField = new PasswordField(); // 设置密码填充域 密码不回显

    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        window = stage;

        Account_TextField.setPromptText("8~15数字、字母 不能存在符号");
        Passwd_TextField.setPromptText("8~15数字、字母 能存在符号");   //文本域提示语
        Passwd_TextField.setPrefColumnCount(13);    // 首文本长度
        Account_Label.setStyle("-fx-font-family: '华文行楷' ;-fx-font-size: 20");
        Passwd_Label.setStyle("-fx-font-family: '华文行楷' ;-fx-font-size: 20");

        imageview.setFitHeight(810);
        imageview.setFitWidth(1535); // 背景图片属性
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        Register.Panel_Layout(Bt_Login, Account_Label, Passwd_Label, Account_TextField, Passwd_TextField, gridpane);
        Bt_Login.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family: '华文行楷';-fx-border-color: pink");
        Bt_SingUp.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family:'华文行楷';-fx-border-color: pink");

        Passwd_TextField.setOnAction(e->{new Choice().start(window);LogIn_Successful();});
        Bt_Login.setOnAction(e->Bt_Login_Method());

       // Bt_Login.setOnAction(e -> {new Choice().start(window);LogIn_Successful();});
        Bt_SingUp.setOnAction(e -> new Register().start(window));

        hbox.getChildren().add(Bt_SingUp);
        borderPane.setTop(hbox);
        borderPane.setCenter(gridpane);
        stackpane.getChildren().addAll(imageview, borderPane);

        window.setX(500);
        window.setY(200);
        window.setScene(new Scene(stackpane, 400, 400));
        window.setTitle("登录");
        window.getIcons().add(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\t.png"));
        window.show();
    }

    private void Bt_Login_Method() {
        Statement stmt1 = null;
        Statement stmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            rs1 = stmt1.executeQuery("select account from passwd_date");
            rs2 = stmt2.executeQuery("select passwd from passwd_date");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        Inquire_LogIN(rs1,rs2);
    }

    private void Inquire_LogIN(ResultSet rs1,ResultSet rs2) {
        if (!(Account_TextField.getText().matches("") || Passwd_TextField.getText().matches(""))) {
            if (Account_TextField.getLength() >= 8 && Account_TextField.getLength() <= 15 && Passwd_TextField.getLength() >= 8 && Passwd_TextField.getLength() <= 15) {

                try {
                    rs1.next();
                    rs2.next();
                    do {

                        if (rs1.getString(1).matches(Account_TextField.getText())) {

                            do {

                                if (rs2.getString(1).matches(Passwd_TextField.getText())) {

                                    System.out.println("登录成功");
                                    LogIn_Successful();
                                    new Choice().start(window);

                                }

                            } while (rs2.next());
                        } else {
                            System.out.println("用户不存在");
                        }

                    } while (rs1.next());
                } catch (Exception ex) {
                    ex.getStackTrace();
                }
            } else
                System.out.println("账户或者密码长度小于8|大于15");
        } else
            System.out.println("账户或者密码为空");
    }

    private void LogIn_Successful() {
        Stage window1 = new Stage();
        Pane pane = new Pane();
        Text text = new Text("登陆成功");
        text.setStyle("-fx-font-family: '华文行楷';-fx-font-size: 20");
        text.setX(120);
        text.setY(100);
        pane.getChildren().add(text);
        window1.setScene(new Scene(pane, 300, 200));
        window1.setX(550);
        window1.setY(300);
        window1.showAndWait();
    }

}