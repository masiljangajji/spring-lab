package com.masiljangajji.scheduler.domain;

import java.time.LocalDateTime;

public interface UserDeletePolicy {

    LocalDateTime getCutoff();

}
