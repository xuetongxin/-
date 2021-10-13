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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Salary_Inquire extends Application {
    TextField ID_TextField=new TextField();
    TextField Name_TextField=new TextField();
    TextField Allowance_TextField=new TextField();
    TextField BaseSalary_TextField=new TextField();
    TextField Tootle_TextField =new TextField();
    TextField AverageSalary_TextField=new TextField();


    @Override
    public void start(Stage stage) throws Exception {

        Label ID_Label=new Label("工号");
        Label Name_Label=new Label("名字");
        Label Allowance_Label=new Label("津贴");
        Label Base_Label=new Label("基本工资");
        Label Tootle_Label=new Label("总工资");
        Label AverageSalary_Label=new Label("平均工资");

        ID_Label.setStyle("-fx-font-family: '华文行楷';-fx-text-fill: 'white';-fx-font-size: 20");

        Button Bt_Inquire=new Button("查询");
        Button Bt_Return=new Button("返回");
        GridPane gridPane=new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(ID_Label,0,0);
        gridPane.add(ID_TextField,1,0);
        gridPane.add(Name_Label,0,1);
        gridPane.add(Name_TextField,1,1);
        gridPane.add(Allowance_Label,0,2);
        gridPane.add(Allowance_TextField,1,2);
        gridPane.add(Base_Label,0,3);
        gridPane.add(BaseSalary_TextField,1,3);
        gridPane.add(Tootle_Label,0,4);
        gridPane.add(Tootle_TextField,1,4);
        gridPane.add(AverageSalary_Label,0,5);
        gridPane.add(AverageSalary_TextField,1,5);
        gridPane.add(Bt_Inquire,0,8);
        gridPane.add(Bt_Return,2,8);


        Bt_Inquire.setOnAction(e->Inquire_Result());
        Bt_Return.setOnAction(e-> {
            try {
                new Choice().Choice_Inquire_Method(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BorderPane borderPane=new BorderPane();
        borderPane.setCenter(gridPane);

        stage.setScene(new Scene(borderPane));
        stage.setTitle("教师工资查询");
        stage.show();

    }

    private void Inquire_Result() {
        double tootle=Double.parseDouble(Allowance_TextField.getText())+Double.parseDouble(BaseSalary_TextField.getText())*12;
        double average=tootle/12;
        Tootle_TextField.setText(String.valueOf(tootle));
        AverageSalary_TextField.setText(String.valueOf(average));
        Add_Salary_To_Table(tootle,average);

    }

    private void Add_Salary_To_Table(Double tootle,double average){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl?","root","xsl203457");
            PreparedStatement preparedStatement= connection.prepareStatement("update xsl.teacher_salary set tootle_salary=? ,average_salary=? where id=?");
            preparedStatement.setDouble(1,tootle);
            preparedStatement.setDouble(2,average);
            preparedStatement.setString(3,ID_TextField.getText());
            preparedStatement.executeUpdate();
            System.out.println("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
