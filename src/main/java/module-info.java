module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports main;
    exports controllers;
    exports database.utils;
}