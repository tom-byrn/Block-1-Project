package com.project.block1project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;

    }

    // Creating a change scene method
    @FXML
    private void changeScene(String fxmlFile) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(fxmlFile)); // Load the FXML file given
            Scene scene = new Scene(fxmlloader.load(), 1728, 972);

            // Switch statement to handle different controllers
            switch (fxmlFile) {
                case "operatingsystem.fxml": {
                    OperatingSystem osController = new OperatingSystem();
                    osController.initialize(); // Call the initialization method for Operating System
                    break;
                }
                case "home.fxml": {
                    Home homeController = new Home();
                    homeController.initialize();// Call the initialization method for Home
                    System.out.println("Home page chosen");
                    break;
                }
                case "cpu.fxml": {
                    CPU cpuController = new CPU();
                    cpuController.initialize(); // Call the initialization method for CPU
                    break;
                }
                case "memory.fxml": {
                    Memory memoryController = new Memory();
                    memoryController.initialize(); // Call the initialization method for Memory
                    break;
                }
                case "peripherals.fxml": {
                    Peripherals peripheralsController = new Peripherals();
                    peripheralsController.initialize(); // Call the initialization method for Peripherals
                    break;
                }
                default: {
                    // Optional: Handle unknown fxmlFile cases if necessary
                    break;
                }
            }

            // New Controller object, allows stages to be switched multiple times
            HelloController newController = fxmlloader.getController();
            if (newController != null) {
                newController.setStage(stage);
            }

            stage.setTitle("System Information");
            stage.setScene(scene);
            stage.setMinWidth(1728);
            stage.setMinHeight(972);
            stage.setMaxWidth(1728);
            stage.setMaxHeight(972);

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onHomeButtonClick() {
        changeScene("home.fxml");
    }

    @FXML
    protected void onCPUButtonClick() {
        changeScene("cpu.fxml");
    }

    @FXML
    protected void onMemoryButtonClick() {
        changeScene("memory.fxml");
    }

    @FXML
    protected void onOperatingSystemButtonClick() {
        changeScene("operatingsystem.fxml");
    }

    @FXML
    protected void onPeripheralsButtonClick() {
        changeScene("peripherals.fxml");
    }

    // Logic for each individual page
}