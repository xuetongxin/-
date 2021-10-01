package Teacher_Salary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Choice extends Application {
    Choice() {
    }


    Pane pane = new Pane();
    HBox box = new HBox(10);
    Button bt_input = new Button("input");
    Button bt_update = new Button("update");
    Button bt_select = new Button("select");
    Button bt_delete = new Button("delete");
    Button bt_close= new Button("close");
    Label label1 = null;
    Label label2 = null;
    Label label3 = null;
    Label label4 = null;;
    Button btclose = new Button("Close");
    BorderPane borderpane = new BorderPane();
    GridPane gridpane = new GridPane();

    public void start(Stage stage) throws Exception {

        //设置录入标签
        Label label1 = new Label("1.录入信息");
        // 标签字体属性
        label1.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // 标签字体颜色
        label1.setTextFill(Color.BLUE);
        // 设置更新标签
        Label label2 = new Label("2.更新信息");
        // 字体属性 “字体类型、字体粗细、字体倾斜、字体大小”
        label2.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // 字体颜色
        label2.setTextFill(Color.BLUE);
        Label label3 = new Label("3.查询信息");
        label3.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        label3.setTextFill(Color.BLUE);
        Label label4 = new Label("4.删除信息");
        label4.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        label4.setTextFill(Color.RED);

        bt_delete.setTextFill(Color.RED);  //按钮填充颜色
        box.setAlignment(Pos.CENTER); //Box 在面板中的位置
        box.setPadding(new Insets(0, 0, 10, 0)); // Box 节点距上，右，下，左 的距离
        gridpane.setHgap(30);
        gridpane.setVgap(30);
        gridpane.add(label1, 0, 0);
        gridpane.add(label2, 0, 1);
        gridpane.add(label3, 0, 2);
        gridpane.add(label4, 0, 3);
        gridpane.setAlignment(Pos.CENTER);
        // Body();
        // Panel_Loyout(); // 面板布局
        Bt_Choice(stage); // 按键选项

        box.getChildren().addAll(bt_input, bt_update, bt_delete, bt_select,bt_close);
        borderpane.setCenter(gridpane);
        borderpane.setBottom(box);
        Scene scene = new Scene(borderpane, 400, 410);
        stage.setScene(scene);
        stage.setTitle("choice");
        stage.show();
    }

    /*
     * public void Body() {
     *
     * Label label1 = new Label("1.录入信息");
     * label1.setFont(Font.font("Time New Roman", FontWeight.BOLD,
     * FontPosture.ITALIC, 20)); label1.setTextFill(Color.BLUE); Label label2 = new
     * Label("2.更新信息"); label2.setFont(Font.font("Time New Roman", FontWeight.BOLD,
     * FontPosture.ITALIC, 20)); label2.setTextFill(Color.BLUE); Label label3 = new
     * Label("3.查询信息"); label3.setFont(Font.font("Time New Roman", FontWeight.BOLD,
     * FontPosture.ITALIC, 20)); label3.setTextFill(Color.BLUE); Label label4 = new
     * Label("4.删除信息"); label4.setFont(Font.font("Time New Roman", FontWeight.BOLD,
     * FontPosture.ITALIC, 20)); label4.setTextFill(Color.RED);
     *
     * }
     *
     * private void Panel_Loyout() { bt4.setTextFill(Color.RED);
     * box.setAlignment(Pos.CENTER); box.setPadding(new Insets(0, 0, 10, 0));
     * gridpane.setHgap(30); gridpane.setVgap(30); gridpane.add(label1, 0, 0);
     * gridpane.add(label2, 0, 1); gridpane.add(label3, 0, 2); gridpane.add(label4,
     * 0, 3); gridpane.setAlignment(Pos.CENTER); }
     */
    private void Bt_Choice(Stage stage) {
        Input input = new Input();
        bt_input.setOnAction(e -> {
            try {
                input.start(stage);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        });

        bt_update.setOnAction(e -> {
            Update update = new Update();
            try {
                update.start(stage);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        });

        bt_select.setOnAction(e -> {
            Select select = new Select();
            try {
                select.start(stage);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        });

        bt_delete.setOnAction(e -> {
            Delete delect = new Delete();
            try {
                delect.start(stage);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        });
        //未能关闭界面
        btclose.setOnAction(e -> {
            stage.close();
        });
    }

}
