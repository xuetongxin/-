package Teacher_Salary;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Choice extends Login {
    final HBox box = new HBox(50);
    final Label Input_Label = new Label();
    final Label Update_label = new Label();
    final Label Select_Label = new Label();
    final Label Close_Label = new Label();
    final StackPane stackPane = new StackPane();
    final BorderPane borderpane = new BorderPane();
    final GridPane gridpane = new GridPane();

    public void start(Stage stage) {
        Body();
        Label_Button_Choice_Method(stage); // 按键选项
        imageView.setFitHeight(1080);
        imageView.setFitWidth(1985); // 背景图片属性
        imageView.setImage(new Image("file:/home/ximeng/IdeaProjects/IJ_WorkSpace/out/production/IJ_WorkSpace/Teacher_Salary/image/bg.jpg"));
        borderpane.setCenter(gridpane);
        stackPane.getChildren().addAll(imageView, borderpane);
        stage.setScene(new Scene(stackPane, 500, 500));
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setTitle("");
        stage.show();
    }

    private void Body() {
        //设置录入标签
        Input_Label.setText("1.录入信息");
        // 标签字体属性  字体类型、加粗、倾斜、大小
        Input_Label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // 标签字体颜色
        Input_Label.setTextFill(Color.WHITE);
        // 设置更新标签
        Update_label.setText("2.更新信息");
        // 字体属性 “字体类型、字体粗细、字体倾斜、字体大小”
        Update_label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // 字体颜色
        Update_label.setTextFill(Color.WHITE);
        //设置查询标签
        Select_Label.setText("3.查询信息");
        Select_Label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        Select_Label.setTextFill(Color.WHITE);
        Close_Label.setText("4.退出");
        Close_Label.setFont(Font.font("华文行楷", FontWeight.BOLD, FontPosture.ITALIC, 20));
        Close_Label.setTextFill(Color.WHITE);

        gridpane.setHgap(30);
        gridpane.setVgap(30);
        gridpane.add(Input_Label, 0, 0);
        gridpane.add(Update_label, 0, 1);
        gridpane.add(Select_Label, 0, 2);
        gridpane.add(Close_Label, 0, 3);
        gridpane.setAlignment(Pos.CENTER);

    }

    private void Label_Button_Choice_Method(Stage stage) {
        Input_Label.setOnMouseClicked(e -> new Input().start(stage));

        Update_label.setOnMouseClicked(e -> new Update().start(stage));

        Select_Label.setOnMouseClicked(e -> Choice_Inquire_Method(stage));
        //关闭界面
        Close_Label.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit?");
            alert.showAndWait();
            if (alert.getResult().getButtonData().isDefaultButton()) {
                stage.close();
                System.out.println("关闭窗体");
            }
        });
    }

    void Choice_Inquire_Method(Stage stage) {
        Button Bt_Return = new Button("返回");
        Text Teacher_Salary = new Text("教师工资查询");
        Text Information_Inquire = new Text("教师信息查询");
        Teacher_Salary.setStyle("-fx-font-family: '华文行楷';-fx-font-size: 30");
        Information_Inquire.setStyle("-fx-font-family: '华文行楷';-fx-font-size: 30");

        Teacher_Salary.setOnMouseClicked(e -> {
            try {
                new Salary_Inquire().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Information_Inquire.setOnMouseClicked(e -> {
            new Inquire().start(stage);
        });
        Bt_Return.setOnAction(e -> new Choice().start(stage));

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(Teacher_Salary, Information_Inquire, Bt_Return);
        stage.setScene(new Scene(box, 500, 500));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setTitle("选择查询类型");
        stage.show();

    }

}
