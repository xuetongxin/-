package Teacher_Salary;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Choice extends Login {
    final Label Input_Label = new Label();
    final Label Update_label = new Label();
    final Label Select_Label = new Label();
    final Label Close_Label = new Label();
    final StackPane stackPane = new StackPane();
    final BorderPane borderpane = new BorderPane();
    final GridPane gridpane = new GridPane();

    public void start(Stage stage) {
        Body();
        Label_Button_Choice_Method(stage); // ����ѡ��

        borderpane.setCenter(gridpane);
        stackPane.getChildren().addAll(imageView, borderpane);
        stage.setScene(new Scene(stackPane, 550, 500));
        stage.setMinHeight(500);
        stage.setMinWidth(550);
        stage.setTitle("");
        stage.show();
    }

    private void Body() {
        //����¼���ǩ
        Input_Label.setText("1.¼����Ϣ");
        // ��ǩ��������  �������͡��Ӵ֡���б����С
        Input_Label.setFont(Font.font("�����п�", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // ��ǩ������ɫ
        Input_Label.setTextFill(Color.WHITE);
        // ���ø��±�ǩ
        Update_label.setText("2.������Ϣ");
        // �������� ���������͡������ϸ��������б�������С��
        Update_label.setFont(Font.font("�����п�", FontWeight.BOLD, FontPosture.ITALIC, 20));
        // ������ɫ
        Update_label.setTextFill(Color.WHITE);
        //���ò�ѯ��ǩ
        Select_Label.setText("3.��ѯ��Ϣ");
        Select_Label.setFont(Font.font("�����п�", FontWeight.BOLD, FontPosture.ITALIC, 20));
        Select_Label.setTextFill(Color.WHITE);
        Close_Label.setText("4.�˳�");
        Close_Label.setFont(Font.font("�����п�", FontWeight.BOLD, FontPosture.ITALIC, 20));
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
        //�رս���
        Close_Label.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit?");
            alert.showAndWait();
            if (alert.getResult().getButtonData().isDefaultButton()) {
                stage.close();
                System.out.println("�رմ���");
            }
            new OperationData().Exit_Log(super.Account_TextField.getText());

        });
    }

    void Choice_Inquire_Method(Stage stage) {
        Button Bt_Return = new Button("����");
        Text Teacher_Salary = new Text("��ʦ���ʲ�ѯ");
        Text Information_Inquire = new Text("��ʦ��Ϣ��ѯ");
        Teacher_Salary.setStyle("-fx-font-family: '�����п�';-fx-font-size: 30");
        Information_Inquire.setStyle("-fx-font-family: '�����п�';-fx-font-size: 30");

        Teacher_Salary.setOnMouseClicked(e -> {
            try {
                new Salary_Inquire().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Information_Inquire.setOnMouseClicked(e -> new Inquire().start(stage));
        Bt_Return.setOnAction(e -> new Choice().start(stage));

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(Teacher_Salary, Information_Inquire, Bt_Return);
        stage.setScene(new Scene(box, 500, 500));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setTitle("ѡ���ѯ����");
        stage.show();

    }

}