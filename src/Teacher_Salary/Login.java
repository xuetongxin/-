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
    private final StackPane stackpane = new StackPane();
    private final BorderPane borderPane = new BorderPane();
    final ImageView imageview = new ImageView(
            new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"));
    private final HBox hbox = new HBox(10);
    private final GridPane gridpane = new GridPane();
    private final Button Bt_Login = new Button("登录"); // 设置登录按钮
    private final Button Bt_SingUp = new Button("注册"); // 设置注册按钮
    final Label Account_Label = new Label("账户:"); // 设置用户名标签
    final Label Passwd_Label = new Label("密码:"); // 设置密码标签
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
        Account_Label.setStyle("-fx-font-family: '华文行楷' ;-fx-font-size: 20;-fx-text-fill: 'white'");
        Passwd_Label.setStyle("-fx-font-family: '华文行楷' ;-fx-font-size: 20;-fx-text-fill: 'white'");

        imageview.setFitHeight(810);
        imageview.setFitWidth(1540); // 背景图片属性

        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        Register.Panel_Layout(Bt_Login, Account_Label, Passwd_Label, Account_TextField, Passwd_TextField, gridpane);

        Bt_Login.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family: '华文行楷';-fx-border-color: pink");
        Bt_SingUp.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white;-fx-font-family:'华文行楷';-fx-border-color: pink");
        //Bt_Login.setOnAction(e->Bt_Login_Method());
        Bt_Login.setOnAction(e -> {new Choice().start(window);});
        Bt_SingUp.setOnAction(e -> new Register().start(window));
        Passwd_TextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Bt_Login_Method();
            }
        });

        hbox.getChildren().add(Bt_SingUp);
        borderPane.setTop(hbox);
        borderPane.setCenter(gridpane);
        stackpane.getChildren().addAll(imageview, borderPane);

        window.setX(500);
        window.setY(200);
        window.setScene(new Scene(stackpane,300,300));
        window.setHeight(500);
        window.setWidth(500);

        window.setTitle("登录");
        window.getIcons().add(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\t.png"));
        window.show();
    }

    private void Bt_Login_Method() {
        Statement stmt1;
        PreparedStatement preparedStatement;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            stmt1 = con.createStatement();
            preparedStatement=con.prepareStatement("select passwd from xsl.passwd_date where account=?");
            preparedStatement.setString(1,Account_TextField.getText());
            rs2= preparedStatement.executeQuery();

            rs1 = stmt1.executeQuery("select account from passwd_date");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        Judgement(rs1,rs2);
    }

    private void Judgement(ResultSet rs1,ResultSet rs2) {

        if ((Account_TextField.getText().matches("") || Passwd_TextField.getText().matches(""))) {
            Alert alert=new Alert(Alert.AlertType.WARNING,"账户或者密码为空");
            alert.showAndWait();
            System.out.println("账户或者密码为空");
        } else if (!(Account_TextField.getLength() >= 8 && Account_TextField.getLength() <= 15 && Passwd_TextField.getLength() >= 8 && Passwd_TextField.getLength() <= 15)) {
            Alert alert=new Alert(Alert.AlertType.WARNING,"账户或者密码长度小于8|大于15");
            alert.showAndWait();
                System.out.println("账户或者密码长度小于8|大于15");

        } else{
            Inquire_User(rs1,rs2);
        }
    }

    private void Inquire_User(ResultSet rs1,ResultSet rs2){
        boolean User_UnExit=false;
        boolean User_Exit=false;
        boolean Passwd_True=false;

        try {
            while (rs1.next()){
                if (rs1.getString(1).matches(Account_TextField.getText())) {
                    User_Exit=true;
                    try{
                        while (rs2.next()) {
                            if (rs2.getString(1).matches(Passwd_TextField.getText())) {
                                Passwd_True=true;
                                System.out.println("登录成功");
                                break;
                            }
                        }
                    }catch(Exception ex){ex.getStackTrace();}

                } else {
                    User_UnExit=true;
                    System.out.println("用户不存在");
                }
            }

            if(User_Exit){
                if (Passwd_True){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION,"欢迎进入教师工资管理系统");
                    alert.setHeaderText("你好");
                    alert.showAndWait();
                    new Choice().start(window);
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR,"密码错误");
                    alert.showAndWait();
                }

            }else if(User_UnExit){
                Alert alert=new Alert(Alert.AlertType.ERROR,"用户不存在");
                alert.showAndWait();
            }

        } catch (Exception ex) {
            ex.getStackTrace();

        }
    }


}