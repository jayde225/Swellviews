package UserData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * allows for a file to be read and to be written
 */
public class UserSerializer {
    private static final String userFile = "src\\Users.json";

    // READ AND WRITING FUNCTIONS
    /**
     * read user data from a json file
     * @return
     */
    public static UserCollection read()
    {
        String jsonString = "";
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileReader(userFile));
        } catch (FileNotFoundException fe) {
            return new UserCollection();   // if there is no file, then it will return an empty list
        }

        // Build the jsonString object line by line
        while (inFile.hasNextLine()) {
            jsonString = jsonString + inFile.nextLine();
        }

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        User[] users;  //A java primitive of Movie class
        ArrayList<User> userList = new ArrayList<User>(); // An array list to hold a collection of movies
        users = gson.fromJson(jsonString, User[].class);
        Collections.addAll(userList, users);

        return new UserCollection(userList);
    }

    /**
     * write user data to a json file
     * @param users
     */
    public static void write(UserCollection users)
    {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(users);

        try {
            FileWriter myWriter = new FileWriter(userFile);
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("File could not be written");
        }
    }
}
