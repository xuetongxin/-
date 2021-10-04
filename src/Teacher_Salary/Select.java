package Teacher_Salary;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select extends Choice {
    Select() {
    }

    //创建表格
    TableView<Teacher> table = new TableView<>();
    BorderPane borderPane = new BorderPane();
    HBox box = new HBox(5);
    Button Bt_Return = new Button("Return");

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void start(Stage stage) {
        //创建 ID NAME POSITION SALARY 列
        TableColumn Id_Column = new TableColumn("ID");
        TableColumn Name_Column = new TableColumn("NAME");
        TableColumn Position_Column = new TableColumn("Position");
        TableColumn Salary_Column = new TableColumn("Salary");

        //数据绑定
        Id_Column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name_Column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Position_Column.setCellValueFactory(new PropertyValueFactory<>("Position"));
        Salary_Column.setCellValueFactory(new PropertyValueFactory<>("Salary"));

        //列宽
        Id_Column.setMinWidth(100);
        Name_Column.setMinWidth(100);
        Position_Column.setMinWidth(100);
        Salary_Column.setMinWidth(100);

        //表格加入创建的Colmun
        table.getColumns().addAll(Id_Column, Name_Column, Position_Column, Salary_Column);

        //从数据库导入数据到表格
        Mysql_Select();

        // 设置可编辑（列需要同时设置才有用）
        table.setEditable(true);

        // （很有用）宽度绑定窗口的宽度（意思窗口大小改变，它也跟着改变，自适应效果）
        table.prefWidthProperty().bind(stage.widthProperty());

        Bt_Return.setOnAction(e -> new Choice().start(stage));

        box.getChildren().add(Bt_Return);
        borderpane.setCenter(table);
        borderpane.setBottom(box);
        Scene scene = new Scene(borderpane, 400, 400);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.show();

    }

    public static class Teacher {

        private final SimpleIntegerProperty Id;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Position;
        private final SimpleDoubleProperty Salary;

        Teacher() {
            Id = null;
            Name = null;
            Position = null;
            Salary = null;
        }

        Teacher(int id, String name, String position, double salary) {
            this.Id = new SimpleIntegerProperty(id);
            this.Name = new SimpleStringProperty(name);
            this.Position = new SimpleStringProperty(position);
            this.Salary = new SimpleDoubleProperty(salary);
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

        public String getPosition() {
            return Position.get();
        }

        public void setPosition(String position) {
            Position.set(position);
        }

        public double getSalary() {
            return Salary.get();
        }

        public void setSalary(double salary) {
            Salary.set(salary);
        }

    }

    private void Mysql_Select() {
        //表格显示数据
        ObservableList<Teacher> data = FXCollections.observableArrayList();
        Teacher teacher = new Teacher();
        ResultSet rs1 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            Statement stmt = con.createStatement();
            rs1 = stmt.executeQuery("select * from teacher_salary");

            while (rs1.next()) {
                // 将数据存入数据列表
                data.add(new Teacher(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getDouble(4)));
                table.setItems(data);
            }

        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

}