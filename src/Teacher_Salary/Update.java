package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class Update extends Choice {

    BorderPane borderPane=new BorderPane();
    HBox box1=new HBox();
    HBox box2=new HBox(200);
    final Button Bt_Update = new Button("确认");
    final Button Bt_Return = new Button("返回");
    final Button Bt_Reset = new Button("重置");
    final Button Bt_Inquire=new Button("查询");

    final TextField Id_Txfd = new TextField();
    final TextField Name_Txfd = new TextField();
    final TextField Position_Txfd = new TextField();
    final TextField Salary_Txfd = new TextField();

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        //Text_Field_Attribute();
        Id_Txfd.setPromptText("ID必须正确");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");
        Input.Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Salary_Txfd, Bt_Update);

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset,Bt_Inquire);

        box1.setPadding(new Insets(20,0,0,20));
        box2.setPadding(new Insets(0,0,20,0));
        box2.setAlignment(Pos.CENTER);

        Bt_Reset.setOnAction(e -> new Update().start(stage));
        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Update.setOnAction(e->Inquire_User());
        Bt_Inquire.setOnAction(e->new Inquire().start(stage));

        borderPane.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\d.jpg"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        borderPane.setTop(box1);
        borderPane.setCenter(gridpane);
        borderPane.setBottom(box2);

        Scene scene = new Scene(borderPane, 400, 400);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.setTitle("修改");
        stage.show();
    }

    private void Inquire_User(){
        Connection con;
        Statement  stmt;
        PreparedStatement ps;
        ResultSet resultSet;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            stmt=con.createStatement();
            resultSet=stmt.executeQuery("select id from xsl.teacher_salary");
            System.out.println("查询用户");
            while(resultSet.next()){
                if (Id_Txfd.getText().matches(String.valueOf(resultSet.getInt(1)))){
                    Judgement_Update();
                    break;
                }else
                    System.out.println("用户不存在");
            }
        }catch(Exception ex){
            ex.getStackTrace();
        }

    }

    private void Judgement_Update(){
        if (Id_Txfd.getLength()==0||Name_Txfd.getLength()==0||Position_Txfd.getLength()==0||Salary_Txfd.getLength()==0){
            System.out.println("不能为空");
        }else if(Id_Txfd.getLength()>10||Salary_Txfd.getLength()>10){
            System.out.println("ID||Salary 长度大于10");
        }else
            Bt_Update_Method();
    }

    private void Bt_Update_Method() {
            Connection con;
            PreparedStatement ps;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

                ps = con.prepareStatement("update xsl.teacher_salary set name=?,position=?,salary=? where id=?");
                ps.setDouble(4, Double.parseDouble(Id_Txfd.getText()));
                ps.setString(1, Name_Txfd.getText());
                ps.setString(2, Position_Txfd.getText());
                ps.setInt(3, Integer.parseInt(Salary_Txfd.getText()));

                Clear_TextField();
                ps.executeUpdate();
                Update_Successful();
                System.out.println("修改成功");

            } catch (Exception ex) {
                ex.getStackTrace();
            }
    }

    private void Text_Field_Attribute(){
        Id_Txfd.setPromptText("ID必须正确");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");
    }

    private void Update_Successful(){
        Stage window=new Stage();
        Pane pane=new Pane();
        Text text=new Text("修改成功");
        text.setStyle("-fx-font-family: '华文行楷';-fx-font-size: 20");
        text.setX(120);
        text.setY(100);
        pane.getChildren().add(text);
        window.setScene(new Scene(pane,300,200));
        window.setX(550);
        window.setY(300);
        window.showAndWait();
    }
    public void Clear_TextField(){
        Id_Txfd.clear();
        Name_Txfd.clear();
        Position_Txfd.clear();
        Salary_Txfd.clear();
    }

}