package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Choice extends Login {
    Choice() {
    }

    final HBox box = new HBox(50);
    final Button Bt_Input = new Button("录入");
    final Button Bt_Update = new Button("修改");
    final Button Bt_Select = new Button("查询");
    final Button Bt_Close = new Button("关闭");
    final Label Input_Label = new Label();
    final Label Update_label = new Label();
    final Label Select_Label = new Label();
    final Label Close_Label=new Label();
    final BorderPane borderpane = new BorderPane();
    final GridPane gridpane = new GridPane();

    public void start(Stage stage) {

        Body();
        Bt_Choice_Method(stage); // 按键选项
        box.setAlignment(Pos.TOP_CENTER); //Box 在面板中的位置
        box.setPadding(new Insets(0, 0, 100, 0)); // Box 节点距上，右，下，左 的距离
        box.getChildren().addAll(Bt_Input, Bt_Update, Bt_Select ,Bt_Close);

        borderpane.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\d.jpg"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        borderpane.setCenter(gridpane);
        borderpane.setBottom(box);

        Scene scene = new Scene(borderpane, 400, 400);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.setTitle("");

        stage.show();
    }

    private void Body() {
        //设置录入标签
        Input_Label.setText("1.录入信息");
        // 标签字体属性  字体类型、加粗、倾斜、大小
        Input_Label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // 标签字体颜色
        Input_Label.setTextFill(Color.BLUE);
        // 设置更新标签
        Update_label.setText("2.更新信息");
        // 字体属性 “字体类型、字体粗细、字体倾斜、字体大小”
        Update_label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // 字体颜色
        Update_label.setTextFill(Color.BLUE);
        //设置查询标签
        Select_Label.setText("3.查询信息");
        Select_Label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        Select_Label.setTextFill(Color.BLUE);
        Close_Label.setText("4.退出");
        Close_Label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        Close_Label.setTextFill(Color.BLUE);

        gridpane.setHgap(30);
        gridpane.setVgap(30);
        gridpane.add(Input_Label, 0, 0);
        gridpane.add(Update_label, 0, 1);
        gridpane.add(Select_Label, 0, 2);
        gridpane.add(Close_Label, 0, 3);
        gridpane.setAlignment(Pos.CENTER);

        //按钮填充颜色
        Bt_Input.setStyle("-fx-background-color:DODGERBLUE;-fx-text-fill:white");
        Bt_Update.setStyle("-fx-background-color:DODGERBLUE;-fx-text-fill:white");
        Bt_Select.setStyle("-fx-background-color:DODGERBLUE;-fx-text-fill:white");
        Bt_Close.setStyle("-fx-background-color:DODGERBLUE;-fx-text-fill:white");
    }

    private void Bt_Choice_Method(Stage stage) {

        Bt_Input.setOnAction(e -> new Input().start(stage));

        Bt_Update.setOnAction(e -> new Update().start(stage));

        Bt_Select.setOnAction(e -> new Inquire().start(stage));
        //关闭界面
        Bt_Close.setOnAction(e -> {stage.close();System.out.println("关闭窗体");});
    }

}
