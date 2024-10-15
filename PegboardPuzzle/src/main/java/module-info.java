module com.rsteiner.pegboardpuzzle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.rsteiner.pegboardpuzzle to javafx.fxml;
    exports com.rsteiner.pegboardpuzzle;
}