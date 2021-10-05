package Teacher_Salary;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Update extends Choice {

    final Button Bt_Update = new Button("Update");
    final Button Bt_Return = new Button("Return");
    final Button Bt_Reset = new Button("Reset");

    final TextField Id_Txfd = new TextField();
    final TextField Name_Txfd = new TextField();
    final TextField Position_Txfd = new TextField();
    final TextField Salary_Txfd = new TextField();

    Stage window;

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        window = stage;
        Text_Field_Attribute();

        Input.Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Salary_Txfd, Bt_Return, Bt_Update, Bt_Reset);

        Bt_Update_Method();
        Bt_Reset.setOnAction(e -> new Update().start(window));
        Bt_Return.setOnAction(e -> new Choice().start(window));

        Scene scene = new Scene(gridpane, 400, 400);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.setTitle("Update");
        stage.show();
    }

    private void Bt_Update_Method() {
        Bt_Update.setOnAction(e -> {
            Connection con;
            PreparedStatement ps;
            //PreparedStatement ps1 = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

                ps = con.prepareStatement("update xsl.teacher_salary set name=?,position=?,salary=? where id=?");
                ps.setDouble(4, Double.parseDouble(Id_Txfd.getText()));
                ps.setString(1, Name_Txfd.getText());
                ps.setString(2, Position_Txfd.getText());
                ps.setInt(3, Integer.parseInt(Salary_Txfd.getText()));

                new Input().Clear_TextField();
                ps.executeUpdate();
                System.out.println("修改成功");

            } catch (Exception ex) {
                ex.getStackTrace();
            }
        });
    }

    private void Text_Field_Attribute(){
        Id_Txfd.setPromptText("ID必须正确");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");
    }


}