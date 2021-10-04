package Teacher_Salary;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class Register extends Application {
    Stage window;
    Successful_SingUp Successful = new Successful_SingUp();
    private final GridPane gridpane = new GridPane();
    private final BorderPane borderpane = new BorderPane();
    private final Button register = new Button("Register");
    private final Button btreturn = new Button("Return");
    private final Label lb1 = new Label("AccountName:");
    private final Label lb2 = new Label("Passwd:");
    private final TextField txfd1 = new TextField();
    private final TextField txfd2 = new TextField();

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        window=stage;
        Scene scene = new Scene(borderpane, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Sing Up");
        stage.show();

        Body(register, lb1, lb2, txfd1, txfd2, gridpane);

        borderpane.setCenter(gridpane);
        borderpane.setBottom(btreturn);

        btreturn.setOnAction(e -> new Login().start(stage));
        register.setOnAction(e -> Register_Method(window));
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

    private void Register_Method(Stage stage){
        Statement stmt1 ;
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
            while (rs1.next()) {

                if (rs1.getString(1).matches(txfd1.getText())) {
                    System.out.println("用户名已存在");

                } else {

                    Register_User(stage);

                }

            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    void Register_User(Stage stage){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root",
                    "xsl203457");
            PreparedStatement ps = con
                    .prepareStatement("insert into passwd_date (account,passwd) values (?,?)");
            ps.setString(1, txfd1.getText());
            ps.setString(2, txfd2.getText());
            ps.executeUpdate();
            Successful.start(stage);

            //register_user_mysql();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private void close(Stage stage) {

        stage.close();
    }
    void register_user_mysql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
        Statement stmt = con.createStatement();

        stmt.executeQuery("create table user (account varchar(20),passwd varchar(25)");
    }
}
