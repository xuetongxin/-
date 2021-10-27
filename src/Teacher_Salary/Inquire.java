package Teacher_Salary;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Inquire extends Choice {
    public final TextField Text_Field = new TextField();
    //�������
    final TableView<Teacher> table = new TableView<>();
    final HBox box = new HBox(50);
    final Button Bt_Inquire = new Button("��ѯ");
    final Button Bt_Return = new Button("����");
    final Button Bt_Delete = new Button("ɾ��");
    final Button Bt_Print=new Button("��ӡ");
    ObservableList<Teacher> data = FXCollections.observableArrayList();

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void start(Stage stage) {

        //Text_Field.setPromptText("��������Ҫɾ�����û�ID");
        Text_Field.setPrefColumnCount(120);
        Text_Field.setPrefWidth(150);

        //���� ID NAME POSITION SALARY ��
        TableColumn Id_Column = new TableColumn("ID");
        TableColumn Name_Column = new TableColumn("NAME");
        TableColumn Sex_Column = new TableColumn("SEX");
        TableColumn Birth_Column = new TableColumn("BIRTH");
        TableColumn Age_Column = new TableColumn("AGE");
        TableColumn Marriage_Status_Column = new TableColumn("MARRIAGE_STATUS");
        TableColumn Address_Column = new TableColumn("ADDRESS");
        TableColumn Position_Column = new TableColumn("POSITION");
        TableColumn TootleSalary_Column = new TableColumn("TOOTLE_SALARY");
        TableColumn AverageSalary_Column = new TableColumn("AVERAGE_SALARY");

        //���ݰ�
        Id_Column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name_Column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Sex_Column.setCellValueFactory(new PropertyValueFactory<>("Sex"));
        Birth_Column.setCellValueFactory(new PropertyValueFactory<>("Birth"));
        Age_Column.setCellValueFactory(new PropertyValueFactory<>("Age"));
        Marriage_Status_Column.setCellValueFactory(new PropertyValueFactory<>("marriage_status"));
        Address_Column.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Position_Column.setCellValueFactory(new PropertyValueFactory<>("Position"));
        TootleSalary_Column.setCellValueFactory(new PropertyValueFactory<>("TootleSalary"));
        AverageSalary_Column.setCellValueFactory(new PropertyValueFactory<>("AverageSalary"));

        //�п�A
        Id_Column.setMinWidth(100);
        Name_Column.setMinWidth(100);
        Sex_Column.setMinWidth(50);
        Birth_Column.setMinWidth(100);
        Age_Column.setMinWidth(30);
        Marriage_Status_Column.setMinWidth(100);
        Address_Column.setMinWidth(200);
        Position_Column.setMinWidth(100);
        TootleSalary_Column.setMinWidth(200);
        AverageSalary_Column.setMinWidth(200);


        //�����봴����Columns
        table.getColumns().addAll(Id_Column, Name_Column, Sex_Column, Birth_Column, Age_Column, Marriage_Status_Column, Address_Column, Position_Column, TootleSalary_Column, AverageSalary_Column);

        box.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30, 0, 30, 0));
        box.getChildren().addAll(Text_Field, Bt_Inquire,Bt_Print,Bt_Delete ,Bt_Return);

        // ��Ȱ󶨴��ڵĿ�ȣ���˼���ڴ�С�ı䣬��Ҳ���Ÿı䣬����ӦЧ����
        //table.prefWidthProperty().bind(stage.widthProperty());
        //table.prefHeightProperty().bind(stage.heightProperty());

        Bt_Return.setOnAction(e -> new Choice().Choice_Inquire_Method(stage));
        Bt_Delete.setOnAction(e -> Delete_User(Text_Field, stage));
        Bt_Inquire.setOnAction(e -> Refresh_Table());
        Bt_Print.setOnAction(e->{
            try {
                new OperationData().Print_Data();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        borderpane.setCenter(table);
        borderpane.setTop(box);

        stage.setTitle("��ʦ��Ϣ��ѯ");
        stage.setScene(new Scene(borderpane, 550, 500));
        stage.setMinHeight(500);
        stage.setMinWidth(550);
        stage.show();
    }

    void Refresh_Table() {
        if (Text_Field.getLength() > 0) {
            int row = table.getItems().size();
            if (table.getItems().size() > 0) {
                for (int i = 0; i < row; i++) {
                    table.getItems().remove(0);
                    System.out.println(table.getItems().size());
                }
            }
            Position_Inquire();
        } else {
            if (table.getItems().size() > 0) {
                int row = table.getItems().size();
                for (int i = 0; i < row; i++) {
                    table.getItems().remove(0);
                    System.out.println(table.getItems().size());
                }
            }
            Mysql_Select();
        }
    }

    private void Position_Inquire() {
        try {
            System.out.println("�������ݿ�");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl?", "root", "xsl203457");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from xsl.teacher_salary where position=?");
            preparedStatement.setString(1, Text_Field.getText());
            ResultSet rs1 = preparedStatement.executeQuery();
            Insert(rs1);
            System.out.println("��ѯ�ɹ�");

        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }

    private void Mysql_Select() {
        //�����ʾ����
        ResultSet rs1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            Statement stmt = con.createStatement();
            rs1 = stmt.executeQuery("select * from teacher_salary");
            Insert(rs1);
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

    private void Insert(ResultSet rs1) throws SQLException {
        while (rs1.next()) {
            // �����ݴ��������б�
            data.add(new Teacher(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getInt(4), rs1.getInt(5), rs1.getString(6), rs1.getString(7), rs1.getString(8), rs1.getDouble(9), rs1.getDouble(10)));
            table.setItems(data);
        }
    }

    private void Delete_User(TextField Text_Field, Stage stage) {
        if (Text_Field.getLength()<=0){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Delete information is null");
            alert.showAndWait();
        }else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
                PreparedStatement preparedStatement = connection.prepareStatement("delete from xsl.teacher_salary where id=?");
                preparedStatement.setInt(1, Integer.parseInt(Text_Field.getText()));
                preparedStatement.execute();
                new Inquire().start(stage);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ɾ���ɹ�");
                alert.showAndWait();

                Text_Field.clear();
                System.out.println("ɾ���ɹ�");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Refresh_Table();

    }



    public static class Teacher {

        private final SimpleIntegerProperty Id;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Sex;
        private final SimpleIntegerProperty Birth;
        private final SimpleIntegerProperty Age;
        private final SimpleStringProperty Marriage_status;
        private final SimpleStringProperty Address;
        private final SimpleStringProperty Position;
        private final SimpleDoubleProperty TootleSalary;
        private final SimpleDoubleProperty AverageSalary;

        Teacher(int id, String name, String sex, int birth, int age, String marriage_status, String address, String position, double tootle_salary, double average_salary) {
            this.Id = new SimpleIntegerProperty(id);
            this.Name = new SimpleStringProperty(name);
            this.Sex = new SimpleStringProperty(sex);
            this.Birth = new SimpleIntegerProperty(birth);
            this.Age = new SimpleIntegerProperty(age);
            this.Marriage_status = new SimpleStringProperty(marriage_status);
            this.Address = new SimpleStringProperty(address);
            this.Position = new SimpleStringProperty(position);
            this.TootleSalary = new SimpleDoubleProperty(tootle_salary);
            this.AverageSalary = new SimpleDoubleProperty(average_salary);
        }

        public int getId() {
            return Id.get();
        }

        public void setId(int id) {
            Id.set(id);
        }

        public String getName() {
            return Name.get();
        }

        public void setName(String name) {
            Name.set(name);
        }

        public String getSex() {
            return Sex.get();
        }

        public void setSex(String sex) {
            Sex.set(sex);
        }

        public int getBirth() {
            return Birth.get();
        }

        public void setBirth(int birth) {
            Birth.set(birth);
        }

        public int getAge() {
            return Age.get();
        }

        public void setAge(int age) {
            Age.set(age);
        }

        public void setMarriage_statue(String marriage_statue) {
            Marriage_status.set(marriage_statue);
        }

        public String getMarriage_status() {
            return Marriage_status.get();
        }

        public String getAddress() {
            return Address.get();
        }

        public void setAddress(String address) {
            Address.set(address);
        }

        public String getPosition() {
            return Position.get();
        }

        public void setPosition(String position) {
            Position.set(position);
        }

        public double getTootleSalary() {
            return TootleSalary.get();
        }

        public void setTootleSalary(double tootle_salary) {
            TootleSalary.set(tootle_salary);
        }

        public double getAverageSalary() {
            return AverageSalary.get();
        }

        public void setAverageSalary(double average_salary) {
            AverageSalary.set(average_salary);
        }

    }
}