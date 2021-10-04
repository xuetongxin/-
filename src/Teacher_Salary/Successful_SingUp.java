package Teacher_Salary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Successful_SingUp extends Application {
        private HBox hbox = new HBox(200);
        private GridPane gridpane = new GridPane();
        private BorderPane borderpane = new BorderPane();
        private Button btok = new Button("确认");
        private Button btcancle = new Button("取消");
    @Override
    public void start(Stage stage) {

        gridpane.getChildren().add(new Label("注册成功!"));
        hbox.getChildren().addAll(btok, btcancle);
        borderpane.setCenter(gridpane);
        borderpane.setBottom(hbox);

        stage.setX(500);
        stage.setY(200);
        stage.setScene(new Scene(borderpane, 400, 400));
        stage.setTitle("");
        stage.show();

        btok.setOnAction(e -> new Login().start(stage));
        btcancle.setOnAction(e -> new Register().start(stage));

    }

}
