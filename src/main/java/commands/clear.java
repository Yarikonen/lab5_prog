package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;

public class clear extends Command {
    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        a.getStorage().removeAllElements();
        System.out.println("колекция почищена");

    }

    @Override
    public String description() {
        return("чисти");

    }

    @Override
    public String getname() {
        return("clear");
    }
}
