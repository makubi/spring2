package at.makubi.mysingleton;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MySingleton {
    void doSomeMagic();
}
