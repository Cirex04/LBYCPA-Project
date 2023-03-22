package com.project.bookface;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.UUID;

public class HelloController {

    @FXML
    TextField userNameField;
    @FXML
    TextField displayNameField;
    @FXML
    TextField userPasswordField;

    private final String database_filename = "database/user_profiles.csv";
    File database = new File(Objects.requireNonNull(this.getClass().getResource(database_filename)).getPath());

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    protected void addUserToDatabase(String user_data) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(database,true));

        // \n is added for new row in table
        System.out.println(this.getClass().getResource(database_filename));
        System.out.println(user_data);
        printWriter.write(user_data);
        System.out.println("User has been registered to the database.");
        printWriter.close();
    }

    public void registerButtonClicked() throws IOException {
        UUID user_uid = generateUUID();
        String user_data = "\n" + user_uid.toString().substring(0,7) + "," + userNameField.getText() +
                "," + displayNameField.getText() + "," + userPasswordField.getText();
        addUserToDatabase(user_data);
    }
}