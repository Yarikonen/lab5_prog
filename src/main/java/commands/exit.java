package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;

public class exit extends Command {
    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        System.out.println("завершение работы");
        System.exit(0);

    }

    @Override
    public String description() {
        return("ОФАЙ");

    }

    @Override
    public String getname() {
        return("exit");
    }
}
