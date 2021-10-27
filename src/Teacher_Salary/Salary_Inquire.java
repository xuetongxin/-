package Teacher_Salary;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Salary_Inquire extends OperationData{
    TextField ID_TextField = new TextField();
    TextField Name_TextField = new TextField();
    TextField Allowance_TextField = new TextField();
    TextField BaseSalary_TextField = new TextField();
    TextField Tootle_TextField = new TextField();
    TextField AverageSalary_TextField = new TextField();

    public void start(Stage stage) {

        Label ID_Label = new Label("����");
        Label Name_Label = new Label("����");
        Label Allowance_Label = new Label("����");
        Label Base_Label = new Label("��������");
        Label Tootle_Label = new Label("�ܹ���");
        Label AverageSalary_Label = new Label("ƽ������");

        ID_Label.setStyle("-fx-font-family: '�����п�';-fx-text-fill: 'white';-fx-font-size: 20");
        Name_Label.setStyle("-fx-font-family: '�����п�';-fx-text-fill: 'white';-fx-font-size: 20");
        Allowance_Label.setStyle("-fx-font-family: '�����п�';-fx-text-fill: 'white';-fx-font-size: 20");
        Base_Label.setStyle("-fx-font-family: '�����п�';-fx-text-fill: 'white';-fx-font-size: 20");
        Tootle_Label.setStyle("-fx-font-family: '�����п�';-fx-text-fill: 'white';-fx-font-size: 20");
        AverageSalary_Label.setStyle("-fx-font-family: '�����п�';-fx-text-fill: 'white';-fx-font-size: 20");

        Button Bt_Inquire = new Button("��ѯ");
        Button Bt_Return = new Button("����");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Panel_Layout(ID_Label, Name_Label, Allowance_Label, Base_Label, gridPane, ID_TextField, Name_TextField, Allowance_TextField, BaseSalary_TextField);

        gridPane.add(Tootle_Label, 0, 10);
        gridPane.add(Tootle_TextField, 1, 10);
        gridPane.add(AverageSalary_Label, 0, 11);
        gridPane.add(AverageSalary_TextField, 1, 11);
        gridPane.add(Bt_Inquire, 0, 12);
        gridPane.add(Bt_Return, 2, 12);


        Bt_Inquire.setOnAction(e -> Inquire_Result());
        Bt_Return.setOnAction(e -> {
            try {
                new Choice().Choice_Inquire_Method(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, borderPane);
        stage.setScene(new Scene(stackPane, 500, 500));
        stage.setTitle("��ʦ���ʲ�ѯ");
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.show();

    }
    static void Panel_Layout(Label ID_Label, Label name_Label, Label allowance_Label, Label base_Label, GridPane gridPane, TextField id_textField, TextField name_textField, TextField allowance_textField, TextField baseSalary_textField) {
        gridPane.add(ID_Label, 0, 0);
        gridPane.add(id_textField, 1, 0);
        gridPane.add(name_Label, 0, 1);
        gridPane.add(name_textField, 1, 1);
        gridPane.add(allowance_Label, 0, 2);
        gridPane.add(allowance_textField, 1, 2);
        gridPane.add(base_Label, 0, 3);
        gridPane.add(baseSalary_textField, 1, 3);
    }

    private void Inquire_Result() {
        if (ID_TextField.getLength() > 0 && Name_TextField.getLength() > 0 && Allowance_TextField.getLength() > 0 && BaseSalary_TextField.getLength() > 0) {
            double tootle = Double.parseDouble(Allowance_TextField.getText()) + Double.parseDouble(BaseSalary_TextField.getText()) * 12;
            double average = tootle / 12;
            Tootle_TextField.setText(String.valueOf(tootle));
            AverageSalary_TextField.setText(String.valueOf(average));
            Add_Salary_To_Table(tootle, average);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "��Ϣ����Ϊ��");
            alert.showAndWait();
        }
    }

    private void Add_Salary_To_Table(Double tootle, double average) {
        boolean user_exit = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl?", "root", "xsl203457");
            PreparedStatement preparedStatement = connection.prepareStatement("update xsl.teacher_salary set tootle_salary=? ,average_salary=? where id=?");
            PreparedStatement preparedStatement1 = connection.prepareStatement("select id, name  from xsl.teacher_salary ");
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == Integer.parseInt(ID_TextField.getText()) && resultSet.getString(2).matches(Name_TextField.getText())) {
                    user_exit = true;
                }
            }
            if (user_exit) {
                preparedStatement.setDouble(1, tootle);
                preparedStatement.setDouble(2, average);
                preparedStatement.setString(3, ID_TextField.getText());
                preparedStatement.executeUpdate();
                System.out.println("����ɹ�");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "�û�������");
                alert.showAndWait();
                clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void clear(){
        ID_TextField.clear();
        Name_TextField.clear();
        Allowance_TextField.clear();
        Tootle_TextField.clear();
        AverageSalary_TextField.clear();
        BaseSalary_TextField.clear();
    }
}
