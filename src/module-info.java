module IJ.WorkSpace {
    requires javafx.controls;
    requires java.sql;
    requires javafx.graphics;
    requires  javafx.fxml;


    opens Teacher_Salary to javafx.fxml;
    exports Teacher_Salary;
}