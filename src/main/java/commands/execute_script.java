package commands;

import collec_class.Collection_manager;
import collec_class.Route;
import utils.WrongScriptException;

import java.io.*;
import java.util.Stack;

public class execute_script extends Command {
    private boolean another_script;
    private Collection_manager<Route> target;
    private Stack<String> scripts = new Stack<>();
    private String script;
    private String scriptnext;
    private PrintStream trueStream;
    public execute_script(boolean another_script) {
        this.another_script = another_script;
        this.trueStream= System.out;
    }
    @Override
    public void execute(Collection_manager<Route> a, BufferedReader buff) throws IOException {

        if(another_script){
            PrintStream dummyStream = new PrintStream(new OutputStream(){
                public void write(int b) {
                }
            });
            System.setOut(dummyStream);

        }
        scripts = new Stack<>();

        target = a;
        boolean cont = true;
        buff.reset();
        do{
        try {
            String[] content = buff.readLine().split(" ");
            if (content.length==2){script = content[1];}
            else{ script = content[0];}
            scriptnext = script;
            exec(script);
            cont = false;
        }
        catch(FileNotFoundException ex){
            if (script.equals("EXIT")) cont = false;
            else System.out.println("файл не найден или вы забыли его ввести, попробуйте ввести другой\n---Чтобы выйти из программы напишите EXIT---");

        }

        }while(cont);


    }

    @Override
    public String description() {
        return("я умею запускать скрипт");

    }

    @Override
    public String getname() {
        return("execute_script");
    }
    private void exec(String script) throws IOException {
        scripts.add(scriptnext);
        BufferedReader scriptReader = new BufferedReader(new FileReader(script));
        Command_man man = new Command_man(
                new save(true),
                new hentai()
                ,new show()
                ,new remove_by_id(true)
                ,new info()
                ,new add(true)
                ,new help()
                ,new updatebyID(true)
                ,new execute_script(true)
                ,new remove_last()
                ,new remove_lover(true)
                ,new reorder()
                ,new remove_all_by_distance(true)
                ,new sum_of_distance()
                ,new filter_starts_with_name(true));

        while (true) {
            scriptReader.mark(8192);
            String command;
            String commargs = scriptReader.readLine();
            if (commargs == null) {
                break;
            }
            String[] content = commargs.split(" ");
            command=content[0];
            try{
            if (command.equals("execute_script")) {
                if (content.length==1) throw new WrongScriptException();
                scriptnext = content[1];
                if (!scripts.contains(scriptnext)) {
                    try {
                        exec(scriptnext);
                    } catch (FileNotFoundException ex) {
                        System.out.println("файл " + scriptnext+ " не найден или доступ к нему затреднён");
                    }
                } else {
                    System.out.println("Данный скрипт уже исполнялся - "+scriptnext);
                }
            } else {
                try {
                    man.command_exec(command, target, scriptReader);
                } catch (NullPointerException exp) {
                    System.setOut(trueStream);
                    System.out.println("Команды " + command + " не существует");
                }
            }
            }catch(IOException e){
                try {
                    scriptReader.reset();
                    scriptReader.readLine();
                } catch (IOException exp) {
                    exp.printStackTrace();
                    System.out.println("SAD");
                }
                System.setOut(trueStream);
                System.out.println("Ошибка при выполнении " + command + "\nСкрипт переходит к следующей команде");
            }
        }

    }
}