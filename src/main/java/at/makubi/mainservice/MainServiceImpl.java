package at.makubi.mainservice;

import at.makubi.ApplicationConfig;
import at.makubi.entities.MyEntity;
import at.makubi.mysingleton.MySingleton;
import at.makubi.repositories.MyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
class MainServiceImpl implements MainService {

    private final MySingleton mySingleton;
    private final MyEntityRepository repository;

    @Autowired
    public MainServiceImpl(MySingleton mySingleton, MyEntityRepository repository) {
        this.mySingleton = mySingleton;
        this.repository = repository;
    }

    public void neueMethode() {
        mySingleton.doSomeMagic();
    }

    @Override
    public void sync() {
        try {
            Thread.sleep(500);
            MyEntity myEntity = new MyEntity();
            myEntity.setName("felix");
            repository.save(myEntity);
//            throw new RuntimeException("exit");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Async
    @Override
    public void async() {
        try {
            Thread.sleep(3000);
            System.out.println("slept 3 secs");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void sync2() {
       repository.findAll().iterator().next().setName("benjamin");
    }

    public static void main(String... args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MainService bean = annotationConfigApplicationContext.getBean(MainService.class);
        bean.async();
        bean.sync();

        bean.sync2();
    }

}
