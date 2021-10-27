package Teacher_Salary;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class OperationData {
    private final String Account;
    private final String Password;
    private final String driver;
    private final String url = "jdbc:mysql://localhost:3306/xsl";
    private String sql;
    final ImageView imageView = new ImageView();
    private PreparedStatement preparedStatement;
    private PreparedStatement ps;
    private Connection con;
    private ResultSet rst;
    java.util.Date date = new Date();
    File file;
    FileWriter print;

    public OperationData()  //构造数据库链接默认值
    {
        Account = "root";  //SQL Server登陆账号
        Password = "xsl203457";    //SQL Server登陆密码
        driver = "com.mysql.cj.jdbc.Driver"; //驱动加载
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Error_Information();
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, Account, Password);
        } catch (SQLException e) {
            Error_Information();
            e.printStackTrace();
        }
        imageView.setImage(new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\bg.jpg"));
        imageView.setFitHeight(1080);
        imageView.setFitWidth(1980); // 背景图片属性
    }

    public void Error_Information() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Connection Error");
        alert.showAndWait();
    }

    protected void Register_user(TextField account_textfield, TextField passwd_textfield, TextField passwd2_textfield) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into xsl.passwd_date (account,passwd)values (?,?)");
            System.out.println("egister");
            ps.setString(1, account_textfield.getText());
            ps.setString(2, passwd_textfield.getText());
            System.out.println("register");
            ps.execute();
            ps.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "注册成功");
            alert.showAndWait();
            System.out.println("注册成功");
            account_textfield.clear();
            passwd_textfield.clear();
            passwd2_textfield.clear();
        } catch (Exception ex) {
            ex.getStackTrace();
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
            Account_ErrorLog(Account.getText());
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
            Passwd_ErrorLog(Account.getText());

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

    void Input_Teacher_data(TextField id, TextField name, TextField birth, TextField age, TextField marrigr, TextField address, TextField position, TextField sex) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position) values (?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(id.getText()));
            ps.setString(2, name.getText());
            ps.setString(3, sex.getText());
            ps.setInt(4, Integer.parseInt(birth.getText()));
            ps.setInt(5, Integer.parseInt(age.getText()));
            ps.setString(6, marrigr.getText());
            ps.setString(7, address.getText());
            ps.setString(8, position.getText());
            int i = ps.executeUpdate();
            System.out.println(i);
            ps.close();
            new Input().Clear_TextField();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Input Successfully");
            alert.showAndWait();
            System.out.println("Input Successfully");
            Input_Log(id.getText());

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    void All_Update(int id, String name, String sex, int age, int birth, String marriage_status, String position, String address) {

        try {
            ps = con.prepareStatement("update xsl.teacher_salary set name=?,position=?,sex=?,birth=?,marriage_status=?,address=?,age=? where id=?");
            ps.setString(1, name);
            ps.setString(2, position);
            ps.setString(3, sex);
            ps.setInt(4, birth);
            ps.setString(5, marriage_status);
            ps.setString(6, address);
            ps.setInt(7, age);
            ps.setInt(8, id);
            ps.executeUpdate();
            new Input().Clear_TextField();
            new Update().Clear_Box();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update Successfully");
            alert.showAndWait();
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

    void Age_Update(int id, int age) {
        try {
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set age=? where id=?");
            ps.setInt(2, id);
            ps.setInt(1, age);
            ps.executeUpdate();
            System.out.println("连接成功");
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        new Input().Clear_TextField();
        new Update().Clear_Box();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Age Update Successfully");
        alert.showAndWait();
    }


    void Address_Update(int id, String address) {
        try {
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set address=? where id=?");
            ps.setInt(2, id);
            ps.setString(1, address);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        int i = new Input().Clear_TextField();
        System.out.println(i);
        new Update().Clear_Box();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Address Update Successfully");
        alert.showAndWait();
    }

    void Name_Update(int id, String name) {
        try {
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set name=? where id=?");
            ps.setDouble(2, id);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        new Input().Clear_TextField();
        new Update().Clear_Box();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Name Update Successfully");
        alert.showAndWait();
    }

    void Position_Update(int id, String position) {
        try {
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set position=? where id=?");
            ps.setDouble(2, id);
            ps.setString(1, position);
            ps.executeUpdate();
            System.out.println("连接成功");

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        //格式化文本域
        new Input().Clear_TextField();
        new Update().Clear_Box();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Position Update Successfully");
        alert.showAndWait();
        System.out.println("职位修改成功");
    }


    void Sex_Update(int id, String sex) {
        try {
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set sex=? where id=?");
            ps.setDouble(2, id);
            ps.setString(1, sex);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        new Input().Clear_TextField();
        new Update().Clear_Box();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sex Update Successfully");
        alert.showAndWait();
    }

    void Birth_Update(int id, int birth) {
        try {

            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set birth=? where id=?");
            ps.setDouble(2, id);
            ps.setInt(1, birth);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        new Input().Clear_TextField();
        new Update().Clear_Box();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Born Update Successfully");
        alert.showAndWait();
    }

    void Marriage_Update(int id, String marriage) {
        try {
            PreparedStatement ps = con.prepareStatement("update xsl.teacher_salary set marriage_status=? where id=?");
            ps.setDouble(2, id);
            ps.setString(1, marriage);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        new Input().Clear_TextField();
        new Update().Clear_Box();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Marriage Status Update Successfully");
        alert.showAndWait();
    }

    void File_Connect() {
        file = new File("D:\\IJ_WorkSpace\\src\\META-INF\\Login_Log");
        try {
            print = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Login_Log(String id) {
        File_Connect();
        try {
            print.write("\n" + id + "\tLogin_Log\t\t" + date.toString() + "\n");
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void Passwd_ErrorLog(String id) {
        File_Connect();
        try {
            print.write("\n" + id + "\tpasswd error\t\t" + date.toString());
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Account_ErrorLog(String id) {
        File_Connect();
        try {
            print.write("\n" + id + "\tAccount not exist\t\t" + date.toString());
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Exit_Log(String id) {
        File_Connect();
        try {
            print.write("\n\n" + id + "\t\t\texist system\t\t" + date.toString() + "\n");
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Input_Log(String id) {
        File_Connect();
        try {
            print.write("\n" + "\t\t\tInsert Teacher\t" + id + "\t" + date.toString());
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Update_Log(int choice, String id) {
        File_Connect();
        switch (choice) {
            case 0: {
                try {
                    print.write("\n\t\t\t" + id + "\tAll Update\t\t" + date.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case 1: {
                try {
                    print.write("\n\t\t\t" + id + "\tName Update\t\t" + date.toString());
                    print.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2: {
                try {
                    print.write("\n\t\t\t" + id + "\tPosition Update\t\t" + date.toString());
                    print.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 3: {
                try {
                    print.write("\n\t\t\t" + id + "\tSex Update\t\t" + date.toString());
                    print.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4: {
                try {
                    print.write("\n\t\t\t" + id + "\tBirth Update\t\t" + date.toString());
                    print.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 5: {
                try {
                    print.write("\n\t\t\t" + id + "\tAge Update\t\t" + date.toString());
                    print.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 6: {
                try {
                    print.write("\n\t\t\t" + id + "\tMarriage Status Update\t\t" + date.toString());
                    print.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 7: {
                try {
                    print.write("\n\t\t\t" + id + "\tAddress Update\t\t" + date.toString());
                    print.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 8: {
                break;
            }
        }

    }

    void Print_Data() throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you want to print all?");
        alert.showAndWait();
        sql="select * from teacher_salary";
        FileWriter write = null;
        try {
            File file=new File("D:\\IJ_WorkSpace\\src\\META-INF\\Salary");
            write =new FileWriter(file ,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (alert.getResult().getButtonData().isDefaultButton()){
            write.write("\n"+"id\t"+"name\t"+"sex\t"+"age\t"+"birth\t"+"marriage\t"+"position\t"+"address\t"+"tootle\t"+"average\n");
            try {
                PreparedStatement  preparedStatement=con.prepareStatement(sql);
                rst=preparedStatement.executeQuery();
               while(rst.next()){
                   String id= String.valueOf(rst.getInt(1));
                   String name=rst.getString(2);
                   String sex=rst.getString(3);
                   String age= String.valueOf(rst.getInt(4));
                   String birth = String.valueOf(rst.getInt(5));
                   String marriage=rst.getString(6);
                   String position=rst.getString(7);
                   String address=rst.getString(8);
                   String tootle= String.valueOf(rst.getDouble(9));
                   String average= String.valueOf(rst.getDouble(10));
                   write.write(id+"\t"+name+"\t"+sex+"\t"+age+"\t"+birth+"\t"+marriage+"\t"+position+"\t"+address+"\t"+tootle+"\t"+average+"\n");
               }
               write.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            write.write("\n"+"id\t"+"name\t"+"tootle\t"+"average\n");
            try {
                PreparedStatement  preparedStatement=con.prepareStatement(sql);
                rst=preparedStatement.executeQuery();
                while(rst.next()){
                    String id= String.valueOf(rst.getInt(1));
                    String name=rst.getString(2);
                    String tootle= String.valueOf(rst.getDouble(9));
                    String average= String.valueOf(rst.getDouble(10));
                    write.write(id+"\t"+name+"\t"+tootle+"\t"+average+"\n");
                }
                write.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}



