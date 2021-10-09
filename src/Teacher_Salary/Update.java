package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class Update extends Choice {

    final BorderPane borderPane=new BorderPane();
    final StackPane stackPane=new StackPane();
    final HBox box1=new HBox();
    final HBox box2=new HBox(200);

    final Button Bt_Update = new Button("确认");
    final Button Bt_Return = new Button("返回");
    final Button Bt_Reset = new Button("重置");
    final Button Bt_Inquire=new Button("查询");

    final TextField Id_Txfd = new TextField();
    final TextField Name_Txfd = new TextField();
    final TextField Position_Txfd = new TextField();
    final TextField Salary_Txfd = new TextField();

    final CheckBox Name_Box=new CheckBox();
    final CheckBox Position_Box=new CheckBox();
    final CheckBox Salary_Box=new CheckBox();

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        imageview.setFitHeight(810);
        imageview.setFitWidth(1535); // 背景图片属性
        imageview.setImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"));

        Id_Txfd.setPromptText("ID必须正确");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");

        Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Salary_Txfd, Bt_Update,Name_Box,Position_Box,Salary_Box);

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset,Bt_Inquire);
        box1.setPadding(new Insets(20,0,0,20));
        box2.setPadding(new Insets(0,0,200,0));
        box2.setAlignment(Pos.CENTER);

        Bt_Reset.setOnAction(e -> new Update().start(stage));
        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Inquire.setOnAction(e->new Inquire().start(stage));
        Bt_Update.setOnAction(e->Judgement_Update());

        Id_Txfd.setOnAction(e->Judgement_Update());
        Name_Txfd.setOnAction(e->Judgement_Update());
        Position_Txfd.setOnAction(e->Judgement_Update());
        Salary_Txfd.setOnAction(e->Judgement_Update());

        //borderPane.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\b.jpg"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        borderPane.setTop(box1);
        borderPane.setCenter(gridpane);
        borderPane.setBottom(box2);

        stackPane.getChildren().addAll(imageview,borderPane);

        Scene scene = new Scene(stackPane);
        //stage.setX(500);
        //stage.setY(200);
        stage.setScene(scene);
        stage.setTitle("修改");
        stage.show();
    }

    private static void Panel_Layout(GridPane gridpane, TextField id_txfd, TextField name_txfd, TextField position_txfd, TextField salary_txfd, Button bt_ok,CheckBox name_box, CheckBox position_box,CheckBox salary_box) {
        Label Id_Label=new Label("序号");
        Label Name_label=new Label("名字");
        Label Position_Label=new Label("职位");
        Label Salary_Label=new Label("薪水");
        Id_Label.setStyle("-fx-text-fill:'white'");
        Name_label.setStyle("-fx-text-fill:'white'");
        Position_Label.setStyle("-fx-text-fill:'white'");
        Salary_Label.setStyle("-fx-text-fill:'white'");
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(Id_Label, 0, 0);
        gridpane.add(id_txfd, 1, 0);
        gridpane.add(Name_label, 0, 1);
        gridpane.add(name_txfd, 1, 1);
        gridpane.add(name_box,2,1);
        gridpane.add(Position_Label, 0, 2);
        gridpane.add(position_txfd, 1, 2);
        gridpane.add(position_box,2,2);
        gridpane.add(Salary_Label, 0, 3);
        gridpane.add(salary_txfd, 1, 3);
        gridpane.add(salary_box,2,3);
        gridpane.add(bt_ok, 0, 5);

    }

    private void Judgement_Update(){
        if (Id_Txfd.getLength()==0||Name_Txfd.getLength()==0||Position_Txfd.getLength()==0||Salary_Txfd.getLength()==0){
            Alert alert=new Alert(Alert.AlertType.WARNING,"更改信息不能为空");
            alert.showAndWait();
        }else if(Id_Txfd.getLength()>10||Salary_Txfd.getLength()>10){
            Alert alert=new Alert(Alert.AlertType.ERROR,"ID长度小于8或者大于15");
            alert.showAndWait();
        }else
            Inquire_User();
    }





    private void Inquire_User(){
        Connection con;
        Statement  stmt;
        ResultSet resultSet;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            stmt=con.createStatement();
            resultSet=stmt.executeQuery("select id from xsl.teacher_salary");
            System.out.println("查询用户");
            boolean User_Exit=false;
            while(resultSet.next()){
                if (Id_Txfd.getText().matches(String.valueOf(resultSet.getInt(1)))){
                    User_Exit=true;
                  // System.out.println("用户存在");
                   // Bt_Update_Method();
                    break;
                }else{
                    System.out.println("用户不存在");
                }

            }

            if(User_Exit){
                System.out.println("用户存在");
                Choice_Update();
                //Bt_Update_Method();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR,"用户不存在");
                alert.showAndWait();
            }

        }catch(Exception ex){
            ex.getStackTrace();
        }

    }

    private void Choice_Update() {

        if(Name_Box.isSelected()&&Position_Box.isSelected()&& Salary_Box.isSelected()){
            Bt_Update_Method();
        } else if(Name_Box.isSelected()&&Position_Box.isSelected()){
            Name_Position_Update();

        }else if(Position_Box.isSelected()&&Salary_Box.isSelected()){
            Position_Salary_Update();

        }else if(Salary_Box.isSelected()&&Name_Box.isSelected()){
            Name_Salary_Update();

        }else if(Name_Box.isSelected()){
            Name_Update();
        }else if(Position_Box.isSelected()){
            Position_Update();
        }else if (Salary_Box.isSelected()){
            Salary_update();
        }else{
            Alert alert =new Alert(Alert.AlertType.WARNING,"请选择你要修改的信息");
            alert.showAndWait();
            System.out.println("请选择你要修改的信息");
        }

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
                Name_Box.setSelected(false);
                Position_Box.setSelected(false);
                Salary_Box.setSelected(false);
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"修改成功");
                alert.showAndWait();
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
        Name_Box.setSelected(false);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"名字修改成功");
        alert.showAndWait();
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
        Position_Box.setSelected(false);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"职位修改成功");
        alert.showAndWait();
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
        Salary_Box.setSelected(false);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"薪水修改成功");
        alert.showAndWait();
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

        Name_Box.setSelected(false);
        Salary_Box.setSelected(false);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"名字和薪水修改成功");
        alert.showAndWait();
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
        Name_Box.setSelected(false);
        Position_Box.setSelected(false);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"名字和职位修改成功");
        alert.showAndWait();
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
        Position_Box.setSelected(false);
        Salary_Box.setSelected(false);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"薪水和职位修改成功");
        alert.showAndWait();
        System.out.println("职位和薪水修改成功");
    }

}