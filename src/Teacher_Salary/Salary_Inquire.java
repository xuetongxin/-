package Teacher_Salary;

import javafx.application.Application;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Salary_Inquire extends Application {
    TextField ID_TextField = new TextField();
    TextField Name_TextField = new TextField();
    TextField Allowance_TextField = new TextField();
    TextField BaseSalary_TextField = new TextField();
    TextField Tootle_TextField = new TextField();
    TextField AverageSalary_TextField = new TextField();


    @Override
    public void start(Stage stage) throws Exception {
        Image image=new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg");

        ImageView imageView=new ImageView(image);
        imageView.setFitHeight(810);
        imageView.setFitWidth(1580);
        Label ID_Label = new Label("工号");
        Label Name_Label = new Label("名字");
        Label Allowance_Label = new Label("津贴");
        Label Base_Label = new Label("基本工资");
        Label Tootle_Label = new Label("总工资");
        Label AverageSalary_Label = new Label("平均工资");

        ID_Label.setStyle("-fx-font-family: '华文行楷';-fx-text-fill: 'white';-fx-font-size: 20");
        Name_Label.setStyle("-fx-font-family: '华文行楷';-fx-text-fill: 'white';-fx-font-size: 20");
        Allowance_Label.setStyle("-fx-font-family: '华文行楷';-fx-text-fill: 'white';-fx-font-size: 20");
        Base_Label.setStyle("-fx-font-family: '华文行楷';-fx-text-fill: 'white';-fx-font-size: 20");
        Tootle_Label.setStyle("-fx-font-family: '华文行楷';-fx-text-fill: 'white';-fx-font-size: 20");
        AverageSalary_Label.setStyle("-fx-font-family: '华文行楷';-fx-text-fill: 'white';-fx-font-size: 20");

        Button Bt_Inquire = new Button("查询");
        Button Bt_Return = new Button("返回");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(ID_Label, 0, 0);
        gridPane.add(ID_TextField, 1, 0);
        gridPane.add(Name_Label, 0, 1);
        gridPane.add(Name_TextField, 1, 1);
        gridPane.add(Allowance_Label, 0, 2);
        gridPane.add(Allowance_TextField, 1, 2);
        gridPane.add(Base_Label, 0, 3);
        gridPane.add(BaseSalary_TextField, 1, 3);

        gridPane.add(Tootle_Label, 0, 10);
        gridPane.add(Tootle_TextField, 1, 10);
        gridPane.add(AverageSalary_Label, 0, 11);
        gridPane.add(AverageSalary_TextField, 1, 11);
        gridPane.add(Bt_Inquire, 0, 12);
        gridPane.add(Bt_Return, 2, 12);


        Bt_Inquire.setOnAction(e -> Inquire_Result());
        Bt_Return.setOnAction(e -> {
            try {
                new Choice().Choice_Inquire_Method(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);

        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(imageView,borderPane);

        stage.setScene(new Scene(stackPane,500,500));
        stage.setTitle("教师工资查询");
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.show();

    }

    private void Inquire_Result() {
        if(ID_TextField.getLength()>0&&Name_TextField.getLength()>0&&Allowance_TextField.getLength()>0&&BaseSalary_TextField.getLength()>0){
            double tootle = Double.parseDouble(Allowance_TextField.getText()) + Double.parseDouble(BaseSalary_TextField.getText()) * 12;
            double average = tootle / 12;
            Tootle_TextField.setText(String.valueOf(tootle));
            AverageSalary_TextField.setText(String.valueOf(average));
            Add_Salary_To_Table(tootle, average);
        }else{
            Alert alert=new Alert(Alert.AlertType.WARNING,"信息不能为空");
            alert.showAndWait();
        }


    }

    private void Add_Salary_To_Table(Double tootle, double average) {
        boolean user_exit=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl?", "root", "xsl203457");
            PreparedStatement preparedStatement = connection.prepareStatement("update xsl.teacher_salary set tootle_salary=? ,average_salary=? where id=?");
            PreparedStatement preparedStatement1= connection.prepareStatement("select id, name  from xsl.teacher_salary ");
            ResultSet resultSet=preparedStatement1.executeQuery();
            while(resultSet.next()){
                if (resultSet.getInt(1)==Integer.parseInt(ID_TextField.getText())&&resultSet.getString(2).matches(Name_TextField.getText())) {
                    user_exit = true;
                }
            }
            if (user_exit){
                preparedStatement.setDouble(1, tootle);
                preparedStatement.setDouble(2, average);
                preparedStatement.setString(3, ID_TextField.getText());
                preparedStatement.executeUpdate();
                System.out.println("插入成功");
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR,"用户不存在");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
