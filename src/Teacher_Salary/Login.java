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

    // --注释掉检查 (2021/10/5 2:34):p// --注释掉检查 (2021/10/5 2:34):rivate Statement stmt1;
    private Statement stmt2;
// --注释掉检查 START (2021/10/5 2:34):
//    // --注释掉检查 (2021/10/5 2:34):private ResultSet rs1 = null;
//    private ResultSet rs2 = null;
// --注释掉检查 STOP (2021/10/5 2:34)

    public void start(Stage stage) {
        window = stage;
        Bt_Login.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-border-color: white");
        Bt_Singup.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-border-color: white");

        Txfd1_attribute(); // 设置用户名填充属性
        Txfd2_attribute(); // 设置密码填充属性
        body();
        Bt_Login.setOnAction(e->new Choice().start(window));
        Bt_Singup.setOnAction(e->new Register().start(window));

        hbox.getChildren().add(Bt_Singup);
        stackpane.getChildren().addAll(imageview, hbox, gridpane);
        Scene scene = new Scene(stackpane, 400, 400);
        window.setX(500);
        window.setY(200);
        window.setScene(scene);
        window.setTitle("login");
        window.show();

    }


    private void body() {
        imageview.setFitHeight(810);
        imageview.setFitWidth(1535); // 背景图片属性
        hbox.setAlignment(Pos.BOTTOM_LEFT); // 注册按钮位置设置在左下
        hbox.setPadding(new Insets(0, 0, 10, 10));
        Register.Body(Bt_Login, lb1, lb2, txfd1, txfd2, gridpane);
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
