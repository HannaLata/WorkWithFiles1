package com.mainacad.service;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExistingFileTest {

    private static final String FILE_NAME = "connections.txt";
    private List<ConnectionInfo> connectionInfoList;

    @BeforeEach
    void setUp() {
        connectionInfoList = FileService.readConnectionsFromFile(FILE_NAME);
    }

    @AfterEach
    void tearDOwn() {
        boolean append = false;
        for (ConnectionInfo connectionInfo :connectionInfoList) {
            FileService.writeTextToFile(connectionInfo.toString(), FILE_NAME, append);
            append = true;
        }

    }

    @Test
    void testReadAndWriteObject() {
        ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
        FileService.writeTextToFile(connectionInfo.toString(), FILE_NAME, false);
        List<ConnectionInfo> testObject = FileService.readConnectionsFromFile(FILE_NAME);

        assertEquals(testObject.get(0).getIp(), connectionInfo.getIp());
        assertEquals(testObject.get(0).getSessionId(), connectionInfo.getSessionId());
        assertEquals(testObject.get(0).getConnectionTime(), connectionInfo.getConnectionTime());
    }
}
