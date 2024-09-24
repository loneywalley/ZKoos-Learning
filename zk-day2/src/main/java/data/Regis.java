package data;

import org.zkoss.zul.Radiogroup;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Regis {

    public static List<User> userList = new LinkedList<>();
    static {
//         Integer id = 1;
        userList.add(
                new User(UUID.randomUUID().toString(),
                        "Bramasta",
                        "Male",
                        "21 December 2000",
                        "Indonesia"
                )
        );
        userList.add(
                new User(UUID.randomUUID().toString(),
                        "Luna",
                        "Female",
                        "01 January 2001",
                        "Japan"
                )
        );
    }


}
