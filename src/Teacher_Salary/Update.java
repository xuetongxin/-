package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

    CheckBox ID_Box=new CheckBox();
    CheckBox Name_Box=new CheckBox();
    CheckBox Position_Box=new CheckBox();
    CheckBox Salary_Box=new CheckBox();

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根

        Id_Txfd.setPromptText("ID必须正确");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");

        Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Salary_Txfd, Bt_Update,Name_Box,Position_Box,Salary_Box);

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset,Bt_Inquire);

        box1.setPadding(new Insets(20,0,0,20));
        box2.setPadding(new Insets(0,0,20,0));
        box2.setAlignment(Pos.CENTER);

        Bt_Reset.setOnAction(e -> new Update().start(stage));
        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Update.setOnAction(e->Choice_Update());
        Bt_Inquire.setOnAction(e->new Inquire().start(stage));

        borderPane.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\b.jpg"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
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

    private static void Panel_Layout(GridPane gridpane, TextField id_txfd, TextField name_txfd, TextField position_txfd, TextField salary_txfd, Button bt_ok,CheckBox name_box, CheckBox position_box,CheckBox salary_box) {
        Label Salary_Label=new Label("薪水");
        Salary_Label.setStyle("-fx-text-fill:'white'");
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(new Label("序号:"), 0, 0);
        gridpane.add(id_txfd, 1, 0);

        gridpane.add(new Label("名字:"), 0, 1);
        gridpane.add(name_txfd, 1, 1);
        gridpane.add(name_box,2,1);
        gridpane.add(new Label("职位:"), 0, 2);
        gridpane.add(position_txfd, 1, 2);
        gridpane.add(position_box,2,2);
        gridpane.add(Salary_Label, 0, 3);
        gridpane.add(salary_txfd, 1, 3);
        gridpane.add(salary_box,2,3);
        gridpane.add(bt_ok, 0, 5);

    }

    private void Choice_Update() {
        Connection con;
        PreparedStatement ps;

        if(Name_Box.isSelected()){
            Name_Update();
        }else if(Position_Box.isSelected()){
            Position_Update();
        }else if(Salary_Box.isSelected()){
            Salary_update();
        }else if(Name_Box.isSelected()&&Position_Box.isSelected()){
            Name_Position_Update();
        }else if(Position_Box.isSelected()&&Salary_Box.isSelected()){
            Position_Salary_Update();
        }else if (Name_Box.isSelected()&&Salary_Box.isSelected()){
            Name_Salary_Update();
        }else {
            System.out.println("Box没有被选择");
            //Inquire_User();
            Judgement_Update();
        }
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
                   System.out.println("用户存在");
                    //Judgement_Update();
                    Bt_Update_Method();
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
            Inquire_User();
            //Bt_Update_Method();
    }

    private void Bt_Update_Method() {
            Connection con;
            PreparedStatement ps;
            try {
                System.out.println("连接数据库");
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

                ps = con.prepareStatement("update xsl.teacher_salary set name=?,position=?,salary=? where id=?");
                ps.setDouble(4, Double.parseDouble(Id_Txfd.getText()));
                ps.setString(1, Name_Txfd.getText());
                ps.setString(2, Position_Txfd.getText());
                ps.setInt(3, Integer.parseInt(Salary_Txfd.getText()));
                ps.executeUpdate();
                System.out.println("连接成功");

                //格式化文本域
                Id_Txfd.clear();
                Name_Txfd.clear();
                Position_Txfd.clear();
                Salary_Txfd.clear();

                Update_Successful();
                System.out.println("修改成功");

            } catch (Exception ex) {
                ex.getStackTrace();
            }
    }




    private void Name_Update(){
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set name=? where id=?");
            ps.setDouble(2, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Name_Txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Name_Txfd.clear();
        Update_Successful();
        System.out.println("名字修改成功");
    }
    private void Position_Update(){
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set position=? where id=?");
            ps.setDouble(2, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Position_Txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Position_Txfd.clear();
        Update_Successful();
        System.out.println("职位修改成功");
    }

    private void Salary_update(){
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set salary=? where id=?");
            ps.setDouble(2, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Salary_Txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Salary_Txfd.clear();
        Update_Successful();
        System.out.println("薪水修改成功");
    }
    private void Name_Salary_Update(){
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set name=? ,salary=?  where id=?");
            ps.setDouble(3, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Name_Txfd.getText());
            ps.setDouble(2, Double.parseDouble(Salary_Txfd.getText()));
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Name_Txfd.clear();
        Salary_Txfd.clear();
        Update_Successful();
        System.out.println("名字和薪水修改成功");
    }
    private void Name_Position_Update(){
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set name=?,position=? where id=?");
            ps.setDouble(3, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Name_Txfd.getText());
            ps.setString(2,Position_Txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Name_Txfd.clear();
        Position_Txfd.clear();
        Update_Successful();
        System.out.println("名字和职位修改成功");
    }
    private void Position_Salary_Update(){
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set position=?,salary=? where id=?");
            ps.setDouble(3, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Position_Txfd.getText());
            ps.setDouble(2, Double.parseDouble(Salary_Txfd.getText()));
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Position_Txfd.clear();
        Salary_Txfd.clear();
        Update_Successful();
        System.out.println("职位和薪水修改成功");
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

}