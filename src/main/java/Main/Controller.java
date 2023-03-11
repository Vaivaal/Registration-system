package Main;

import javafx.event.ActionEvent;

import java.io.IOException;

public abstract class Controller {

    abstract void switchScenes(ActionEvent event)throws IOException;
    abstract void initialize();
}
