package com.xin.controller;

import com.xin.LoginApplication;
import com.xin.MainApplication;
import com.xin.util.DBUtil;
import com.xin.util.StringUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorinfo;

    @FXML
    public void doLogin() {
        String usernameText = username.getText();
        String passwordText = password.getText();
        if (StringUtil.isEmpty(usernameText) || StringUtil.isEmpty(passwordText)) {
            errorinfo.setText("用户名或者密码不能为空");
            errorinfo.setVisible(true);
            return;
        }

        try {
            Connection conn = DBUtil.getConn();
            Statement stmt = conn.createStatement();
            String sql = "select username, password from user where username='" + usernameText + "' and password='" + passwordText + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                // 这里登录成功之后，进行窗口的跳转
                errorinfo.setText("登录成功!");
                errorinfo.setVisible(true);

                // 加载主窗口的 FXML 文件
                FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("mainview.fxml"));
                // 创建主窗口场景
                Scene mainScene = new Scene(loader.load(), 800, 600);

                // 设置主窗口场景并显示主窗口
                Stage primaryStage = new Stage();
                primaryStage.setTitle("欢迎来到ChinaSteam！");
                primaryStage.setScene(mainScene);
                primaryStage.show();

            } else {
                errorinfo.setText("用户名或者密码错误!");
                errorinfo.setVisible(true);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}