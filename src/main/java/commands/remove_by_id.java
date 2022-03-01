package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class remove_by_id extends Command{
    private boolean anotherscript;
    private PrintStream truestream;
    public remove_by_id(Boolean anotherscript){
        this.anotherscript = anotherscript;
        truestream = System.out;
    }
    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if(anotherscript){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);

        }
        b.reset();
        Route intik = new Route();
        boolean cont=true;
        do {
            try {
                String c;
                String kekw = b.readLine();
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
                if (anotherscript) {
                    throw new WrongScriptException();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Выход за границы, попробуйте ещё раз");
                if (anotherscript) {
                    throw new WrongScriptException();
                }
            }  catch (NullPointerException exp) {
                System.out.println("такого id нет");
                if (anotherscript) {
                    throw new WrongScriptException();
                }
            }

        } while (cont);
        a.getStorage().remove(intik);
    }

    @Override
    public String getname() {
        return("remove_by_id");
    }

    @Override
    public String description() {
        return("могу удалять элемент по его id");
    }
}
