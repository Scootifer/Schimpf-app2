module com.org.inventoryapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.org.inventoryapplication to javafx.fxml;
    exports com.org.inventoryapplication;
}