package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;

public class remove_last extends Command {

    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if (a.getStorage().isEmpty()) {
            System.out.println("коллекция и так пустая");
        } else {
            a.getStorage().pop();
        }
    }

    @Override
    public String description() {
        return("удалю послдений элемент");

    }

    @Override
    public String getname() {
        return("remove_last");
    }
}
