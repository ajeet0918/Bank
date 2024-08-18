package com.example.boibank;

import com.example.boibank.models.Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Show the splash screen
        showSplashScreen(primaryStage);
    }
    private void showSplashScreen(Stage primaryStage) {
        Image splashImage = new Image(getClass().getResourceAsStream("/Images/Splash.jpg"));
        double desiredWidth = splashImage.getWidth() / 4;
        double desiredHeight = splashImage.getHeight() / 4;
        ImageView splashImageView = new ImageView(splashImage);
        splashImageView.setFitWidth(desiredWidth);
        splashImageView.setFitHeight(desiredHeight);
        StackPane splashPane = new StackPane(splashImageView);
        Scene splashScene = new Scene(splashPane);
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Splash Screen");
        primaryStage.show();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    primaryStage.close();
                    Model.getInstance().getViewfactory().showLoginWindow();
                })
        );
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
