package commands;

import collec_class.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class show extends Command {
    Stack<Route> target = new Stack<>();

    @Override
    public void execute(Collection_manager<Route> a, BufferedReader buff) throws IOException {
        this.target = a.getStorage();
        this.infor();
        try {
            buff.mark(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String description() {
        return("вывожу че в коллекции");

    }

    @Override
    public String getname() {
        return("show");
    }
    public void infor(){
        for (Route route: target
             ) {
            System.out.println(route.toString());


        }
    }

}
