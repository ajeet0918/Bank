package com.example.boibank.de.jensd.fx.glyphs.fontawesome;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FontAwesomeIconView extends Text {


    public FontAwesomeIconView(FontAwesomeIcon user) {
        super();
        setFont(Font.loadFont(getClass().getResourceAsStream("/FontAwesome.ttf"), 16));
    }

    public FontAwesomeIconView(String text) {
        this(FontAwesomeIcon.USER);
        setText(text);
    }
}

