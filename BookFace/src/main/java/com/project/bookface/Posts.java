package com.project.bookface;

import java.io.*;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @brief Provides the list of posts with the UUID to the corresponding user
 */
public class Posts {
    /**
     * properties
     */
    private final ArrayList<PostNode> POST_LIST;

    /**
     * @brief PostNode model (node) - detail of the PostNode component
     */
    public class PostNode {
        /**
         * properties
         */
        private String UID;
        private String name;
        private String text;
        private Date TimeStamp;
        private int likes;

        /**
         * constructors
         */
        public PostNode(String UID, String name, String text, Date timeStamp, int likes) {
            this.UID = UID;
            this.name = name;
            this.text = text;
            TimeStamp = timeStamp;
            this.likes = likes;
        }

        /**
         * behaviors
         */
        public String getUID() {
            return UID;
        }

        public String getName() {
            return name;
        }

        public String getText() {
            return text;
        }

        public Date getTimeStamp() {
            return TimeStamp;
        }

        public int getLikes() {
            return likes;
        }
    }

    /**
     * constructors
     */
    public Posts() {
        POST_LIST = new ArrayList<>(5);
    }

    /**
     * behaviors
     */
    public void connection() throws URISyntaxException, FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\src\\" +
                "main\\" +
                "resources\\" +
                "com\\" +
                "project\\" +
                "bookface\\" +
                "database\\" +
                "posts.csv"));
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            POST_LIST.add(new PostNode(line[0], line[1], line[2], new SimpleDateFormat("dd/MM/yyyy").parse(line[3]), Integer.parseInt(line[4])));
        }
    }

    public void writeData(String UID, String name, String text, String timeStamp, int likes) throws IOException {
        FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\src\\" +
                "main\\" +
                "resources\\" +
                "com\\" +
                "project\\" +
                "bookface\\" +
                "database\\" +
                "posts.csv", true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write((UID + "," + name + "," + text + "," + timeStamp + "," + likes));
        writer.newLine();
        writer.close();
        fileWriter.close();
    }

    public ArrayList<PostNode> getPOST_LIST() {
        return POST_LIST;
    }
}
