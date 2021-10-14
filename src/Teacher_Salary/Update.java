package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class Update extends Choice {

    final BorderPane borderPane = new BorderPane();
    final StackPane stackPane = new StackPane();
    final HBox box1 = new HBox();
    final HBox box2 = new HBox(200);

    final Button Bt_Update = new Button("确认");
    final Button Bt_Return = new Button("返回");
    final Button Bt_Reset = new Button("重置");
    final Button Bt_Inquire = new Button("查询");

    final TextField Id_Txfd = new TextField();
    final TextField Name_Txfd = new TextField();
    final TextField Position_Txfd = new TextField();
    final CheckBox Name_Box = new CheckBox();
    final CheckBox Position_Box = new CheckBox();
    //final CheckBox Salary_Box=new CheckBox();
    final CheckBox Age_Box = new CheckBox();
    final CheckBox Marriage_Box = new CheckBox();
    final CheckBox Birth_Box = new CheckBox();
    //final TextField Salary_Txfd = new TextField();
    final CheckBox Sex_Box = new CheckBox();
    final CheckBox Address_Box = new CheckBox();
    private final TextField Sex_Txfd = new TextField();
    private final TextField Birth_Txfd = new TextField();
    private final TextField Age_Txfd = new TextField();
    private final TextField Marriage_Status_txfd = new TextField();
    private final TextField Address_Txfd = new TextField();

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        imageview.setFitHeight(1080);
        imageview.setFitWidth(1980); // 背景图片属性
        imageview.setImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"));

        Id_Txfd.setPromptText("ID必须正确");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");

        Label Id_Label = new Label("序号");
        Label Name_label = new Label("名字");
        Label Sex_Label = new Label("性别");
        Label Birth_Label = new Label("出生年月");
        Label Age_Label = new Label("年龄");
        Label Marriage_status_label = new Label("婚姻状态");
        Label Address_Label = new Label("家庭地址");
        Label Position_Label = new Label("职位");
        //Label Salary_Label=new Label("薪水");
        //Salary_Label.setStyle("-fx-text-fill:'white'");
        Id_Label.setStyle("-fx-text-fill:'white'");
        Name_label.setStyle("-fx-text-fill:'white'");
        Position_Label.setStyle("-fx-text-fill:'white'");
        Sex_Label.setStyle("-fx-text-fill: 'white'");
        Age_Label.setStyle("-fx-text-fill: 'white'");
        Marriage_status_label.setStyle("-fx-text-fill: 'white'");
        Address_Label.setStyle("-fx-text-fill: 'white'");
        Birth_Label.setStyle("-fx-text-fill:'white'");
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(Id_Label, 0, 0);
        gridpane.add(Id_Txfd, 1, 0);

        gridpane.add(Name_label, 0, 1);
        gridpane.add(Name_Txfd, 1, 1);
        gridpane.add(Name_Box, 2, 1);

        gridpane.add(Position_Label, 0, 2);
        gridpane.add(Position_Txfd, 1, 2);
        gridpane.add(Position_Box, 2, 2);

        gridpane.add(Sex_Label, 0, 3);
        gridpane.add(Sex_Txfd, 1, 3);
        gridpane.add(Sex_Box, 2, 3);

        gridpane.add(Birth_Label, 0, 4);
        gridpane.add(Birth_Txfd, 1, 4);
        gridpane.add(Birth_Box, 2, 4);

        gridpane.add(Age_Label, 0, 5);
        gridpane.add(Age_Txfd, 1, 5);
        gridpane.add(Age_Box, 2, 5);

        gridpane.add(Marriage_status_label, 0, 6);
        gridpane.add(Marriage_Status_txfd, 1, 6);
        gridpane.add(Marriage_Box, 2, 6);

        gridpane.add(Address_Label, 0, 7);
        gridpane.add(Address_Txfd, 1, 7);
        gridpane.add(Address_Box, 2, 7);

        //gridpane.add(Salary_Label, 0, 8);
        //gridpane.add(Salary_Txfd, 1, 8);
        //gridpane.add(Salary_Box,2,8);

        gridpane.add(Bt_Update, 0, 9);
        //Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Salary_Txfd, Bt_Update,Name_Box,Position_Box,Salary_Box);

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset, Bt_Inquire);
        box1.setPadding(new Insets(20, 0, 0, 20));
        box2.setPadding(new Insets(0, 0, 100, 0));
        box2.setAlignment(Pos.CENTER);

        Bt_Reset.setOnAction(e -> new Update().start(stage));
        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Inquire.setOnAction(e -> new Inquire().start(stage));
        Bt_Update.setOnAction(e -> Inquire_User());

        borderPane.setTop(box1);
        borderPane.setCenter(gridpane);
        borderPane.setBottom(box2);

        stackPane.getChildren().addAll(imageview, borderPane);

        stage.setScene(new Scene(stackPane,500,500));
        stage.setTitle("修改");
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.show();
    }


    private void Inquire_User() {
        Connection con;
        Statement stmt;
        ResultSet resultSet;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("select id from xsl.teacher_salary");
            System.out.println("查询用户");
            boolean User_Exit = false;
            while (resultSet.next()) {
                if (Id_Txfd.getText().matches(String.valueOf(resultSet.getInt(1)))) {
                    User_Exit = true;
                    // System.out.println("用户存在");
                    // Bt_Update_Method();
                    break;
                } else {
                    System.out.println("用户不存在");
                }
            }
            if (User_Exit) {
                System.out.println("用户存在");
                Choice_Update();
                //Bt_Update_Method();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "用户不存在");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

    private void Choice_Update() {
        int choice;
        if (Name_Box.isSelected() && Position_Box.isSelected() && Sex_Box.isSelected() && Age_Box.isSelected() && Birth_Box.isSelected() && Marriage_Box.isSelected() && Address_Box.isSelected())
            choice = 0;
        else if (Name_Box.isSelected())
            choice = 1;
        else if (Position_Box.isSelected())
            choice = 2;
        else if (Sex_Box.isSelected())
            choice = 3;
        else if (Birth_Box.isSelected())
            choice = 4;
        else if (Age_Box.isSelected())
            choice = 5;
        else if (Marriage_Box.isSelected())
            choice = 6;
        else if (Address_Box.isSelected())
            choice = 7;
        else
            choice = 8;
        switch (choice) {
            case 0: {
                if (Name_Txfd.getLength() > 0 && Position_Txfd.getLength() > 0 && Sex_Txfd.getLength() > 0 && Age_Txfd.getLength() > 0 && Birth_Txfd.getLength() > 0 && Marriage_Status_txfd.getLength() > 0 && Address_Txfd.getLength() > 0)
                    All_Update();
                else
                    Null_Tips();
            }
            case 1: {
                if (Name_Txfd.getLength() > 0)
                    Name_Update();
                else
                    Null_Tips();
                break;
            }

            case 2: {
                if (Position_Txfd.getLength() > 0)
                    Position_Update();
                else
                    Null_Tips();
                break;
            }

            case 3: {
                if (Sex_Txfd.getLength() > 0)
                    Sex_Update();
                else
                    Null_Tips();
                break;
            }
            case 4: {
                if (Birth_Txfd.getLength() > 0)
                    Birth_Update();
                else
                    Null_Tips();
                break;
            }
            case 5: {
                if (Age_Txfd.getLength() > 0)
                    Age_Update();
                else
                    Null_Tips();
            }
            case 6: {
                if (Marriage_Status_txfd.getLength() > 0)
                    Marriage_Update();
                else
                    Null_Tips();
                break;
            }
            case 7: {
                if (Address_Txfd.getLength() > 0)
                    Address_Update();
                else
                    Null_Tips();
                break;
            }
            case 8: {
                Alert alert = new Alert(Alert.AlertType.WARNING, "请选择你要修改的信息，一次只能修改一个或者全部修改");
                alert.showAndWait();
            }
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }


    }

    private void Null_Tips() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "不能为空");
        alert.showAndWait();
    }

    private void All_Update() {
        Connection con;
        PreparedStatement ps;
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            ps = con.prepareStatement("update xsl.teacher_salary set name=?,position=? where id=?");
            ps.setDouble(3, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Name_Txfd.getText());
            ps.setString(2, Position_Txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

            //格式化文本域
            Id_Txfd.clear();
            Name_Txfd.clear();
            Position_Txfd.clear();

            Name_Box.setSelected(false);
            Position_Box.setSelected(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "修改成功");
            alert.showAndWait();
            System.out.println("修改成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }


    private void Name_Update() {
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "名字修改成功");
        alert.showAndWait();
        System.out.println("名字修改成功");
    }

    private void Position_Update() {
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "职位修改成功");
        alert.showAndWait();
        System.out.println("职位修改成功");
    }


    private void Sex_Update() {
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set sex=? where id=?");
            ps.setDouble(2, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Sex_Txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        Id_Txfd.clear();
        Sex_Txfd.clear();
        Sex_Box.setSelected(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "性别修改成功");
        alert.showAndWait();
        System.out.println("性别修改成功");

    }

    private void Address_Update() {
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set address=? where id=?");
            ps.setDouble(2, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Address_Txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        Id_Txfd.clear();
        Address_Txfd.clear();
        Address_Box.setSelected(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "地址修改成功");
        alert.showAndWait();
        System.out.println("地址修改成功");

    }


    private void Age_Update() {
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set age=? where id=?");
            ps.setDouble(2, Double.parseDouble(Name_Txfd.getText()));
            ps.setInt(1, Integer.parseInt(Age_Txfd.getText()));
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Age_Txfd.clear();
        Age_Box.setSelected(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "年龄修改成功");
        alert.showAndWait();
        System.out.println("年龄修改成功");
    }

    private void Birth_Update() {
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set birth=? where id=?");
            ps.setDouble(2, Double.parseDouble(Id_Txfd.getText()));
            ps.setInt(1, Integer.parseInt(Birth_Txfd.getText()));
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Birth_Txfd.clear();
        Birth_Box.setSelected(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "出生年月修改成功");
        alert.showAndWait();
        System.out.println("出生年月修改成功");
    }

    private void Marriage_Update() {
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set marriage_status=? where id=?");
            ps.setDouble(2, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(1, Marriage_Status_txfd.getText());
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        Id_Txfd.clear();
        Marriage_Status_txfd.getText();
        Marriage_Box.setSelected(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "薪水和职位修改成功");
        alert.showAndWait();
        System.out.println("职位和薪水修改成功");
    }

}