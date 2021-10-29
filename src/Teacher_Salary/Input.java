package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Input extends OperationData {
    final StackPane stackPane = new StackPane();

    final HBox box1 = new HBox();
    final HBox box2 = new HBox(200);
    final GridPane gridpane = new GridPane();
    private final Button Bt_Ok = new Button("确认");
    final Button Bt_Return = new Button("返回");
    final Button Bt_Reset = new Button("重置");
    final Button Bt_Inquire = new Button("查询");
    final TextField Id_Txfd = new TextField();
    final TextField Name_Txfd = new TextField();
    final TextField Sex_Txfd = new TextField();
    final TextField Birth_Txfd = new TextField();
    final TextField Age_Txfd = new TextField();
    final TextField Marriage_Status_txfd = new TextField();
    final TextField Address_Txfd = new TextField();
    final TextField Position_Txfd = new TextField();
    BorderPane borderPane = new BorderPane();

    public void start(Stage stage) {
        // TODO 自动生成的方法存根
        box1.setPadding(new Insets(20, 0, 0, 20));
        box2.setAlignment(Pos.CENTER);
        box2.setPadding(new Insets(0, 0, 100, 0));

        Id_Txfd.setPromptText("！必须为数字");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Id_Txfd.setPrefColumnCount(150);
        Id_Txfd.setPrefWidth(150);

        Panel_Layout(gridpane, Id_Txfd, Name_Txfd, Position_Txfd, Sex_Txfd, Birth_Txfd, Age_Txfd, Address_Txfd, Marriage_Status_txfd, Bt_Ok);

        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Reset.setOnAction(e -> Clear_TextField());
        Bt_Ok.setOnAction(e -> {
            try {
                Judgement_Input();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Bt_Inquire.setOnAction(e -> new Choice().Choice_Inquire_Method(stage));

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset, Bt_Inquire);

        borderPane.setTop(box1);
        borderPane.setCenter(gridpane);
        borderPane.setBottom(box2);
        stackPane.getChildren().addAll(imageView, borderPane);

        stage.setScene(new Scene(stackPane, 500, 500));
        stage.setTitle("录入");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.show();
    }

    private void Judgement_Input() throws SQLException {
        if (Id_Txfd.getLength() == 0 || Name_Txfd.getLength() == 0 || Position_Txfd.getLength() == 0 || Age_Txfd.getLength() == 0 || Address_Txfd.getLength() == 0 || Marriage_Status_txfd.getLength() == 0 || Birth_Txfd.getLength() == 0 || Sex_Txfd.getLength() == 0) {
            Warring_Tip("Input information is not null");
        } else if (Id_Txfd.getLength() > 10) {
            Error_Tip("ID长度小于8或者大于15");
        } else {
            if (super.ID_Exist(Integer.parseInt(Id_Txfd.getText()))) {
                Error_Tip("Teacher has existed");
            } else
                Input_Teacher_data(Id_Txfd, Name_Txfd, Birth_Txfd, Age_Txfd, Marriage_Status_txfd, Address_Txfd, Position_Txfd, Sex_Txfd);
        }
    }


    private static void Panel_Layout(GridPane gridpane, TextField id_txfd, TextField name_txfd, TextField position_txfd, TextField sex_Txfd, TextField birth_Txfd, TextField age_Txfd, TextField address_Txfd, TextField marriage_Status_txfd, Button bt_ok) {
        Label Id_Label = new Label("序号");
        Label Name_label = new Label("名字");
        Label Sex_Label = new Label("性别");
        Label Birth_Label = new Label("出生年月");
        Label Age_Label = new Label("年龄");
        Label Marriage_status_label = new Label("婚姻状态");
        Label Address_Label = new Label("家庭地址");
        Label Position_Label = new Label("职位");

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
        Salary_Inquire.Panel_Layout(Id_Label, Name_label, Sex_Label, Birth_Label, gridpane, id_txfd, name_txfd, sex_Txfd, birth_Txfd);
        gridpane.add(Age_Label, 0, 4);
        gridpane.add(age_Txfd, 1, 4);
        gridpane.add(Marriage_status_label, 0, 5);
        gridpane.add(marriage_Status_txfd, 1, 5);
        gridpane.add(Address_Label, 0, 6);
        gridpane.add(address_Txfd, 1, 6);
        gridpane.add(Position_Label, 0, 7);
        gridpane.add(position_txfd, 1, 7);
        gridpane.add(bt_ok, 0, 9);

    }

    int Clear_TextField() {
        Id_Txfd.clear();
        Name_Txfd.clear();
        Position_Txfd.clear();
        Sex_Txfd.clear();
        Birth_Txfd.clear();
        Age_Txfd.clear();
        Marriage_Status_txfd.clear();
        Address_Txfd.clear();
        return 1;
    }
}

