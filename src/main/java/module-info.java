module com.xin.chinasteam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.xin to javafx.fxml;
    exports com.xin;
    exports com.xin.controller;
    opens com.xin.controller to javafx.fxml;
    opens com.xin.bean to javafx.base;
}