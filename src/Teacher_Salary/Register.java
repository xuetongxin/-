package Teacher_Salary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Register extends Login {
    final StackPane stackPane = new StackPane();
    private final HBox box = new HBox();
    final Button Bt_Register = new Button("ȷ��");
    final Button Bt_Return = new Button("����");
    final Label passwd_Label2 = new Label("��֤����");
    final PasswordField passwd_Field2 = new PasswordField();
    final CheckBox Check_SingUp = new CheckBox("ע��");
    final CheckBox Check_Reset = new CheckBox("��������");


    static void Panel_Layout(Button register, Label lb1, Label lb2, TextField txfd1, TextField txfd2, GridPane gridpane) {
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.add(lb1, 0, 0);
        gridpane.add(txfd1, 1, 0);
        gridpane.add(lb2, 0, 1);
        gridpane.add(txfd2, 1, 1);
        gridpane.add(register, 0, 7);
        gridpane.setAlignment(Pos.CENTER);
    }

    public void start(Stage stage,int i) {

        // TODO �Զ����ɵķ������
        Account_Label.setStyle("-fx-text-fill: 'white';-fx-font-family: '�����п�';-fx-font-size: 15");
        Passwd_Label.setStyle("-fx-text-fill: 'white';-fx-font-family: '�����п�';-fx-font-size: 15");
        passwd_Label2.setStyle("-fx-text-fill: 'white';-fx-font-family: '�����п�';-fx-font-size: 15");

        Account_TextField.setPromptText("8~15���֡���ĸ ���ܴ��ڷ���");
        Passwd_TextField.setPromptText("8~15���֡���ĸ �ܴ��ڷ���");
        passwd_Field2.setPromptText("�ٴ���������");
        Passwd_TextField.setPrefColumnCount(20);

        Check_SingUp.setStyle("-fx-text-fill: 'white';-fx-font-family: '�����п�';-fx-font-size: 15");
        Check_Reset.setStyle("-fx-text-fill: 'white';-fx-font-family: '�����п�';-fx-font-size: 15");

        box.setPadding(new Insets(20, 0, 0, 20));
        box.getChildren().add(Bt_Return);

        Panel_Layout(Bt_Register, Account_Label, Passwd_Label, Account_TextField, Passwd_TextField, gridpane);
        gridpane.add(passwd_Label2, 0, 2);
        gridpane.add(passwd_Field2, 1, 2);
        if (i==1){
            gridpane.add(Check_SingUp, 2, 3);
        }
        if (i==2){
            gridpane.add(Check_Reset, 2, 3);
        }

        Bt_Return.setOnAction(e -> new Login().start(stage));
        Bt_Register.setOnAction(e -> Judgement(stage));
        Passwd_TextField.setOnAction(e -> Judgement(stage));
        borderPane.setCenter(gridpane);
        borderPane.setTop(box);
        stackPane.getChildren().addAll(imageView, borderPane);
        Scene scene = new Scene(stackPane, 500, 500);
        stage.setScene(scene);
        stage.setTitle("ע��");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.show();
    }


    void Judgement(Stage stage) {
        if ((Account_TextField.getText().matches("") || Passwd_TextField.getText().matches(""))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "�˻���������Ϊ��");
            alert.showAndWait();
            System.out.println("�˻���������Ϊ��");
        } else if (!(Account_TextField.getLength() >= 8 && Account_TextField.getLength() <= 15 && Passwd_TextField.getLength() >= 8 && Passwd_TextField.getLength() <= 15)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "�˻��������볤��С��8|����15");
            alert.showAndWait();
            System.out.println("�˻��������볤��С��8|����15");
        } else if (passwd_Field2.getText().matches(Passwd_TextField.getText())) {

            try {

                if (super.Select_User_Account(Account_TextField)) {
                    Information_Tip("User Exist");
                    if (Check_Reset.isSelected()) {
                        super.ResetPassword(Account_TextField, Passwd_TextField, passwd_Field2);
                        Check_SingUp.setSelected(false);
                    } else
                        Warring_Tip("please select your option");
                } else {
                    if (Check_SingUp.isSelected()) {
                        super.Register_user(Account_TextField, Passwd_TextField, passwd_Field2);
                        Check_SingUp.setSelected(false);
                    } else if (Check_SingUp.isSelected()) {
                        Warring_Tip("Please select correct option");
                    } else if (Check_SingUp.isSelected() && Check_Reset.isSelected())
                        Warring_Tip("please select one option");
                    else
                        Warring_Tip("please select your option");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            Warring_Tip("Two different password");

    }

}
