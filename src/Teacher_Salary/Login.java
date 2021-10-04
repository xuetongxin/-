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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Stage window;
    private final StackPane stackpane = new StackPane();
    private final ImageView imageview = new ImageView(
            new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\R-C.jpg"));
    private final HBox hbox = new HBox(10);
    private final GridPane gridpane = new GridPane();
    private final Button Bt_Login = new Button("Login"); // 设置登录按钮
    private final Button Bt_Singup = new Button("SingUp"); // 设置注册按钮
    private final Label lb1 = new Label("account:"); // 设置用户名标签
    private final Label lb2 = new Label("passwd:"); // 设置密码标签
    private TextField txfd1 = null; // 设置用户名填充域
    private PasswordField txfd2 = null; // 设置密码填充域 密码不回显

    Statement stmt1 = null;
    Statement stmt2 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;

    public void start(Stage stage) {
        window = stage;
        Bt_Login.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-border-color: white");
        Bt_Singup.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-border-color: white");

        Txfd1_attribute(); // 设置用户名填充属性
        Txfd2_attribute(); // 设置密码填充属性
        Body();
        //Bt_Login.setOnAction(e->Bt_Login_Method());
        Bt_Login.setOnAction(e -> new Choice().start(window));
        Bt_Singup.setOnAction(e -> new Register().start(window));

        hbox.getChildren().add(Bt_Singup);
        stackpane.getChildren().addAll(imageview, hbox, gridpane);
        Scene scene = new Scene(stackpane, 400, 400);
        window.setX(500);
        window.setY(200);
        window.setScene(scene);
        window.setTitle("login");
        window.show();

    }

    private void Body() {
        imageview.setFitHeight(810);
        imageview.setFitWidth(1535); // 背景图片属性
        hbox.setAlignment(Pos.BOTTOM_LEFT); // 注册按钮位置设置在左下
        hbox.setPadding(new Insets(0, 0, 10, 10));
        Register.Body(Bt_Login, lb1, lb2, txfd1, txfd2, gridpane);
    }


    private void Bt_Login_Method(){
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

        try {
            do{
                rs1.next();

                if (rs1.getString(1).matches(txfd1.getText())) {

                    do{
                        rs2.next();

                        if (rs2.getString(1).matches(txfd2.getText())) {

                            new Choice().start(window);

                        }

                    }while (rs2.next());
                } else {
                    System.out.print("用户不存在");
                }

            }while (rs1.next());
        } catch (Exception ex) {
                ex.getStackTrace();
        }
    }

    public void Txfd1_attribute() {
        txfd1 = new TextField() {
            public void replaceText(int start, int end, String text) {
                if (!text.matches("[a~z .,/]")) {
                    super.replaceText(start, end, text);
                }
            }

            public void replaceSelection(String text) {
                if (text.matches("[a~z .,/]")) {
                    super.replaceSelection(text);
                }
            }
        };
        txfd1.setPromptText("8~20数字、字母 不能存在符号");
    }

    public void Txfd2_attribute() {
        txfd2 = new PasswordField() {
            @Override
            public void replaceText(int start, int end, String text) {
                if (!text.matches("[. /:]")) {
                    super.replaceText(start, end, text);
                }
            }

            public void replaceSelection(String text) {
                if (!text.matches("[. /;:]")) {
                    super.replaceSelection(text);
                }
            }
        };
        txfd2.setPromptText("8~20数字、字母 能存在符号");   //文本域提示语
        txfd2.setPrefColumnCount(13);    // 文本域长度
    }

}
