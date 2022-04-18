package UserData;

import java.util.ArrayList;

public class UserCollection extends ArrayList<User> {

    public UserCollection()
    {

    }

    public UserCollection(ArrayList<User> users)
    {
        this.addAll(users);
    }

        public boolean contains(String username)
    {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getUsername().equals(username))
            {
                return true;
            }
        }

        return false;
    }

    public boolean add(User user)
    {
        if(!this.contains(user))
        {
            return super.add(user);
        }

        return false;
    }

    public User get(String username)
    {
        for (int i = 0; i < super.size(); i++) {
            if (super.get(i).getUsername().equals(username))
            {
                return super.get(i);
            }
        }

        return null;
    }
}

