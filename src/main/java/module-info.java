module com.tusur.firsrfxapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tusur.firsrfxapp to javafx.fxml;
    exports com.tusur.firsrfxapp;
}