package Teacher_Salary;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Delete extends Choice {

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        BorderPane borderPane = new BorderPane();

        TextField Text_Field=new TextField();
        Text_Field.setPromptText("请输入你要删除的用户ID");
        Text_Field.setPrefColumnCount(10);
        Text_Field.setPrefWidth(10);

        Button Bt_Return = new Button("Return");
        Button Bt_Ok=new Button("OK");
        HBox box = new HBox(50);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(Bt_Return,Bt_Ok);

        borderPane.setCenter(Text_Field);
        borderPane.setBottom(box);

        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Ok.setOnAction(e-> Delete_User(Text_Field));
        Scene scene = new Scene(borderPane, 400, 400);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.setTitle("Delete");
        stage.show();
    }

    private void Delete_User(TextField Text_Field){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from xsl.teacher_salary where id=?");
            preparedStatement.setInt(1, Integer.parseInt(Text_Field.getText()));
            preparedStatement.execute();

            System.out.println("删除成功");
            Text_Field.clear();
        }catch  (Exception e) {
            e.printStackTrace();
        }


    }


}
