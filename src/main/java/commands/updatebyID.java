package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class updatebyID extends Command {
    private boolean another_script;
    private PrintStream truestream;
    public updatebyID(Boolean another_script){
        this.another_script = another_script;
        truestream = System.out;
    }


    @Override
    public void execute(Collection_manager<Route> a, BufferedReader in) throws IOException {
        in.reset();
        if (another_script){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);
        }
        add add = new add(another_script);
        Route intik = new Route();
        Boolean cont = true;
        System.out.println("введите номер элемента, который хотите обновить");
        do {
            try {
                String c;
                String kekw = in.readLine();
                if (kekw == null) {
                    throw new NullPointerException();
                }
                String[] content = kekw.split(" ");
                if (content.length==2){c = content[1];}
                else{ c = content[0];}

                intik = a.find_by_id(Integer.parseInt(c));
                if (intik == null) {
                    throw new NullPointerException();
                }
                cont = false;
            } catch (NumberFormatException ex) {
                System.out.println("Возможно вы ввели не integer или забыли ввести данные, попробуйте ещё раз");
                if (another_script) {
                    throw new WrongScriptException();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Выход за границы, попробуйте ещё раз");
                if (another_script) {
                    throw new WrongScriptException();
                }
            }  catch (NullPointerException exp) {
                System.out.println("такого id нет");
                if (another_script) {
                    throw new WrongScriptException();
                }
            }

        } while (cont);
        add.addd(intik, in);




    }

    @Override
    public String description() {
        return("изменим элемент по ID");

    }

    @Override
    public String getname() {
        return("updatebyID");
    }
}
