package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public abstract class Command {
    private PrintStream truestream;
    public abstract void execute(Collection_manager<Route> a, BufferedReader b) throws IOException;
    public abstract String description();
    public String  getname(){
        return(this.getClass().getName());
    }
    public Command(){
    }


}
