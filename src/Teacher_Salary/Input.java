package Teacher_Salary;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Input extends Choice {

    Input() {
    }

    private final GridPane gridpane = new GridPane();
    private final Button Bt_Ok = new Button("OK");
    private final Button Bt_Return = new Button("Return");
    private final Button Bt_Reset = new Button("Reset");

    private final TextField Id_Txfd = new TextField();
    private final TextField Name_Txfd = new TextField();
    private final TextField Position_Txfd = new TextField();
    private final TextField Salary_Txfd = new TextField();
    private Stage window;

    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        window = stage;
        Text_Field_Attribute();
        Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Salary_Txfd, Bt_Return, Bt_Ok, Bt_Reset);
        Bt_Return.setOnAction(e -> new Choice().start(window));
        Bt_Reset.setOnAction(e -> new Input().start(window));
        Bt_Ok_Method();

        Scene scene = new Scene(gridpane, 400, 400);
        window.setX(500);
        window.setY(200);
        window.setScene(scene);
        window.setTitle("input");
        window.show();

    }

    static void Panel_Layout(GridPane gridpane, TextField id_txfd, TextField name_txfd, TextField position_txfd, TextField salary_txfd, Button bt_return, Button bt_ok, Button bt_reset) {
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(new Label("id:"), 0, 0);
        gridpane.add(id_txfd, 1, 0);
        gridpane.add(new Label("name:"), 0, 1);
        gridpane.add(name_txfd, 1, 1);
        gridpane.add(new Label("position:"), 0, 2);
        gridpane.add(position_txfd, 1, 2);
        gridpane.add(new Label("salary:"), 0, 3);
        gridpane.add(salary_txfd, 1, 3);
        gridpane.add(bt_return, 0, 5);
        gridpane.add(bt_ok, 1, 5);
        gridpane.add(bt_reset, 3, 5);
    }

    private void Bt_Ok_Method() {
        Bt_Ok.setOnAction(e -> Mysql_Input());
    }

    private void Mysql_Input() {
        Connection con;
        PreparedStatement ps;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            ps = con.prepareStatement("insert into xsl.teacher_salary (id,name,position,salary) values (?,?,?,?) ;");
            ps.setDouble(1, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(2, Name_Txfd.getText());
            ps.setString(3, Position_Txfd.getText());
            ps.setInt(4, Integer.parseInt(Salary_Txfd.getText()));
            ps.executeUpdate();

            Clear_TextField();
            System.out.println("录入成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public void Clear_TextField(){
        Id_Txfd.clear();
        Name_Txfd.clear();
        Position_Txfd.clear();
        Salary_Txfd.clear();
    }
    private void Text_Field_Attribute(){
        Id_Txfd.setPromptText("！必须为数字");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");

        Id_Txfd.setPrefColumnCount(150);
        //Name_Txfd.setPrefColumnCount(100);
        //Position_Txfd.setPrefColumnCount(100);
        //Salary_Txfd.setPrefColumnCount(100);
        Id_Txfd.setPrefWidth(150);
        //Name_Txfd.setPrefWidth(100);
        //Position_Txfd.setPrefWidth(100);
        //Salary_Txfd.setPrefWidth(100);


    }
}

