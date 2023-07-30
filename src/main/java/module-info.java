module com.example.mergesortvisual {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quicksortvisual to javafx.fxml;
    exports com.example.quicksortvisual;
}