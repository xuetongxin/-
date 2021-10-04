package Teacher_Salary;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.List;

public class Select extends Application {
    Select(){}

    public static void main(String[] args) {
        launch(args);
    }

    private final TableView<Teacher> table = new TableView<Teacher>();
    List<Teacher> number= List.of(
            new Teacher("1", "q", "q", "23")
    );

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void start(Stage stage) throws Exception{


        TableColumn Id_Column = new TableColumn("ID");
        // （很有用），指定该列可编辑（你双击后会显示文本框，修改后回车（Enter）保存）
        Id_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        Id_Column.setMinWidth(100);
        Id_Column.setCellValueFactory(new PropertyValueFactory<Object, Object>(number.get(0).getId()));

        TableColumn Name_Column = new TableColumn("NAME");
        Name_Column.setMinWidth(200);
        Name_Column.setCellValueFactory(new PropertyValueFactory<Object, Object>(number.get(0).getName()));

        TableColumn Position_Column = new TableColumn("Position");
        Position_Column.setMinWidth(200);
        Position_Column.setCellValueFactory(new PropertyValueFactory<Object, Object>(number.get(0).getPosition()));

        TableColumn Salary_Column = new TableColumn("Salary");
        Salary_Column.setMinWidth(200);
        Salary_Column.setCellValueFactory(new PropertyValueFactory<Object, Object>(number.get(0).getSalary()));

        ObservableList<Teacher> data = FXCollections.observableArrayList(number);

        // 将数据存入数据列表
        table.setItems(data);
        TableView<Teacher> table = new TableView<Teacher>((ObservableList<Teacher>) number);
        // 将我们创建的列添加进数据表格
        table.getColumns().addAll(Id_Column, Name_Column, Position_Column, Salary_Column);
        // 设置可编辑（列需要同时设置才有用）
        table.setEditable(true);
        // （很有用）宽度绑定窗口的宽度（意思窗口大小改变，它也跟着改变，自适应效果）
        table.prefWidthProperty().bind(stage.widthProperty());

        Scene scene = new Scene(table, 500, 500);
        stage.setScene(scene);
        stage.show();
    }



    public static class Teacher {
        SimpleStringProperty id;
         SimpleStringProperty name;
         SimpleStringProperty position;
         SimpleStringProperty salary;

        Teacher(){}
        Teacher(String ssPid, String sspName, String ssPosition, String sSalary) {
            setId(ssPid);
            setName(sspName);
            setPosition(ssPosition);
            setSalary(sSalary);

        }

        public String getId() {
            return id.get();
        }

        public void setId(String ssPid) {
            id.set(ssPid);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String sspName) {
            name.set(sspName);
        }

        public String getPosition() {
            return position.get();
        }

        public void setPosition(String ssPosition) {
            position.set(ssPosition);
        }

        public String getSalary() {
            return salary.get();
        }

        public void setSalary(String sSalary) {
            salary.set(sSalary);
        }
    }
}