package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import utils.WrongScriptException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class remove_lover extends Command {
    private boolean another_script;
    private PrintStream truestream;
    public remove_lover(Boolean another_script){
        this.another_script = another_script;
        truestream = System.out;

    }

    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        boolean cont=true;
        if (another_script){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);
        }
        Route route = new Route();

        do {
            System.out.println("Хотите выбрать элемент из существуюших? да/нет");
            try {
                b.mark(8192);
                String decis = b.readLine();
                if (decis.equals("да")) {
                    System.out.println("Введите ID");
                    String c = b.readLine();
                    route = a.find_by_id(Integer.parseInt(c));
                    if (route == null) {
                        throw new NullPointerException();
                    }
                    cont = false;
                }
                else if (decis.equals("нет")) {
                    add add = new add(another_script);
                    add.addd(route, b);
                    cont = false;
                } else {
                    System.out.println("ДА/нЕТ");
                    if (another_script) throw new WrongScriptException();
                }

            } catch (NumberFormatException exp) {
                System.out.println("ID должен быть целым положительным");
                if (another_script) {
                    throw new WrongScriptException();
                }
            } catch (NullPointerException exp) {
                System.out.println("Такого элемента нет");
                if (another_script) {
                    throw new WrongScriptException();
                }
            }
        } while (cont);

        a.remove_lower(route);

    }

    @Override
    public String description() {
        return("удалю все элементы которые меньше");

    }

    @Override
    public String getname() {
        return("remove_lover");
    }
}
