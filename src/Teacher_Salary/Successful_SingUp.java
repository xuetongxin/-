package Teacher_Salary;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Successful_SingUp extends Register {
    private final HBox hbox = new HBox(200);
    private final GridPane gridpane = new GridPane();
    private final BorderPane borderpane = new BorderPane();
    private final Button Bt_Ok = new Button("确认");
    private final Button Bt_Cancel = new Button("取消");

    @Override
    public void start(Stage stage) {

        gridpane.getChildren().add(new Label("注册成功!"));
        hbox.getChildren().addAll(Bt_Ok, Bt_Cancel);
        borderpane.setCenter(gridpane);
        borderpane.setBottom(hbox);

        stage.setX(500);
        stage.setY(200);
        stage.setScene(new Scene(borderpane, 400, 400));
        stage.setTitle("");
        stage.show();

        Bt_Ok.setOnAction(e -> new Login().start(stage));
        Bt_Cancel.setOnAction(e -> new Register().start(stage));

    }

}
