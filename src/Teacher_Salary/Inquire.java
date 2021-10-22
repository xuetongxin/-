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

import java.sql.*;

public class Inquire extends Choice {
    public final TextField Text_Field = new TextField();
    //创建表格
    final TableView<Teacher> table = new TableView<>();

    final HBox box = new HBox(50);

    final Button Bt_Inquire = new Button("查询");
    final Button Bt_Return = new Button("返回");
    final Button Bt_Delete = new Button("删除");
    ObservableList<Teacher> data = FXCollections.observableArrayList();

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void start(Stage stage) {

        // Text_Field.setOnDragEntered(e->Delete_User(Text_Field,stage));
        Text_Field.setPromptText("请输入你要删除的用户ID");
        Text_Field.setPrefColumnCount(120);
        Text_Field.setPrefWidth(150);
        //Text_Field.setOnAction(e -> Delete_User(Text_Field, stage));

        //创建 ID NAME POSITION SALARY 列
        TableColumn Id_Column = new TableColumn("ID");
        TableColumn Name_Column = new TableColumn("NAME");
        TableColumn Sex_Column = new TableColumn("SEX");
        TableColumn Birth_Column = new TableColumn("BIRTH");
        TableColumn Age_Column = new TableColumn("AGE");
        TableColumn Marriage_Status_Column = new TableColumn("MARRAGE_STATUS");
        TableColumn Address_Column = new TableColumn("ADDRESS");
        TableColumn Position_Column = new TableColumn("POSITION");
        TableColumn TootleSalary_Column = new TableColumn("TOOTLE_SALARY");
        TableColumn AverageSalary_Column = new TableColumn("AVERAGE_SALARY");

        //数据绑定
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


        //列宽A
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


        //表格加入创建的Columns
        table.getColumns().addAll(Id_Column, Name_Column, Sex_Column, Birth_Column, Age_Column, Marriage_Status_Column, Address_Column, Position_Column, TootleSalary_Column, AverageSalary_Column);

        box.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30, 0, 30, 0));
        box.getChildren().addAll(Text_Field, Bt_Delete, Bt_Inquire, Bt_Return);

        // 宽度绑定窗口的宽度（意思窗口大小改变，它也跟着改变，自适应效果）
        table.prefWidthProperty().bind(stage.widthProperty());
        table.prefHeightProperty().bind(stage.heightProperty());

        Bt_Return.setOnAction(e -> new Choice().Choice_Inquire_Method(stage));
        Bt_Delete.setOnAction(e -> Delete_User(Text_Field, stage));
        Bt_Inquire.setOnAction(e -> Refresh_Table());

        borderpane.setCenter(table);
        borderpane.setTop(box);
        //stage.setX(500);
        //stage.setY(200);
        stage.setTitle("教师信息查询");
        stage.setScene(new Scene(borderpane, 500, 500));
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.show();
    }

    private void Position_Inquire() {
        try {
            System.out.println("连接数据库");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl?", "root", "xsl203457XSL@");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from xsl.teacher_salary where position=?");
            preparedStatement.setString(1, Text_Field.getText());
            ResultSet rs1 = preparedStatement.executeQuery();
            while (rs1.next()) {
                data.add(new Teacher(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getInt(4), rs1.getInt(5), rs1.getString(6), rs1.getString(7), rs1.getString(8), rs1.getDouble(9), rs1.getDouble(10)));
                table.setItems(data);

            }
            System.out.println("查询成功");

        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }

    private void Mysql_Select() {
        //表格显示数据
        ResultSet rs1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457XSL@");
            Statement stmt = con.createStatement();
            rs1 = stmt.executeQuery("select * from teacher_salary");
            while (rs1.next()) {
                // 将数据存入数据列表
                data.add(new Teacher(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getInt(4), rs1.getInt(5), rs1.getString(6), rs1.getString(7), rs1.getString(8), rs1.getDouble(9), rs1.getDouble(10)));
                table.setItems(data);
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

    private void Delete_User(TextField Text_Field, Stage stage) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457XSL@");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from xsl.teacher_salary where id=?");
            preparedStatement.setInt(1, Integer.parseInt(Text_Field.getText()));
            preparedStatement.execute();
            new Inquire().start(stage);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "删除成功");
            alert.showAndWait();

            System.out.println("删除成功");
            Text_Field.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Refresh_Table() {
        if (Text_Field.getLength() > 0) {
            int row = table.getItems().size();
            if (table.getItems().size() > 0) {
                for (int i = 0; i < row; i++) {
                    table.getItems().remove(0);
                    System.out.println(table.getItems().size());
                }
                Position_Inquire();
            } else
                Position_Inquire();
        } else {
            if (table.getItems().size() > 0) {
                int row = table.getItems().size();
                for (int i = 0; i < row; i++) {
                    table.getItems().remove(0);
                    System.out.println(table.getItems().size());
                }
                Mysql_Select();
            } else
                Mysql_Select();
        }
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