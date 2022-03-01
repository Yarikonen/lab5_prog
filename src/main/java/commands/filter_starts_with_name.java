package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class filter_starts_with_name extends Command {
    boolean another_script;
    PrintStream truestream;
    public filter_starts_with_name(boolean another_script) {
        this.another_script = another_script;
        this.truestream= System.out;
    }
    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        b.reset();
        if(another_script){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);
        }
        System.setOut(truestream);
        String cc = b.readLine();
        String c = "";
        if (cc==null || (cc.split(" ").length==1 && another_script)) throw new WrongScriptException();
        String[] content = cc.split(" ");
        if (content.length==2){c = content[1].replaceAll(" ", "");}
        else if (c.isEmpty()){ System.out.println("Введите подстроку, вы забыли");
            c=b.readLine();}

        a.filterwithname(c);


    }

    @Override
    public String description() {
        return("вывожу все элемент начинающиеся с данной подстроки");

    }

    @Override
    public String getname() {
        return("filter_starts_with_name");
    }
}
