package Test;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class First_Stage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage stage) {
        window = stage;
        FlowPane pane = new FlowPane(Orientation.VERTICAL, 20, 20);
        pane.setPadding(new Insets(20, 0, 0, 20));

        Button bt_next = new Button("Second_Stage");
        bt_next.setOnAction(e -> {
            Second_Stage();
        });

        Text txfd = new Text("hello java");
        txfd.setFont(Font.font("Times New Roman", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC, 30));


        pane.getChildren().addAll(bt_next, txfd);
        Scene scene1 = new Scene(pane, 300, 300);
        window.setX(500);
        window.setY(200);
        window.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("当前高度=" + t1);
            }
        });

        window.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("当前宽度=" + t1);
            }
        });

        window.setOnHidden((event) -> {
            System.out.println("隐藏舞台");
        });

        window.setScene(scene1);
        window.setTitle("First_Stage");
        window.show();
    }

    private void Second_Stage() {
        HBox pane = new HBox();
        Button bt_return = new Button("First_Stage");
        Button bt_close = new Button("Close");

        bt_return.setOnAction(e -> {
            try {
                start(window);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        bt_close.setOnAction(e -> {
            window.setOnCloseRequest((event) -> {
                System.out.println("Closing Stage");
            });

            Close_Stage();
            System.out.println("Closing Stage");
        });

        pane.getChildren().addAll(bt_return, bt_close);
        Scene scene2 = new Scene(pane, 300, 300);


        window.setX(500);
        window.setY(200);
        window.setScene(scene2);
        window.setTitle("Second_Stage");
        //window.show();
    }

    private void Close_Stage() {
        BorderPane borderpane = new BorderPane();
        HBox box = new HBox(10);
        Text tx1 = new Text("if you want to close this stage");

        Button bt_ok = new Button("YES");
        Button bt_no = new Button("NO");

        bt_ok.setOnAction(e -> window.close());
        bt_no.setOnAction(e -> Second_Stage());

        box.getChildren().addAll(bt_ok, bt_no);
        borderpane.setCenter(tx1);
        borderpane.setBottom(box);

        Scene scene3 = new Scene(borderpane, 300, 200);
        scene3.setFill(Color.GREENYELLOW);
        window.setX(500);
        window.setY(200);
        window.setScene(scene3);
        window.setTitle("");
        window.show();

    }
}
