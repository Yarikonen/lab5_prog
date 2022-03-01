package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class remove_all_by_distance extends Command {
    boolean another_script;
    PrintStream truestream;
    public remove_all_by_distance(boolean another_script){
        this.another_script = another_script;
        truestream=System.out;
    }
    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        Long distance;
        if(another_script){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);

        }
        boolean cont = true;
        b.reset();
        do {
            try {
                String c;
                String[] content = b.readLine().split(" ");
                System.out.println(content[0]);
                if (content.length==2){c = content[1];}
                else{ c = content[0];}
                if (c == null) throw new WrongScriptException();
                distance = Long.parseLong(c);
                a.remove_all_by_distance(distance);
                cont = false;
            } catch (NumberFormatException ex) {
                System.out.println("число типа Long не найдено или вы забыли его ввести, попробуйте ввести ещё раз");
                if (another_script) throw new WrongScriptException();
            }

        } while (cont);





    }

    @Override
    public String description() {
        return("удаляю всё че равно такому расстоянию");

    }

    @Override
    public String getname() {
        return("remove_all_by_distance");
    }
}
