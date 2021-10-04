package Test;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class a extends Application {
    public static void main(String[] args){
        launch(args);
    }

    ObservableList<Teacher> date= FXCollections.observableArrayList(
            new Teacher("1", "xsl")
    );
    public void start(Stage stage)throws Exception{
        //创建一个表格
        TableView <Teacher>tableView=new TableView<>();
        //创建表格列
        TableColumn<Teacher,StringProperty> Id_Col=new TableColumn<>("Id");
        TableColumn<Teacher,StringProperty> Name_Col=new TableColumn<>("Name");

        Id_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name_Col.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.setItems(date);
        tableView.getColumns().addAll(Id_Col, Name_Col);
        stage.setScene(new Scene(tableView,300,300));
        stage.show();
    }

    public static class Teacher {
        private final StringProperty id;
        private final StringProperty name;
        Teacher(String id,String name){
            this.id=new SimpleStringProperty(id);
            this.name=new SimpleStringProperty(name);
        }
        void setId(String id1){
            id.set(id1);
        }
        String getId(){
            return id.get();
        }
        void setName(String name1){
            id.set(name1);
        }
        String getName(){
            return name.get();
        }

    }
}
