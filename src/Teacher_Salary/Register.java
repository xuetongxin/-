package Teacher_Salary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class Register extends Application {
    Stage window;
    final ImageView imageView=new ImageView();
    final StackPane stackPane=new StackPane();
    private final HBox box = new HBox();
    private final GridPane gridpane = new GridPane();
    private final BorderPane borderpane = new BorderPane();
    private final Button Bt_Register = new Button("确认");
    private final Button Bt_Return = new Button("返回");
    private final Label Account_Label = new Label("账户");
    private final Label Passwd_Label = new Label("密码:");
    private final TextField Account_TextField = new TextField();
    private final TextField Passwd_TextField = new TextField();

    @Override
    public void start(Stage stage) {


        // TODO 自动生成的方法存根
        window = stage;

        imageView.setFitHeight(810);
        imageView.setFitWidth(1535); // 背景图片属性
        imageView.setImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"));

        box.setPadding(new Insets(20,0,0,20));
        box.getChildren().add(Bt_Return);
        Account_TextField.setPromptText("8~15数字、字母 不能存在符号");
        Passwd_TextField.setPromptText("8~15数字、字母 能存在符号");   //文本域提示语
        Panel_Layout(Bt_Register, Account_Label, Passwd_Label, Account_TextField, Passwd_TextField, gridpane);

        borderpane.setCenter(gridpane);
        borderpane.setTop(box);

        Bt_Return.setOnAction(e -> new Login().start(stage));
        Bt_Register.setOnAction(e -> Register_Method(Account_TextField));
        Passwd_TextField.setOnAction(e->Register_Method(Account_TextField));



        stackPane.getChildren().addAll(imageView,borderpane);
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.setTitle("注册");
        stage.show();
    }

    static void Panel_Layout(Button register, Label lb1, Label lb2, TextField txfd1, TextField txfd2, GridPane gridpane) {
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.add(lb1, 0, 0);
        gridpane.add(txfd1, 1, 0);
        gridpane.add(lb2, 0, 1);
        gridpane.add(txfd2, 1, 1);
        gridpane.add(register, 1, 2);
        gridpane.setAlignment(Pos.CENTER);
    }


    private void Register_Method(TextField account_TextField) {


            if (!(Account_TextField.getText().matches("")||Passwd_TextField.getText().matches(""))) {

                if (Account_TextField.getLength()>=8&&Account_TextField.getLength()<=15&&Passwd_TextField.getLength()>=8&&Passwd_TextField.getLength()<=15) {
                    Judgement(account_TextField);
                }else{
                    Alert alert=new Alert(Alert.AlertType.WARNING,"账户或者密码长度小于8|大于15");
                    alert.showAndWait();
                }

            }else{
                Alert alert=new Alert(Alert.AlertType.WARNING,"账号或者密码为空");
                alert.showAndWait();
            }

    }
    private void Judgement(TextField account_TextField){
        Statement stmt1;
        ResultSet rs1 = null;
        try {
            System.out.println("连接数据库");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery("select account from passwd_date");
            System.out.println("连接成功");
        } catch (Exception ex) {
            ex.getStackTrace();
        }

        try {
            boolean boole = false;
            rs1.next();
            do {
                if (rs1.getString(1).matches(Account_TextField.getText())) {
                    boole=true;
                }
            } while (rs1.next());

            if(boole){
                System.out.println("用户存在");
                Alert alert=new Alert(Alert.AlertType.ERROR,"用户存在");
                alert.showAndWait();
            }else{
                System.out.println("用户不存在");
                Register_User(account_TextField);
            }

        }catch (Exception ex){ex.getStackTrace();}
    }

    void Register_User(TextField account_TextField) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root",
                    "xsl203457");
            PreparedStatement ps1 = con.prepareStatement("insert into xsl.passwd_date (account,passwd) values (?,?)");
            ps1.setString(1, Account_TextField.getText());
            ps1.setString(2, Passwd_TextField.getText());
            ps1.executeUpdate();
            ps1.close();

            Alert alert=new Alert(Alert.AlertType.INFORMATION,"注册成功");
            alert.showAndWait();
            System.out.println("注册成功");

            Account_TextField.clear();
            Passwd_TextField.clear();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

}
