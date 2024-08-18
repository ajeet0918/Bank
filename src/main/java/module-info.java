module com.example.boibank {
    requires javafx.fxml;
   requires javafx.controls;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;
    requires  java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.boibank to javafx.fxml;
    exports com.example.boibank;
    exports com.example.boibank.Contoroler;
    exports com.example.boibank.Contoroler.Admin;
    exports com.example.boibank.Contoroler.clients;
    exports com.example.boibank.models;
    exports com.example.boibank.view;
}