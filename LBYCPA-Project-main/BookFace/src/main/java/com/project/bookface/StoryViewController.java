package com.project.bookface;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


public class StoryViewController {


    public ImageView storyImage;
    public Label user;

    public Queue<Story> storyQueue = new LinkedList<>();
    public Queue<Story> updateStoryData = new LinkedList<>();
    public void initialize() throws IOException {
        storyQueue = loadStoryDatabase();
        storyImage.setImage(storyQueue.peek().getImage());
        user.setText(storyQueue.peek().getDisplayName());
        storyQueue.remove();
    }

    public void nextStory(ActionEvent actionEvent) {

        if (!storyQueue.isEmpty()){
            System.out.println(storyQueue.size());
            storyImage.setImage(storyQueue.peek().getImage());
            user.setText(storyQueue.peek().getDisplayName());
            storyQueue.remove();
        } else {
            Image pic = new Image("caughtup.jpg");
            storyImage.setImage(pic);
            user.setText("");
        }


    }

    public void addStory(ActionEvent actionEvent) throws IOException {
        String uid = "2314";                        // this should vary depending on which account posted the story
        String displayName = "Bon Valencerina";     // this should vary depending on which account posted the story
        String storyFilename = null;

        FileChooser fc = new FileChooser();
        //fc.setInitialDirectory(new File("C:\\Users\\ercid\\OneDrive\\Documents"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile!= null){
            storyFilename = selectedFile.getName();
            System.out.println(storyFilename);
            uploadImage(selectedFile, storyFilename);
        }

        String filename = "D:\\IntelliJ IDEA 2022.3.2\\Projects\\LBYCPA-Project-main\\BookFace\\src\\main\\java\\com\\project\\bookface\\StoryDatabase.csv";
        FileWriter fstream = new FileWriter(filename, true);
        BufferedWriter writer = new BufferedWriter(fstream);
        writer.write(uid + "," + displayName + "," + storyFilename);
        writer.newLine();
        writer.close();
    }

    private Queue loadStoryDatabase() throws IOException {
        Queue<Story> storyQueue = new LinkedList<>();
        String filename = "D:\\IntelliJ IDEA 2022.3.2\\Projects\\LBYCPA-Project-main\\BookFace\\src\\main\\java\\com\\project\\bookface\\StoryDatabase.csv";
        BufferedReader reader;
        String line;

        reader = new BufferedReader(new FileReader(filename));
        while ((line = reader.readLine()) != null){
            Story story = new Story();
            String[] attrib = line.split(",");
            story.setUid(attrib[0]);
            story.setDisplayName(attrib[1]);
            story.setImage(attrib[2]);
            storyQueue.add(story);
        }

        return storyQueue;

    }

    private void uploadImage(File source, String filename) throws IOException {
        File dest = new File("D:\\IntelliJ IDEA 2022.3.2\\Projects\\LBYCPA-Project-main\\BookFace\\src\\main\\resources");
        InputStream is = null;
        OutputStream os = null;
            is = new FileInputStream(source);
            os = new FileOutputStream(dest + File.separator + filename);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            is.close();
            os.close();
        }
    }
