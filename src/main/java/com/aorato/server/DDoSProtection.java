package com.aorato.server;

import java.time.LocalDateTime;

public class DDoSProtection {
    LocalDateTime time;
    int count;

    public DDoSProtection(LocalDateTime time) {
        this.time = time;
        this.count = 0;
    }

    public DDoSProtection(LocalDateTime time, int count) {
        this.time = time;
        this.count = count;
    }
}
