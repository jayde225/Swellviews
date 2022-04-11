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

    // VARIABLES
    private String filename;

    // CONSTRUCTORS

    /**
     * sets the inputed file name to the file name actually being used
     * @param filename
     */
    public UserSerializer(String filename)
    {
        this.filename = filename;
    }

    // READ AND WRITING FUNCTIONS
    /**
     * read json file then turn it into a file, then returns file with data in it
     * @return
     */
    public ArrayList<User> read()
    {
        String jsonString = "";
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileReader(this.filename));
        } catch (FileNotFoundException fe) {
            return new ArrayList<User>();   // if there is no file, then it will return an empty list
        }

        // Build the jsonString object line by line
        while (inFile.hasNextLine()) {
            jsonString = jsonString + inFile.nextLine();
        }

        Gson gson = new Gson();
        User[] users;  //A java primitive of Movie class
        ArrayList<User> userList = new ArrayList<User>(); // An array list to hold a collection of movies
        users = gson.fromJson(jsonString, User[].class);
        Collections.addAll(userList, users);

        return userList;
    }

    /**
     * uses the created file and edits or writes in it
     * @param Users
     */
    public void write(ArrayList<User> Users)
    {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(Users);

        try {
            FileWriter myWriter = new FileWriter(this.filename);
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("File could not be written");
        }
    }
}
