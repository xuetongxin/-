package Teacher_Salary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Delete extends Application {

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        BorderPane borderPane = new BorderPane();
        Button Bt_Return = new Button("Return");

        borderPane.setBottom(Bt_Return);
        Bt_Return.setOnAction(e -> new Choice().start(stage));

        Scene scene = new Scene(borderPane, 400, 400);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.setTitle("Delete");
        stage.show();
    }


}
