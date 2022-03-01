package commands;

import collec_class.Collection_manager;
import collec_class.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


public  class Command_man {


    private Map<String, Command> commandMap = new HashMap<>();
    private static PrintStream originalStream;
    {originalStream = System.out;}
    public Command_man(Command ... commands){

        for (Command i: commands
             ) {
            commandMap.put(i.getname(),i);
        }

    }
    public void command_exec(String a, Collection_manager<Route> target, BufferedReader buff) throws IOException {
        System.setOut(originalStream);
        commandMap.get(a).execute(target,buff);
    }


}
