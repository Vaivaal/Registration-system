package Main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

import javafx.stage.Stage;

import java.io.IOException;

public class SystemController extends Controller{

    //Text input
    @FXML
    private TextField nameInput;

    @FXML
    private TextField surnameInput;

    @FXML
    private TextField groupInput;

    @FXML
    private TextField idInput;

    //Table
    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> surnameColumn;

    @FXML
    private TableColumn<Student, Integer> groupColumn;

    @FXML
    private TableColumn<Student, Integer> idColumn;

    //Buttons
    @FXML
    private Button insertButton;

    @FXML
    private Button loadXlsButton;

    @FXML
    private Button saveToXlsButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static ObservableList<Student> students;

    @FXML
    protected void switchScenes(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader();
        loader.setLocation(getClass().getResource("attendanceScene.fxml"));
        Parent rootAttendance = loader.load();

        Scene scene = new Scene(rootAttendance);

        AttendanceController controller = loader.getController();
        if (tableView.getSelectionModel().getSelectedItem() != null){
            controller.initData(tableView.getSelectionModel().getSelectedItem());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else{
            System.out.println("the student wasn't selected");

        }


    }

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));

        if (students != null) {
            tableView.setItems(students);
        }
    }

    @FXML
    protected void onInsertButtonClick(ActionEvent event){

        Student student = new Student(nameInput.getText(),
                surnameInput.getText(),
                Integer.parseInt(groupInput.getText()),
                Integer.parseInt(idInput.getText()));
        students = tableView.getItems();
        students.add(student);
        tableView.setItems(students);
    }

    @FXML
    protected void onLoadXlsButton() throws FileNotFoundException {

        new IOExcel().loadFile(tableView);
    }

    @FXML
    protected void onSaveToXlsButton(ActionEvent event) throws IOException {

        new IOExcel().saveToFile(tableView);
    }

    @FXML
    protected void onAttendanceTable(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader();
        loader.setLocation(getClass().getResource("attendanceTable.fxml"));
        Parent rootAttendance = loader.load();

        Scene scene = new Scene(rootAttendance);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onSaveToCsv() throws IOException {
        new IOCsv().saveToFile(tableView);
    }

    @FXML
    protected void onLoadCsv() throws IOException {
        new IOCsv().loadFile(tableView);
    }

}