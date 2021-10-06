package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Input extends Choice {
    Input() {
    }
    private final GridPane gridpane = new GridPane();
    BorderPane borderPane=new BorderPane();
    HBox box1=new HBox();
    HBox box2=new HBox(200);
    private final Button Bt_Ok = new Button("确认");
    private final Button Bt_Return = new Button("返回");
    private final Button Bt_Reset = new Button("重置");
    private final Button Bt_Inquire=new Button("查询");

    private final TextField Id_Txfd = new TextField();
    private final TextField Name_Txfd = new TextField();
    private final TextField Position_Txfd = new TextField();
    private final TextField Salary_Txfd = new TextField();
    Stage window =new Stage();
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        box1.setPadding(new Insets(20,0,0,20));
        box2.setAlignment(Pos.CENTER);
        box2.setPadding(new Insets(0,0,20,0));

        Text_Field_Attribute();
        Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Salary_Txfd, Bt_Ok);
        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Reset.setOnAction(e -> new Input().start(stage));
        Bt_Ok.setOnAction(e -> JudgeMent_LogIN());
        Bt_Inquire.setOnAction(e->new Inquire().start(stage));
        borderPane.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\d.jpg"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset,Bt_Inquire);

        borderPane.setTop(box1);
        borderPane.setCenter(gridpane);
        borderPane.setBottom(box2);

        stage.setX(500);
        stage.setY(200);
        stage.setScene(new Scene(borderPane, 400, 400));
        stage.setTitle("录入");
        stage.show();
    }

    static void Panel_Layout(GridPane gridpane, TextField id_txfd, TextField name_txfd, TextField position_txfd, TextField salary_txfd, Button bt_ok) {
        Label Salary_Label=new Label("薪水");
        Salary_Label.setStyle("-fx-text-fill:'white'");
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(new Label("序号:"), 0, 0);
        gridpane.add(id_txfd, 1, 0);
        gridpane.add(new Label("名字:"), 0, 1);
        gridpane.add(name_txfd, 1, 1);
        gridpane.add(new Label("职位:"), 0, 2);
        gridpane.add(position_txfd, 1, 2);
        gridpane.add(Salary_Label, 0, 3);
        gridpane.add(salary_txfd, 1, 3);
        gridpane.add(bt_ok, 0, 5);

    }
    private void JudgeMent_LogIN(){
        if (Id_Txfd.getLength()==0||Name_Txfd.getLength()==0||Position_Txfd.getLength()==0||Salary_Txfd.getLength()==0){
            System.out.println("不能为空");
        }else if(Id_Txfd.getLength()>10||Salary_Txfd.getLength()>10){
            System.out.println("ID||Salary 长度大于10");
        }else
            Mysql_Input();
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
            Input_Successful();
            System.out.println("录入成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private void Input_Successful(){
        Stage window1=new Stage();
        Pane pane=new Pane();
        Text text=new Text("录入成功");
        text.setStyle("-fx-font-family:'华文行楷' ;-fx-font-size: 20");
        text.setX(120);
        text.setY(100);
        pane.getChildren().add(text);
        window1.setScene(new Scene(pane,300,200));
        window1.setX(550);
        window1.setY(300);
        window1.showAndWait();
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

