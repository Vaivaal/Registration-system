module com.example.lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    //requires debugger.app;
    requires org.apache.pdfbox;
    requires itextpdf;
    requires opencsv;


    opens Main to javafx.fxml;
    exports Main;
}