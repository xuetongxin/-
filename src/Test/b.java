package Test;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class b extends Application {
    String url = "jdbc:mysql://127.0.0.1:3306/xsl?";
    String user = "root";
    String pwd = "xsl203457";
    String jdbc = "com.mysql.cj.jdbc.Driver";
    ResultSet rst = null;
    Connection cont = null;
    Statement ppst = null;

    public void start(Stage procedrue) throws Exception{
        TableView<Student> table = new TableView<>();
        //定义表格的行标
        TableColumn Id = new TableColumn("id");
        TableColumn name = new TableColumn("name");
        TableColumn position = new TableColumn("phone");
        TableColumn salary= new TableColumn("Address");
        //表格列宽宽度设置
        Id.setMinWidth(100);
        name.setMinWidth(100);
        position.setMinWidth(100);
        salary.setMinWidth(100);
        //确定数据导入的列
        Id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        position.setCellValueFactory(
                new PropertyValueFactory<>("position"));
        salary.setCellValueFactory(
                new PropertyValueFactory<>("salary"));
        //向表中导入数据
        //table.setItems(data);
        date( table, Id, name, position,  salary);
        table.getColumns().addAll(Id, name, position, salary);
        Scene scene = new Scene(table,400, 200);
        procedrue.setTitle("Table View Sample");
        procedrue.setScene(scene);
        procedrue.show();
    }

    public void date(TableView table,  TableColumn Id,
                     TableColumn name, TableColumn phone, TableColumn address) {
        try {
            Class.forName(jdbc);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql4 = "select  * from teacher_salary";
        ObservableList<Student> data = FXCollections.observableArrayList();
        try {
            cont = DriverManager.getConnection(url, user, pwd);
            ppst = cont.createStatement();
            rst = ppst.executeQuery(sql4);
            System.out.print("连接成功");
            while(rst.next()) {
                data.add(new
                        Student(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));

                table.setItems(data);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(cont != null && ppst != null && rst != null) {
                try {
                    cont.close();
                    ppst.close();
                    rst.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public class Student {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty position;
        private final SimpleDoubleProperty salary;

        Student(){
            id=null;
            name=null;
            position=null;
            salary=null;
        }
        Student(int id, String name, String position, double salary){
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.position = new SimpleStringProperty(position);
            this.salary = new SimpleDoubleProperty(salary);
        }
        public int getId() {
            return id.get();
        }
        public void setId(int id) {
            this.id.set(id);
        }
        public String getName() {
            return this.name.get();
        }
        public void setName(String name) {
            this.name.set(name);
        }
        public String getPhone() {
            return this.position.get();
        }
        public void setPhone(String phone) {
            this.position.set(phone);
        }
        public double getAddress() {
            return this.salary.get();
        }
        public void setAddress(double salary) {
            this.salary.set(salary);
        }
    }


}
