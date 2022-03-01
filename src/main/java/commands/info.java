package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class info extends Command {


    @Override
    public void execute(Collection_manager<Route> a, BufferedReader buff) throws IOException {
        Stack<Route> b=a.getStorage();
        System.out.println((b.getClass().getName()+" "+a.getCreationDate().toString()));

    }

    @Override
    public String description() {
        return("информация о коллекции");

    }

    @Override
    public String getname() {
        return("info");
    }

}
