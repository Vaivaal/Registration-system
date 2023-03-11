package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AttendTableController extends Controller{

    @FXML
    private TableColumn<Student, String> nameColumn1;

    @FXML
    private TableColumn<Student, String> surnameColumn1;

    @FXML
    private TableColumn<Student, Integer> groupColumn1;

    @FXML
    private TableColumn<Student, Integer> idColumn1;

    @FXML
    private TableColumn<Student, String> attendedColumn1;

    @FXML
    private TableColumn<Student, String> didntAttendColumn1;

    @FXML
    private TableView attendanceTable1;

    @FXML
    private Button homeButton;

    @FXML
    DatePicker filterDatePicker;

    ObservableList<Student> filteredList = FXCollections.observableArrayList();

    public void initialize(){
        nameColumn1.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surnameColumn1.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        groupColumn1.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        idColumn1.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        didntAttendColumn1.setCellValueFactory(new PropertyValueFactory<Student, String>("didntAttend"));
        attendedColumn1.setCellValueFactory(new PropertyValueFactory<Student, String>("attended"));


        if (AttendanceController.attendanceTableList != null) {
            attendanceTable1.setItems(AttendanceController.attendanceTableList);
        }
    }

    public void switchScenes(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void filter(ActionEvent event){

        filteredList.clear();
        LocalDate selectedDate = filterDatePicker.getValue();
        for (Student student: AttendanceController.attendanceTableList){
            List<String> AtDates = student.getAttended();
            List<String> DidntDates = student.getDidntAttend();
            for (String date:  AtDates){
                if (date.equals(selectedDate.toString())){
                    filteredList.add(student);
                }
            }
            for (String date:  DidntDates){
                if (date.equals(selectedDate.toString())){
                    filteredList.add(student);
                }
            }
        }

            attendanceTable1.setItems(filteredList);
    }

    @FXML
    private void saveToPDF () throws IOException{

        OutPDF.saveToFile(AttendanceController.attendanceTableList);
    }
}
