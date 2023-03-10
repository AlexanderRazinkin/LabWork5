package command;

public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            System.out.println(
                    "help : вывести справку по доступным командам\n" +
                            "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                            "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                            "add {element} : добавить новый элемент в коллекцию\n" +
                            "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                            "remove_by_id id : удалить элемент из коллекции по его id\n" +
                            "clear : очистить коллекцию\n" +
                            "save : сохранить коллекцию в файл\n" +
                            "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                            "exit : завершить программу (без сохранения в файл)\n" +
                            "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)\n" +
                            "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                            "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                            "remove_any_by_type type : удалить из коллекции один элемент, значение поля type которого эквивалентно заданному\n" +
                            "sum_of_age : вывести сумму значений поля age для всех элементов коллекции\n" +
                            "print_field_ascending_color : вывести значения поля color всех элементов в порядке возрастания");
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда help не имеет аргументов!");
            return false;
        }
    }

}
