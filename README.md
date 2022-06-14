# lab_5_prog
## IVT - 2 sem - 2022
### Var - 3215

Реализовать консольное приложение, которое реализует управление коллекцией объектов в интерактивном режиме. В коллекции необходимо хранить объекты класса  `SpaceMarine`, описание которого приведено ниже.

**Разработанная программа должна удовлетворять следующим требованиям:**

-   Класс, коллекцией экземпляров которого управляет программа, должен реализовывать сортировку по умолчанию.
-   Все требования к полям класса (указанные в виде комментариев) должны быть выполнены.
-   Для хранения необходимо использовать коллекцию типа  `java.util.HashSet`
-   При запуске приложения коллекция должна автоматически заполняться значениями из файла.
-   Имя файла должно передаваться программе с помощью:  **переменная окружения**.
-   Данные должны храниться в файле в формате  `json`
-   Чтение данных из файла необходимо реализовать с помощью класса  `java.io.BufferedReader`
-   Запись данных в файл необходимо реализовать с помощью класса  `java.io.PrintWriter`
-   Все классы в программе должны быть задокументированы в формате javadoc.
-   Программа должна корректно работать с неправильными данными (ошибки пользовательского ввода, отсутсвие прав доступа к файлу и т.п.).

**В интерактивном режиме программа должна поддерживать выполнение следующих команд:**

-   `help`  : вывести справку по доступным командам
-   `info`  : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
-   `show`  : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
-   `add {element}`  : добавить новый элемент в коллекцию
-   `update id {element}`  : обновить значение элемента коллекции, id которого равен заданному
-   `remove_by_id id`  : удалить элемент из коллекции по его id
-   `clear`  : очистить коллекцию
-   `save`  : сохранить коллекцию в файл
-   `execute_script file_name`  : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
-   `exit`  : завершить программу (без сохранения в файл)
-   `add_if_min {element}`  : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
-   `remove_greater {element}`  : удалить из коллекции все элементы, превышающие заданный
-   `remove_lower {element}`  : удалить из коллекции все элементы, меньшие, чем заданный
-   `group_counting_by_name`  : сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе
-   `count_by_loyal loyal`  : вывести количество элементов, значение поля loyal которых равно заданному
-   `print_descending`  : вывести элементы коллекции в порядке убывания

**Формат ввода команд:**

-   Все аргументы команды, являющиеся стандартными типами данных (примитивные типы, классы-оболочки, String, классы для хранения дат), должны вводиться в той же строке, что и имя команды.
-   Все составные типы данных (объекты классов, хранящиеся в коллекции) должны вводиться по одному полю в строку.
-   При вводе составных типов данных пользователю должно показываться приглашение к вводу, содержащее имя поля (например, "Введите дату рождения:")
-   Если поле является enum'ом, то вводится имя одной из его констант (при этом список констант должен быть предварительно выведен).
-   При некорректном пользовательском вводе (введена строка, не являющаяся именем константы в enum'е; введена строка вместо числа; введённое число не входит в указанные границы и т.п.) должно быть показано сообщение об ошибке и предложено повторить ввод поля.
-   Для ввода значений null использовать пустую строку.
-   Поля с комментарием "Значение этого поля должно генерироваться автоматически" не должны вводиться пользователем вручную при добавлении.

**Описание хранимых в коллекции классов:**

```
public class SpaceMarine {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer health; //Поле не может быть null, Значение поля должно быть больше 0
    private Long heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3
    private boolean loyal;
    private AstartesCategory category; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
}
public class Coordinates {
    private Long x; //Поле не может быть null
    private int y;
}
public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;
    private Long marinesCount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private String world; //Поле не может быть null
}
public enum AstartesCategory {
    AGGRESSOR,
    INCEPTOR,
    TACTICAL,
    CHAPLAIN,
    HELIX;
}

```

**Отчёт по работе должен содержать:**

1.  Текст задания.
2.  Диаграмма классов разработанной программы.
3.  Исходный код программы.
4.  Выводы по работе.

**Вопросы к защите лабораторной работы:**

1.  Коллекции. Сортировка элементов коллекции. Интерфейсы  `java.util.Comparable`  и  `java.util.Comparator`.
2.  Категории коллекций - списки, множества. Интерфейс  `java.util.Map`  и его реализации.
3.  Параметризованные типы. Создание параметризуемых классов. Wildcard-параметры.
4.  Классы-оболочки. Назначение, область применения, преимущества и недостатки. Автоупаковка и автораспаковка.
5.  Потоки ввода-вывода в Java. Байтовые и символьные потоки. "Цепочки" потоков (Stream Chains).
6.  Работа с файлами в Java. Класс  `java.io.File`.
7.  Пакет  `java.nio`  - назначение, основные классы и интерфейсы.
8.  Утилита  `javadoc`. Особенности автоматического документирования кода в Java.
