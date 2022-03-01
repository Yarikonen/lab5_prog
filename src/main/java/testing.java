import collec_class.*;
import commands.*;
import utils.WrongScriptException;
import utils.WrongXMLWormatException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Set;


public class testing {
    public static void main(String ... arg)  {
        try {
            JAXBContext context = JAXBContext.newInstance(Collection_manager.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Collection_manager<Route> kek = new Collection_manager<>();
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            try {
                kek = (Collection_manager) unmarshaller.unmarshal(new BufferedReader(new FileReader("aboba.txt")));
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                for ( Route a: kek.getStorage()
                     ) {
                    Set<ConstraintViolation<Route>> viol = validator.validate(a);
                    if (viol.size()>0){throw new WrongXMLWormatException();}

                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден или к нему нет доступа");
                exit exit = new exit();
                try {
                    exit.execute(kek, buff);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            catch(WrongScriptException exp){
                System.out.println("XML файл задан некорректно");
            }


            Command_man man = new Command_man(

                    new exit(),
                    new save(false),
                    new remove_by_id(false),
                    new hentai()
                    , new show()
                    , new info()
                    , new add(false)
                    , new help()
                    , new updatebyID(false)
                    , new execute_script(false)
                    , new remove_last()
                    , new remove_lover(false)
                    , new reorder()
                    , new remove_all_by_distance(false)
                    , new sum_of_distance()
                    , new filter_starts_with_name(false));
            boolean booling = true;
            do {
                try {
                    PrintStream originalStream = System.out;
                    System.setOut(originalStream);
                    buff.mark(8192);
                    String aa = buff.readLine();

                    man.command_exec(aa.split(" ")[0], kek, buff);
                } catch (NullPointerException excp) {
                    excp.printStackTrace();
                    System.out.println("нет такой команды//вы принудительно вышли");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (booling);
        }
        catch(JAXBException exp){
            System.out.println("problems");
        }




    }


}
