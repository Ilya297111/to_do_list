package entity;

public abstract class Variables {

    public static final String databaseName = "to_do_list";
    public static final String tableName1 = "users";
    public static final String tableName2 = "categories";
    public static final String tableName3 = "tasks";

    public static final String category1 = "'баг'";
    public static final String category2 = "'разработка'";
    public static final String category3 = "'изучение'";

    public static final String table1_table3_Id = "id";
    public static final String table1_Login = "login";
    public static final String table1_Password = "password";

    public static final String table2_table3_Category = "category";

    public static final String table3_Text = "text";
    public static final String table3_Name = "name";
    public static final String table3_Status = "status";

    public static final String URL = "jdbc:mysql://localhost/" + databaseName + "?useUnicode=true&serverTimezone=UTC";
    public static final String username = "ilya";
    public static final String password = "9708";
}
