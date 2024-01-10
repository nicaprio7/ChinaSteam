package com.xin.controller;

import com.xin.bean.Game;
import com.xin.bean.User;
import com.xin.util.DBUtil;
import com.xin.util.StringUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

/**
 * @author wudixin
 * @date 2024/1/8 13:24
 */
public class MainController {
    @FXML
    private TableView<Game> tableView;
    @FXML
    private TableColumn<Game, Integer> id;
    @FXML
    private TableColumn<Game, String> name;
    @FXML
    private TableColumn<Game, Integer> price;
    @FXML
    private TableColumn<Game, Integer> amount;
    @FXML
    private TableColumn<Game, String> describe;


    @FXML
    private TableView<User> tableView1;
    @FXML
    private TableColumn<User, Integer> userid;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> userpass;

    @FXML
    private TextField name1;
    @FXML
    private TextField price1;
    @FXML
    private TextField amount1;
    @FXML
    private TextField describe1;

    @FXML
    private TextField id1;

    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        name.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        price.setCellValueFactory(new PropertyValueFactory<>("gamePrice"));
        amount.setCellValueFactory(new PropertyValueFactory<>("gameAmount"));
        describe.setCellValueFactory(new PropertyValueFactory<>("gameDescribe"));

        userid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        username.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userpass.setCellValueFactory(new PropertyValueFactory<>("userPassword"));

        try {
            Connection conn = DBUtil.getConn();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from game");

            ObservableList<Game> data = FXCollections.observableArrayList();
            while (rs.next()) {
                int id = rs.getInt("gameId");
                String name = rs.getString("gameName");
                int price = rs.getInt("gamePrice");
                int amount = rs.getInt("gameAmount");
                String describe = rs.getString("gameDescribe");
                data.add(new Game(id, name, price, amount, describe));
            }
            tableView.setItems(data);
            DBUtil.close(conn, statement, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection conn = DBUtil.getConn();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from user");

            ObservableList<User> data = FXCollections.observableArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String password = rs.getString("password");
                data.add(new User(id, name, password));
            }
            tableView1.setItems(data);
            DBUtil.close(conn, statement, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void gameAdd() {
        String name = name1.getText();
        Integer price = Integer.valueOf(price1.getText());
        Integer amount = Integer.valueOf(amount1.getText());
        String describe = describe1.getText();

        try {
            Connection conn = DBUtil.getConn();
            String sql = "insert into game(gameName, gamePrice, gameAmount, gameDescribe) values (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, price);
            statement.setInt(3, amount);
            statement.setString(4, describe);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // 插入成功
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("添加游戏");
                alert.setHeaderText(null);
                alert.setContentText("新游戏已成功添加到数据库！");
                alert.showAndWait();
                name1.setText("");
                price1.setText("");
                amount1.setText("");
                describe1.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void gameDel() {
        Integer id = Integer.valueOf(id1.getText());

        try {
            Connection conn = DBUtil.getConn();
            String sql = "delete from game where gameId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                // 删除成功
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("删除游戏");
                alert.setHeaderText(null);
                alert.setContentText("游戏已成功从数据库中删除！");
                alert.showAndWait();
                id1.setText("");
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // 删除失败
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("删除游戏");
            alert.setHeaderText(null);
            alert.setContentText("删除游戏时发生错误：" + e.getMessage());
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void refresh() {
        // 刷新数据库中的内容到窗口
        initialize();
    }



}
