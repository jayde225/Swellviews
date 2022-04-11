import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserSerializer {

    private String filename;

    public UserSerializer(String filename)
    {
        this.filename = filename;
    }

    // read json info, turn into file, then returns data
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

    // writes to the file
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
