package com.aorato.server;

import org.graalvm.compiler.debug.TimeSource;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Hashtable;

public class DDoSProtectionService {
    Hashtable<Integer, DDoSProtection> clientsRequests;

    public DDoSProtectionService(Hashtable<Integer, DDoSProtection> clientsRequests) {
        this.clientsRequests = clientsRequests;
    }

    public DDoSProtectionService() {
        clientsRequests = new Hashtable<>();
    }

    public boolean isAttack(int clientId){
        LocalDateTime currentTime = LocalDateTime.now();
        if (!clientsRequests.containsKey(clientId)) {
            clientsRequests.put(clientId, new DDoSProtection(LocalDateTime.now()));
        } else {
            if (clientsRequests.get(clientId).count > 5 && Duration.between(clientsRequests.get(clientId).time, currentTime).getSeconds() >= 5) {
                return true;
            }
            if (Duration.between(clientsRequests.get(clientId).time, currentTime).getSeconds() >= 5) {
                clientsRequests.put(clientId, new DDoSProtection(LocalDateTime.now()));
            } else {
                clientsRequests.put(clientId, new DDoSProtection(clientsRequests.get(clientId).time, clientsRequests.get(clientId).count + 1));
            }
        }
        return false;
    }
}
