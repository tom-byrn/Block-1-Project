module com.project.block1project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.project.block1project to javafx.fxml;
    exports com.project.block1project;
    exports com.project.cs4421.project;
}