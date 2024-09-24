package data;

import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService{

    public UserServiceImpl(){

    }

    public List<User> findAll(){
        return Regis.userList;
    }

    public List<User> search(String keyword){
        List<User> result = new LinkedList<User>();
        if (keyword==null || "".equals(keyword)){
            result = Regis.userList;
        }
        else{
            for (User u: Regis.userList){
                if(u.getName().toLowerCase().contains(keyword.toLowerCase())){
                    result.add(u);
                }
            }
        }
        return result;
    }

    @Override
    public void add(User user) {
        Regis.userList.add(user);
    }
}
