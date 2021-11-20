module com.org.inventoryapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens com.org.inventoryapplication to javafx.fxml;
    exports com.org.inventoryapplication;
}