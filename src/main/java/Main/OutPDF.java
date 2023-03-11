package Main;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class OutPDF {

    public static void saveToFile(ObservableList<Student> students){
        Document document = new Document();
        try {
            OutputStream outputStream = new FileOutputStream(new File("Students.pdf"));
            PdfWriter.getInstance(document, outputStream);
            document.open();

            for (Student st: students){

                String name = st.getName();
                String surname = st.getSurname();
                int group = st.getGroup();
                int id = st.getId();
                List<String> attended = st.getAttended();
                List<String> didntAttend = st.getDidntAttend();
                document.add(new Paragraph(name+" "+surname+"; group: "+group+"; ID: "+id+
                        "; attended: "+attended+"; didin't attend: "+didntAttend));
            }
            document.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
