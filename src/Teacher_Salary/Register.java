package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;

public class Register extends Login {
    final StackPane stackPane = new StackPane();
    private final HBox box = new HBox();
    private final Button Bt_Register = new Button("确认");
    private final Button Bt_Return = new Button("返回");
    Stage window;
    Connection con;

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

    public void start(Stage stage) {

        // TODO 自动生成的方法存根
        window = stage;
        imageView.setFitHeight(810);
        imageView.setFitWidth(1535); // 背景图片属性
        imageView.setImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"));
        Account_TextField.setPromptText("8~15数字、字母 不能存在符号");
        Passwd_TextField.setPromptText("8~15数字、字母 能存在符号");
        Passwd_TextField.setPrefColumnCount(20);

        box.setPadding(new Insets(20, 0, 0, 20));
        box.getChildren().add(Bt_Return);
        Panel_Layout(Bt_Register, Account_Label, Passwd_Label, Account_TextField, Passwd_TextField, gridpane);
        Bt_Return.setOnAction(e -> new Login().start(stage));
        Bt_Register.setOnAction(e -> Judgement(stage));
        Passwd_TextField.setOnAction(e -> Judgement(stage));
        borderPane.setCenter(gridpane);
        borderPane.setTop(box);
        stackPane.getChildren().addAll(imageView, borderPane);
        Scene scene = new Scene(stackPane,500,500);
        stage.setScene(scene);
        stage.setTitle("注册");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.show();
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
        } else {
            try {
                OperationData operationData = new OperationData();
                if (operationData.Select_User_Account(Account_TextField)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "User     Exist");
                    alert.showAndWait();
                } else
                    Register_User();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void Register_User() {
        OperationData operationData = new OperationData(con);
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457XSL@");
            PreparedStatement ps = con.prepareStatement("insert into xsl.passwd_date (account,passwd)values (?,?)");
            System.out.println("egister");
            ps.setString(1, Account_TextField.getText());
            ps.setString(2, Passwd_TextField.getText());
            System.out.println("register");
            ps.execute();
            ps.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "注册成功");
            alert.showAndWait();
            System.out.println("注册成功");
            Account_TextField.clear();
            Passwd_TextField.clear();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

}
