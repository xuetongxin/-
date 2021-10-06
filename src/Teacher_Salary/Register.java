package Teacher_Salary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class Register extends Application {
    Stage window;
    private final GridPane gridpane = new GridPane();
    private final BorderPane borderpane = new BorderPane();
    private final Button Bt_Register = new Button("确认");
    private final Button Bt_Return = new Button("返回");
    private final Label lb1 = new Label("账户");
    private final Label lb2 = new Label("密码:");
    private final TextField Account_TextField = new TextField();
    private final TextField Passwd_TextField = new TextField();

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        window = stage;
        HBox box = new HBox();
        box.setPadding(new Insets(20,0,0,20));
        box.getChildren().add(Bt_Return);
        Account_TextField.setPromptText("8~15数字、字母 不能存在符号");
        Passwd_TextField.setPromptText("8~15数字、字母 能存在符号");   //文本域提示语
        Panel_Layout(Bt_Register, lb1, lb2, Account_TextField, Passwd_TextField, gridpane);

        borderpane.setCenter(gridpane);
        borderpane.setTop(box);

        Bt_Return.setOnAction(e -> new Login().start(stage));
        Bt_Register.setOnAction(e -> Register_Method());

        Scene scene = new Scene(borderpane, 400, 400);
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


    private void Register_Method() {

        Statement stmt1;
        ResultSet rs1 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery("select account from passwd_date");

        } catch (Exception ex) {
            ex.getStackTrace();
        }

        //System.out.println(Account_TextField.getLength());
        try {
            if (!(Account_TextField.getText().matches("")||Passwd_TextField.getText().matches(""))) {

                if (Account_TextField.getLength()>=8&&Account_TextField.getLength()<=15&&Passwd_TextField.getLength()>=8&&Passwd_TextField.getLength()<=15) {

                    try {
                        rs1.next();
                        do {

                            if (rs1.getString(1).matches(Account_TextField.getText())) {
                                System.out.println("用户存在");
                                prompt();
                                System.out.println("!!!!");
                            } else {
                                System.out.println("用户不存在");
                                Register_User();
                                System.out.println("！！！");
                                break;
                            }
                        } while (rs1.next());
                    }catch (Exception ex){ex.getStackTrace();}
                }else
                    System.out.println("账户或者密码长度小于8|大于15");

            }else
                System.out.println("账户或者密码为空");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    void prompt() {
        Pane pane = new Pane();
        Text text = new Text("用户存在");
        text.setFont(Font.font("华文行楷", 20));
        text.setX(100);
        text.setY(100);
        pane.getChildren().add(text);
        Stage stage = new Stage();
        stage.setScene(new Scene(pane, 200, 200));
        stage.show();
    }

    void Register_User() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root",
                    "xsl203457");
            PreparedStatement ps1 = con
                    .prepareStatement("insert into xsl.passwd_date (account,passwd) values (?,?)");
            ps1.setString(1, Account_TextField.getText());
            ps1.setString(2, Passwd_TextField.getText());
            ps1.executeUpdate();
            ps1.close();

            Account_TextField.clear();
            Passwd_TextField.clear();

            PreparedStatement ps2 = con
                    .prepareStatement("delete from xsl.passwd_date where account=''");
            ps2.executeUpdate();
            ps2.close();

            Register_Successful();
            System.out.println("注册成功");
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private void Register_Successful(){
        Stage window1=new Stage();
        Pane pane = new Pane();
        Text text = new Text("注册成功");
        text.setFont(Font.font("华文行楷", 30));
        text.setX(100);
        text.setY(100);
        pane.getChildren().add(text);
        window1.setScene(new Scene(pane, 300, 200));
        window1.showAndWait();
    }

}
