module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ormlite.jdbc;
    requires ormlite.core;

    exports main;
    exports controllers;
    exports database.utils;
    exports database.models;
    exports database.dao;

    opens controllers;
    opens database.models;
}