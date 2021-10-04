package Teacher_Salary;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Delete extends Choice {

    @Override
    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 400);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.setTitle("Select");
        stage.show();
    }

}
