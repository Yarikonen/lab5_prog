package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import utils.WrongScriptException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.*;

public class save extends Command {
    private boolean another_script;
    private PrintStream truestream;
    public save(Boolean another_script){
       this.another_script = another_script;
       truestream=System.out;
    }


    @Override
    public void execute(Collection_manager<Route> a, BufferedReader b) throws IOException {
        if (another_script){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);
        }
        System.out.println("Введите файл куда хотите записать");
        System.setOut(truestream);
        boolean cont = true;

        do {
            try {
                String file = b.readLine();
                if (file == null) throw new NullPointerException();
                PrintWriter write = new PrintWriter(file);
                JAXBContext context = JAXBContext.newInstance(Collection_manager.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(a, write);
                cont = false;
            } catch (IOException e) {
                System.out.println("Нет доступа");
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (NullPointerException exp) {
                if (another_script) {
                    throw new WrongScriptException();
                }
            }
        } while (cont);





    }

    @Override
    public String description() {
        return("сохраняю коллекцию в файл");

    }

    @Override
    public String getname() {
        return("save");
    }
}
