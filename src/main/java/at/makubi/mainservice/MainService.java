package at.makubi.mainservice;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

@Transactional
interface MainService {

    void sync();

    @Async
    void async();

    void sync2();
}
