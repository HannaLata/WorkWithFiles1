package com.mainacad.helper;

import com.mainacad.model.ConnectionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionInfoHelperTest {

    @Test
    void getRandomConnectionInfo() {
        ConnectionInfo testObj = ConnectionInfoHelper.getRandomConnectionInfo();
        assertNotNull(testObj);;
        assertNotNull(testObj.getConnectionTime());
        assertNotNull(testObj.getIp());
        assertNotNull(testObj.getSessionId());
        assertTrue(testObj.getSessionId() > 10000 && testObj.getSessionId() < 99999);
    }
}