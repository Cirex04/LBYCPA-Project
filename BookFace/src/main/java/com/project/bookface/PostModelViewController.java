package com.project.bookface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @brief Controller for the post frame
 */

public class PostModelViewController implements Initializable {
    /**
     * properties
     */
    private Posts posts = new Posts();

    /**
     * Post windows
     */
    @FXML
    private AnchorPane writePost;
    @FXML
    private AnchorPane viewPost;

    /**
     * Components of a view post
     */
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private TextArea details;
    @FXML
    private Label likes;

    /**
     * Components of a write post
     */
    @FXML
    private Label nameW;
    @FXML
    private TextArea detailsW;


    /**
     * constructors
     */
    public PostModelViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            posts.connection(); // Retrieves the data from the database
        } catch (URISyntaxException | FileNotFoundException | ParseException e) {
            /**
             * Catches an error based from the given data input
             */
            throw new RuntimeException(e);
        }

        name.setText(posts.getPOST_LIST().get(0).getName());
        date.setText(posts.getPOST_LIST().get(0).getTimeStamp().toString());
        details.setText(posts.getPOST_LIST().get(0).getText());
        likes.setText(String.valueOf(posts.getPOST_LIST().get(0).getLikes()));
        nameW.setText(name.getText());
    }

    /**
     * behaviors
     */
    @FXML
    public void writeButton(ActionEvent event) {
        writePost.setDisable(false);
        writePost.setVisible(true);
        viewPost.setDisable(true);
        viewPost.setVisible(false);
    }

    @FXML
    public void submitButton(ActionEvent event) throws IOException {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDate = localDate.format(dateTimeFormatter);
        posts.writeData(posts.getPOST_LIST().get(0).getUID(), nameW.getText(), detailsW.getText(), formatDate, 0);
        writePost.setDisable(true);
        writePost.setVisible(false);
        viewPost.setDisable(false);
        viewPost.setVisible(true);
        detailsW.clear();
    }

    @FXML
    public void closeButton(ActionEvent event) {
        writePost.setDisable(true);
        writePost.setVisible(false);
        viewPost.setDisable(false);
        viewPost.setVisible(true);
        detailsW.clear();
    }
}
