package com.project.block1project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;

import java.io.IOException;

public class HelloController {
    private Stage stage;

    // FXML components from the home.fxml file
    @FXML
    private Label labelOS;
    @FXML
    private Label labelOSBit;
    @FXML
    private Label labelOSVersion;
    @FXML
    private Label labelOSArchitecture;
    @FXML
    private Label labelCountry;
    @FXML
    private Label labelLanguageAbbreviation;
    @FXML
    private Label labelUser;
    @FXML
    private ProgressBar batteryBar;
    @FXML
    private Label labelBattery;
    @FXML
    private Label labelBatteryTime;


    // FXML components from the cpu.fxml file
    @FXML
    private Label labelCpuName;
    @FXML
    private Label labelCpuPhysicalCores;
    @FXML
    private Label labelCpuLogicalCores;
    @FXML
    private Label labelCpuMaxFrequency;
    @FXML
    private Label labelRam;
    @FXML
    private Label labelTotalMemory;
    @FXML
    private Label labelAvailableMemory;
    @FXML
    private Label labelMemoryUsed;

    // Public static series for CPU usage chart
    public static XYChart.Series<Number, Number> series = new XYChart.Series<>();

    public void setStage(Stage stage) {
        this.stage = stage;

    }

    // Creating a change scene method
    @FXML
    private void changeScene(String fxmlFile) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(fxmlFile)); // Load the FXML file given
            Scene scene = new Scene(fxmlloader.load(), 1728, 972);

            // Switch case for running fxml files
            switch (fxmlFile) {
                case "home.fxml":
                    HelloController homeController = fxmlloader.getController();
                    homeController.initializeHomePage();
                    break;

                case "cpu.fxml":
                    HelloController cpuController = fxmlloader.getController();
                    cpuController.initializeCPUPage();
                    break;

                case "memory.fxml":
                    HelloController memoryController = fxmlloader.getController();
                    memoryController.initializeMemoryPage();
                    break;

                default:
                    // Gives an error if there's no matching fxml file
                    System.out.println("No matching FXML file found.");
                    break;
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

    // Initialize the Home page (moved functionality from Home.java)
    @FXML
    public void initializeHomePage() {
        System.out.println("Home page initialize() is running");

        // Get system properties
        String OS = System.getProperty("os.name");
        String OS_bit = System.getProperty("sun.arch.data.model");
        String OS_Version = System.getProperty("os.version");
        String OS_Architecture = System.getProperty("os.arch");
        String country = System.getProperty("user.country");
        String LanguageAbbreviation = System.getProperty("user.language");
        String user = System.getProperty("user.name");

        // Print statements to test if this is being run
        System.out.println("OS: " + OS);
        System.out.println("OS Bit: " + OS_bit);

        // Set the labels with values, ensuring they are not null
        if (labelOS != null) labelOS.setText("Operating System: " + OS);
        if (labelOSBit != null) labelOSBit.setText("OS Bit: " + OS_bit);
        if (labelOSVersion != null) labelOSVersion.setText("OS Version: " + OS_Version);
        if (labelOSArchitecture != null) labelOSArchitecture.setText("OS Architecture: " + OS_Architecture);
        if (labelCountry != null) labelCountry.setText("Keyboard Layout: " + country);
        if (labelLanguageAbbreviation != null) labelLanguageAbbreviation.setText("Language: " + LanguageAbbreviation);
        if (labelUser != null) labelUser.setText("User: " + user);

        // Battery Info
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        PowerSource[] powerSources = hal.getPowerSources().toArray(new PowerSource[0]);
        if (powerSources.length > 0) {
            PowerSource battery = powerSources[0];

            double batteryRemaining = battery.getRemainingCapacityPercent() * 100;  // Battery remaining percentage
            double batteryTimeRemaining = battery.getTimeRemainingInstant();  // Time remaining in seconds

            batteryBar.setProgress(batteryRemaining / 100.0);

            // Convert time remaining to minutes and hours
            long hours = (long) (batteryTimeRemaining / 3600);
            long minutes = (long) ((batteryTimeRemaining % 3600) / 60);

            // Display battery information
            if (labelBattery != null) {
                labelBatteryTime.setText(String.format("Time left: %d hours %d minutes", hours, minutes));
                labelBattery.setText(String.format("Battery: %.1f%%", batteryRemaining));
            }
            if (hours == 0 && minutes == 0) {
                labelBatteryTime.setText("Time Left: Unknown");
            }
        } else {
            System.out.println("No power sources found");
        }
    }

    // Initialize the CPU page
    public void initializeCPUPage() {
        System.out.println("CPU page initialize() is running");

        // Get CPU information
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();

        // Set the labels with values, ensuring they are not null
        if (labelCpuName != null) labelCpuName.setText("CPU: " + cpu.getProcessorIdentifier());
        if (labelCpuPhysicalCores != null)
            labelCpuPhysicalCores.setText("Physical Cores: " + cpu.getPhysicalProcessorCount());
        if (labelCpuLogicalCores != null)
            labelCpuLogicalCores.setText("Logical Cores: " + cpu.getLogicalProcessorCount());
        if (labelCpuMaxFrequency != null) labelCpuMaxFrequency.setText("Max Frequency: " + cpu.getMaxFreq() + " Hz");

        // Initialize the series for the CPU usage chart
        series.setName("CPU Usage");
    }

    public void initializeMemoryPage() {
        System.out.println("Memory page initialize() is running");

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory memory = hal.getMemory();

        long totalMemory = memory.getTotal();
        long availableMemory = memory.getAvailable();

        if (labelTotalMemory != null) {
            labelTotalMemory.setText("Total Memory: " + totalMemory / (1024 * 1024) + " MB");
        }
        if (labelAvailableMemory != null) {
            labelAvailableMemory.setText("Available Memory: " + availableMemory / (1024 * 1024) + " MB");
        }
        if (labelMemoryUsed != null) {
            labelMemoryUsed.setText("Memory Used: " + (totalMemory - availableMemory) / (1024 * 1024) + " MB");
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
}