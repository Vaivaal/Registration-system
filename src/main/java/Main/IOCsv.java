package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import static java.lang.Integer.parseInt;

public class IOCsv implements FileIO{

    public void saveToFile(TableView tableView) throws IOException {

        File file = new File("Students.csv");

        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);

            List<String[]> data = new ArrayList<String[]>();
            ObservableList<Student> students = tableView.getItems();
            for (Student st: students){
                int group = st.getGroup();
                int id = st.getId();
                data.add(new String[]{st.getName(), st.getSurname(), Integer.toString(group),
                        Integer.toString(id)});
            }

            writer.writeAll(data);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile(TableView tableView) throws FileNotFoundException {
        CSVReader reader = null;
        try
        {
            reader = new CSVReader(new FileReader("C:\\Users\\37065\\IdeaProjects\\Lab3\\Students.csv"));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null)
            {
                String name = nextLine[0];
                String surname = nextLine[1];
                int group = parseInt(nextLine[2]);
                int id = parseInt(nextLine[3]);

                Student student = new Student(name, surname, group, id);
                SystemController.students = tableView.getItems();
                SystemController.students.add(student);
                tableView.setItems(SystemController.students);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
