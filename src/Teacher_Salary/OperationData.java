package Teacher_Salary;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class OperationData {
    private String url = "jdbc:mysql://localhost:3306/xsl";
    private String sql;
    private final String Account;
    private final String Password;
    private final String driver;
    private Statement st;
    private PreparedStatement preparedStatement;
    private Connection con;
    private ResultSet rst;

    public OperationData()  //构造数据库链接默认值
    {
        Account = "root";  //SQL Server登陆账号
        Password = "xsl203457XSL@";    //SQL Server登陆密码
        driver = "com.mysql.cj.jdbc.Driver"; //驱动加载
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Error_INformaton();
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, Account, Password);
        } catch (SQLException e) {
            Error_INformaton();
            e.printStackTrace();
        }

    }

    public OperationData(Connection con)  //构造数据库链接默认值
    {
        this.con = con;
        Account = "root";  //SQL Server登陆账号
        Password = "xsl203457XSL@";    //SQL Server登陆密码
        driver = "com.mysql.cj.jdbc.Driver"; //驱动加载
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Error_INformaton();
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, Account, Password);
        } catch (SQLException e) {
            Error_INformaton();
            e.printStackTrace();
        }
    }

    public OperationData(String account, String password)  //构造指定帐号密码数据库链接
    {
        Account = account;
        Password = password;
        driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            Error_INformaton();
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, Account, Password);
        } catch (SQLException e) {
            Error_INformaton();
            e.printStackTrace();
        }

    }

    boolean Select_User_Account(TextField Account) throws SQLException {
        String sql = "select account from xsl.passwd_date";
        boolean user_Account_exit = false;
        preparedStatement = con.prepareStatement(sql);
        rst = preparedStatement.executeQuery();

        while (rst.next()) {
            if (rst.getString(1).matches(Account.getText()))
                user_Account_exit = true;
        }
        if (!user_Account_exit) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "user dose not exist");
            alert.showAndWait();
        }

        rst.close();
        preparedStatement.close();
        return user_Account_exit;

    }

    boolean Select_User_Password(TextField Account, TextField Password) throws SQLException {
        boolean user_passwd_true = false;
        preparedStatement = con.prepareStatement("select passwd from xsl.passwd_date where account=?");
        preparedStatement.setString(1, Account.getText());
        rst = preparedStatement.executeQuery();
        rst.next();
        if (rst.getString(1).matches(Password.getText()))
            user_passwd_true = true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "passwd is false");
            alert.showAndWait();
        }
        return user_passwd_true;
    }

    public boolean ID_Exist(int id) throws SQLException {
        boolean Id_exist = false;
        preparedStatement = con.prepareStatement("select id from xsl.teacher_salary");
        rst = preparedStatement.executeQuery();
        while (rst.next()) {
            if (id == rst.getInt(1))
                Id_exist = true;
        }
        return Id_exist;
    }

    public void Error_INformaton() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Connection Error");
        alert.showAndWait();
    }

}



