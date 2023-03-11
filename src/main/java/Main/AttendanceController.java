package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Student selectedStudent;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label groupLabel;

    @FXML
    private Label idLabel;

    @FXML
    private TableView<Student> attendanceTable;

    @FXML
    private TableColumn<Student, String> attendedColumn;

    @FXML
    private TableColumn<Student, String> didntAttendColumn;

    @FXML
    ChoiceBox<String> attendanceBox;

    private String[] attendanceList = {"attended", "didn't attend"};

    ObservableList<Student> students;

    List<String> datesAttended = new ArrayList<String>();
    List<String> datesDidntAttend =  new ArrayList<String>();
    List<String> attendance = new ArrayList<>();

    @FXML
    void switchScenes(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initData(Student student){

        selectedStudent = student;
        nameLabel.setText(selectedStudent.getName());
        surnameLabel.setText(selectedStudent.getSurname());
        groupLabel.setText(Integer.toString(selectedStudent.getGroup()));
        idLabel.setText(Integer.toString(selectedStudent.getId()));
    }

    public void initialize(){
        attendanceBox.getItems().addAll(attendanceList);
        attendanceBox.setValue("attended");
        if (students != null) {
            attendanceTable.setItems(students);
        }

    }
    Student student;

    public static ObservableList<Student> attendanceTableList = FXCollections.observableArrayList();
    int pressed = 0;

    @FXML
    private void onSubmitButton(ActionEvent event){


        LocalDate date = datePicker.getValue();
        String choice = attendanceBox.getValue();

        if (choice == "attended"){
            attendedColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("attended"));
            datesAttended.add(date.toString());
        }
        else if (choice == "didn't attend"){
            didntAttendColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("didntAttend"));
            datesDidntAttend.add(date.toString());

        }
        student = new Student(selectedStudent.getName(),
                selectedStudent.getSurname(),
                selectedStudent.getGroup(),
                selectedStudent.getId(),
                attendance, datesAttended, datesDidntAttend);


        attendanceTable.getItems().clear();
        students = attendanceTable.getItems();

        students.add(student);
        attendanceTable.setItems(students);

        if (pressed == 0) {
            attendanceTableList.add(student);
        }
        ++pressed;
    }

}
