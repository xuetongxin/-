package Teacher_Salary;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class Inquire extends Choice {
    Inquire() {
    }

    //创建表格
    TableView<Teacher> table = new TableView<>();
    //BorderPane borderPane = new BorderPane();
    HBox box = new HBox(80);
    Button Bt_Return = new Button("返回");
    Button Bt_Delete=new Button("删除");
    TextField Text_Field=new TextField();
    Stage window =new Stage();

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void start(Stage stage) {

        Text_Field.setPromptText("请输入你要删除的用户ID");
        Text_Field.setPrefColumnCount(120);
        Text_Field.setPrefWidth(150);

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

        //表格加入创建的Columns
        table.getColumns().addAll(Id_Column, Name_Column, Position_Column, Salary_Column);

        //从数据库导入数据到表格
        Mysql_Select();

        //box.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN,CornerRadii.EMPTY,Insets.EMPTY)));
        box.setBackground(new Background(new BackgroundImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\d.jpg"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        // （很有用）宽度绑定窗口的宽度（意思窗口大小改变，它也跟着改变，自适应效果）
        table.prefWidthProperty().bind(stage.widthProperty());
        table.prefHeightProperty().bind(stage.heightProperty());

        Bt_Return.setOnAction(e -> new Choice().start(stage));
        Bt_Delete.setOnAction(e-> Delete_User(Text_Field,stage));
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20,0,20,0));
        box.getChildren().addAll(Bt_Return,Text_Field,Bt_Delete);

        borderpane.setCenter(table);
        borderpane.setTop(box);
        stage.setX(500);
        stage.setY(200);
        stage.setTitle("查询");
        stage.setScene(new Scene(borderpane, 400, 400));
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
        ResultSet rs1 ;
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

    private void Delete_User(TextField Text_Field ,Stage stage){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from xsl.teacher_salary where id=?");
            preparedStatement.setInt(1, Integer.parseInt(Text_Field.getText()));
            preparedStatement.execute();
            new Inquire().start(stage);
            Delete_Successful();
            System.out.println("删除成功");
            Text_Field.clear();
        }catch  (Exception e) {
            e.printStackTrace();
        }


    }

    private void Delete_Successful(){
        Stage window1=new Stage();
        Pane pane=new Pane();
        Text text=new Text("删除成功");
        text.setStyle("-fx-font-family: '华文行楷';-fx-font:20;-fx-text-fill: 'red'");
        text.setX(120);
        text.setY(100);
        pane.getChildren().add(text);
        window1.setScene(new Scene(pane,300,200));
        window1.setX(550);
        window1.setY(300);
        window1.showAndWait();
    }

}