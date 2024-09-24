package data;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public List<User> search(String keyword);

    public void add(User user);
}
