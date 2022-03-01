package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;

public class sum_of_distance extends Command {

    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        System.out.println(a.sum_distance());
    }

    @Override
    public String description() {
        return("суммирую дистанции");

    }

    @Override
    public String getname() {
        return("sum_of_distance");
    }
}
