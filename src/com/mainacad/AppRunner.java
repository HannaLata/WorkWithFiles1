package com.mainacad;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import com.mainacad.model.Person;
import com.mainacad.service.FileService;

import java.util.logging.Logger;

public class AppRunner {

    private static final Logger LOGGER = Logger.getLogger(AppRunner.class.getName());

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
            FileService.writeTextToFile(connectionInfo.toString(), "connections.txt", true);
        }

        Person person = new Person("Hanna", 27, "woman");
        FileService.writeObjectToFile(person, "person.obj");
        FileService.getObjectFromFile("person.obj");



    }
}
