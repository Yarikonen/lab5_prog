package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;

public class reorder extends Command {

    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        a.reorder();
    }

    @Override
    public String description() {
        return("переворачиваю коллекцию");

    }

    @Override
    public String getname() {
        return("reorder");
    }
}
