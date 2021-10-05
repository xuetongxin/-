package Teacher_Salary;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class Register extends Application {
    Stage window;
    private final GridPane gridpane = new GridPane();
    private final BorderPane borderpane = new BorderPane();
    private final Button Bt_Register = new Button("Register");
    private final Button Bt_Return = new Button("Return");
    private final Label lb1 = new Label("AccountName:");
    private final Label lb2 = new Label("Passwd:");
    private final TextField Account_TextField = new TextField();
    private final TextField Passwd_TextField = new TextField();

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        window = stage;
        Scene scene = new Scene(borderpane, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Sing Up");
        stage.show();

        Body(Bt_Register, lb1, lb2, Account_TextField, Passwd_TextField, gridpane);

        borderpane.setCenter(gridpane);
        borderpane.setBottom(Bt_Return);

        Bt_Return.setOnAction(e -> new Login().start(stage));
        Bt_Register.setOnAction(e -> Register_Method() );
    }

    static void Body(Button register, Label lb1, Label lb2, TextField txfd1, TextField txfd2, GridPane gridpane) {
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

        try {

            while (rs1.next()){

                if (rs1.getString(1).matches(Account_TextField.getText())) {
                    System.out.println("用户存在");
                    prompt();
                }else{
                    System.out.println("用户不存在");
                    Register_User();
                }
            }

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
    void prompt(){
        Pane pane=new Pane();
        Text text=new Text("用户存在");
        text.setFont(Font.font("华文正楷",12));
        text.setX(100);
        text.setY(100);
        pane.getChildren().add(text);
        Stage stage=new Stage();
        stage.setScene(new Scene(pane,200,200));
        stage.show();
    }

    void Register_User() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root",
                    "xsl203457");
            PreparedStatement ps = con
                    .prepareStatement("insert into xsl.passwd_date (account,passwd) values (?,?)");
            ps.setString(1, Account_TextField.getText());
            ps.setString(2, Passwd_TextField.getText());
            ps.executeUpdate();

            Account_TextField.clear();
            Passwd_TextField.clear();
            System.out.println("注册成功");


        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

}
