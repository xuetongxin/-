package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class Input extends Choice {
    Input() {
    }
    private final GridPane gridpane = new GridPane();
    BorderPane borderPane=new BorderPane();
    final StackPane stackPane=new StackPane();
    final HBox box1=new HBox();
    final HBox box2=new HBox(200);
    private final Button Bt_Ok = new Button("确认");
    private final Button Bt_Return = new Button("返回");
    private final Button Bt_Reset = new Button("重置");
    private final Button Bt_Inquire=new Button("查询");

    private final TextField Id_Txfd = new TextField();
    private final TextField Name_Txfd = new TextField();
    private final TextField Sex_Txfd=new TextField();
    private final TextField Birth_Txfd=new TextField();
    private final TextField Age_Txfd=new TextField();
    private final TextField Marriage_Status_txfd=new TextField();
    private final TextField Address_Txfd=new TextField();
    private final TextField Position_Txfd = new TextField();
    //private final TextField Salary_Txfd = new TextField();
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        box1.setPadding(new Insets(20,0,0,20));
        box2.setAlignment(Pos.CENTER);
        box2.setPadding(new Insets(0,0,200,0));

        Id_Txfd.setPromptText("！必须为数字");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Id_Txfd.setPrefColumnCount(150);
        Id_Txfd.setPrefWidth(150);

        imageview.setFitHeight(1080);
        imageview.setFitWidth(1980); // 背景图片属性
        imageview.setImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"));

        Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Sex_Txfd,Birth_Txfd,Age_Txfd,Address_Txfd,Marriage_Status_txfd, Bt_Ok);

        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Reset.setOnAction(e -> new Input().start(stage));
        Bt_Ok.setOnAction(e -> Judgement_Input());
        Bt_Inquire.setOnAction(e->new Inquire().start(stage));
        //borderPane.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\b.jpg"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        Id_Txfd.setOnAction(e->Judgement_Input());
        Name_Txfd.setOnAction(e->Judgement_Input());
        Position_Txfd.setOnAction(e->Judgement_Input());
        //Salary_Txfd.setOnAction(e->Judgement_Input());

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset,Bt_Inquire);

        borderPane.setTop(box1);
        borderPane.setCenter(gridpane);
        borderPane.setBottom(box2);
        stackPane.getChildren().addAll(imageview,borderPane);

        //stage.setX(500);
        //stage.setY(200);
        stage.setScene(new Scene(stackPane));
        stage.setTitle("录入");
        stage.show();
    }

    private static void Panel_Layout(GridPane gridpane, TextField id_txfd, TextField name_txfd, TextField position_txfd, TextField sex_Txfd, TextField birth_Txfd, TextField age_Txfd, TextField address_Txfd, TextField marriage_Status_txfd, Button bt_ok) {
        Label Id_Label=new Label("序号");
        Label Name_label=new Label("名字");
        Label Sex_Label=new Label("性别");
        Label Birth_Label=new Label("出生年月");
        Label Age_Label= new Label("年龄");
        Label Marriage_status_label=new Label("婚姻状态");
        Label Address_Label=new Label("家庭地址");
        Label Position_Label=new Label("职位");
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
        gridpane.add(id_txfd, 1, 0);
        gridpane.add(Name_label, 0, 1);
        gridpane.add(name_txfd, 1, 1);
        gridpane.add(Sex_Label,0,2);
        gridpane.add(sex_Txfd,1,2);
        gridpane.add(Birth_Label,0,3);
        gridpane.add(birth_Txfd,1,3);
        gridpane.add(Age_Label,0,4);
        gridpane.add(age_Txfd,1,4);
        gridpane.add(Marriage_status_label,0,5);
        gridpane.add(marriage_Status_txfd,1,5);
        gridpane.add(Address_Label,0,6);
        gridpane.add(address_Txfd,1,6);
        gridpane.add(Position_Label, 0, 7);
        gridpane.add(position_txfd, 1, 7);
        //gridpane.add(Salary_Label, 0, 8);
        //gridpane.add(salary_txfd, 1, 8);
        gridpane.add(bt_ok, 0, 9);

    }
    private void Judgement_Input(){
        if (Id_Txfd.getLength()==0||Name_Txfd.getLength()==0||Position_Txfd.getLength()==0||Age_Txfd.getLength()==0||Address_Txfd.getLength()==0||Marriage_Status_txfd.getLength()==0||Birth_Txfd.getLength()==0||Sex_Txfd.getLength()==0){
            Alert alert=new Alert(Alert.AlertType.WARNING,"输入信息不能为空");
            alert.showAndWait();
        }else if(Id_Txfd.getLength()>10){
            Alert alert=new Alert(Alert.AlertType.ERROR,"ID长度小于8或者大于15");
            alert.showAndWait();
        }else
            user_exist();
    }

    private void user_exist(){
        boolean boole=false;
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery("select id from teacher_salary");
            System.out.println("连接成功");

            while(resultSet.next()){
                if (resultSet.getString(1).matches(Id_Txfd.getText())){
                    boole=true;
                }
            }
            System.out.println(boole);
            if(boole)
                System.out.println("用户存在");

            else
                Mysql_Input();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private void Mysql_Input() {
        Connection con;
        PreparedStatement ps;
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            System.out.println("连接成功");


            ps = con.prepareStatement("insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position) values (?,?,?,?,?,?,?,?) \n");
            ps.setDouble(1, Double.parseDouble(Id_Txfd.getText()));
            ps.setString(2, Name_Txfd.getText());
            ps.setString(3,Sex_Txfd.getText());
            ps.setInt(4,Integer.parseInt(Birth_Txfd.getText()));
            ps.setInt(5,Integer.parseInt(Age_Txfd.getText()));
            ps.setString(6,Marriage_Status_txfd.getText());
            ps.setString(7,Address_Txfd.getText());
            ps.setString(8, Position_Txfd.getText());
            ps.executeUpdate();

            Id_Txfd.clear();
            Name_Txfd.clear();
            Position_Txfd.clear();
            Sex_Txfd.clear();
            Birth_Txfd.clear();
            Age_Txfd.clear();
            Marriage_Status_txfd.clear();
            Address_Txfd.clear();
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"录入成功");
            alert.showAndWait();
            System.out.println("录入成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

}

