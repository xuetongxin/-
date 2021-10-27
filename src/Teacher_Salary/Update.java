package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Update extends Input {

    final Button Bt_Update = new Button("确认");
    final Button Bt_Inquire = new Button("查询");
    final CheckBox Name_Box = new CheckBox();
    final CheckBox Position_Box = new CheckBox();
    final CheckBox Age_Box = new CheckBox();
    final CheckBox Marriage_Box = new CheckBox();
    final CheckBox Birth_Box = new CheckBox();
    final CheckBox Sex_Box = new CheckBox();
    final CheckBox Address_Box = new CheckBox();

    @Override

    public void start(Stage stage) {
        // TODO 自动生成的方法存根

        Id_Txfd.setPromptText("ID必须正确");
        Name_Txfd.setPromptText("输入数字、字母、汉字");
        Position_Txfd.setPromptText("输入数字、字母、汉字");

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
        gridpane.add(Id_Label, 0, 0);
        gridpane.add(Id_Txfd, 1, 0);

        gridpane.add(Name_label, 0, 1);
        gridpane.add(Name_Txfd, 1, 1);
        gridpane.add(Name_Box, 2, 1);

        gridpane.add(Position_Label, 0, 2);
        gridpane.add(Position_Txfd, 1, 2);
        gridpane.add(Position_Box, 2, 2);

        gridpane.add(Sex_Label, 0, 3);
        gridpane.add(Sex_Txfd, 1, 3);
        gridpane.add(Sex_Box, 2, 3);

        gridpane.add(Birth_Label, 0, 4);
        gridpane.add(Birth_Txfd, 1, 4);
        gridpane.add(Birth_Box, 2, 4);

        gridpane.add(Age_Label, 0, 5);
        gridpane.add(Age_Txfd, 1, 5);
        gridpane.add(Age_Box, 2, 5);

        gridpane.add(Marriage_status_label, 0, 6);
        gridpane.add(Marriage_Status_txfd, 1, 6);
        gridpane.add(Marriage_Box, 2, 6);

        gridpane.add(Address_Label, 0, 7);
        gridpane.add(Address_Txfd, 1, 7);
        gridpane.add(Address_Box, 2, 7);

        gridpane.add(Bt_Update, 0, 9);

        box1.getChildren().add(Bt_Return);
        box2.getChildren().addAll(Bt_Reset, Bt_Inquire);
        box1.setPadding(new Insets(20, 0, 0, 20));
        box2.setPadding(new Insets(0, 0, 100, 0));
        box2.setAlignment(Pos.CENTER);

        Bt_Reset.setOnAction(e -> new Update().start(stage));
        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Inquire.setOnAction(e -> new Inquire().start(stage));
        Bt_Update.setOnAction(e -> Inquire_User());

        borderPane.setTop(box1);
        borderPane.setCenter(gridpane);
        borderPane.setBottom(box2);

        stackPane.getChildren().addAll(imageView, borderPane);

        stage.setScene(new Scene(stackPane, 500, 500));
        stage.setTitle("修改");
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.show();
    }

    private void Inquire_User() {
        try {
            if (super.ID_Exist(Integer.parseInt(Id_Txfd.getText())))
                Choice_Update();
            else {
                System.out.println("用户不存在");
                Alert alert = new Alert(Alert.AlertType.ERROR, "用户不存在");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void Choice_Update() {
        int choice;
        if ((Name_Box.isSelected() && Position_Box.isSelected() && Sex_Box.isSelected() && Age_Box.isSelected() && Birth_Box.isSelected() && Marriage_Box.isSelected() && Address_Box.isSelected()))
            choice = 0;
        else if (Name_Box.isSelected())
            choice = 1;
        else if (Position_Box.isSelected())
            choice = 2;
        else if (Sex_Box.isSelected())
            choice = 3;
        else if (Birth_Box.isSelected())
            choice = 4;
        else if (Age_Box.isSelected())
            choice = 5;
        else if (Marriage_Box.isSelected())
            choice = 6;
        else if (Address_Box.isSelected())
            choice = 7;
        else
            choice = 8;

        switch (choice) {
            case 0 -> {
                if (Name_Txfd.getLength() > 0 && Position_Txfd.getLength() > 0 && Sex_Txfd.getLength() > 0 && Age_Txfd.getLength() > 0 && Birth_Txfd.getLength() > 0 && Marriage_Status_txfd.getLength() > 0 && Address_Txfd.getLength() > 0) {
                    super.All_Update(Integer.parseInt(Id_Txfd.getText()), Name_Txfd.getText(), Sex_Txfd.getText(), Integer.parseInt(Age_Txfd.getText()), Integer.parseInt(Birth_Txfd.getText()), Marriage_Status_txfd.getText(), Position_Txfd.getText(), Address_Txfd.getText());
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();

            }
            case 1 -> {
                if (Name_Txfd.getLength() > 0) {
                    super.Name_Update(Integer.parseInt(Id_Txfd.getText()), Name_Txfd.getText());
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();

            }
            case 2 -> {
                if (Position_Txfd.getLength() > 0) {
                    super.Position_Update(Integer.parseInt(Id_Txfd.getText()), Position_Txfd.getText());
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();
            }
            case 3 -> {
                if (Sex_Txfd.getLength() > 0) {
                    super.Sex_Update(Integer.parseInt(Id_Txfd.getText()), Sex_Txfd.getText());
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();
            }
            case 4 -> {
                if (Birth_Txfd.getLength() > 0) {
                    super.Birth_Update(Integer.parseInt(Id_Txfd.getText()), Integer.parseInt(Birth_Txfd.getText()));
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();
            }
            case 5 -> {
                if (Age_Txfd.getLength() > 0) {
                    super.Age_Update(Integer.parseInt(Id_Txfd.getText()), Integer.parseInt(Age_Txfd.getText()));
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();
            }
            case 6 -> {
                if (Marriage_Status_txfd.getLength() > 0) {
                    super.Marriage_Update(Integer.parseInt(Id_Txfd.getText()), Marriage_Status_txfd.getText());
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();
            }
            case 7 -> {
                if (Address_Txfd.getLength() > 0) {
                    super.Address_Update(Integer.parseInt(Id_Txfd.getText()), Address_Txfd.getText());
                    super.Update_Log(choice, Id_Txfd.getText());
                } else
                    Null_Tips();
            }
            case 8 -> {
                Alert alert = new Alert(Alert.AlertType.WARNING, "请选择你要修改的信息，一次只能修改一个或者全部修改");
                alert.showAndWait();
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
        System.out.println(choice);
    }

    private void Null_Tips() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "不能为空");
        alert.showAndWait();
    }

    void Clear_Box() {
        Name_Box.setSelected(false);
        Position_Box.setSelected(false);
        Sex_Box.setSelected(false);
        Marriage_Box.setSelected(false);
        Birth_Box.setSelected(false);
        Age_Box.setSelected(false);
        Address_Box.setSelected(false);
    }
}