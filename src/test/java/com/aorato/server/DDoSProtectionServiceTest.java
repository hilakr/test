package com.aorato.server;

import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DDoSProtectionServiceTest {
    Hashtable<Integer, DDoSProtection> clientsRequests;

    @Test
    public void isAttackTestWithMoreThan5Requests() throws InterruptedException {
        clientsRequests = new Hashtable<>();
        clientsRequests.put(1, new DDoSProtection(LocalDateTime.now(), 6));
        DDoSProtectionService service = new DDoSProtectionService(clientsRequests);
        TimeUnit.SECONDS.sleep(5);

        boolean isAttack = service.isAttack(1);

        assertTrue(isAttack);
    }

    @Test
    public void isAttackTestWithMoreThan5Seconds() throws InterruptedException {
        clientsRequests = new Hashtable<>();
        clientsRequests.put(1, new DDoSProtection(LocalDateTime.now(), 2));
        DDoSProtectionService service = new DDoSProtectionService(clientsRequests);
        TimeUnit.SECONDS.sleep(6);

        service.isAttack(1);

        assert(clientsRequests.get(1).count == 0);
    }

}