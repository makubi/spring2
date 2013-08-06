package at.makubi.mainservice;

import at.makubi.ApplicationConfig;
import at.makubi.entities.*;
import at.makubi.mysingleton.MySingleton;
import at.makubi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
class EntryServiceImpl implements EntryService {

    private final MySingleton mySingleton;
    private final ZoneRepository repository;
    private final CategoryRepository categoryRepository;
    private final EntryRepository entryRepository;
    private final TranslationRepository translationRepository;
    private final IdentifierRepository identifierRepository;


    @Autowired
    public EntryServiceImpl(MySingleton mySingleton, ZoneRepository repository, CategoryRepository categoryRepository, EntryRepository entryRepository, TranslationRepository translationRepository, IdentifierRepository identifierRepository) {
        this.mySingleton = mySingleton;
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.entryRepository = entryRepository;
        this.translationRepository = translationRepository;
        this.identifierRepository = identifierRepository;
    }

    public void neueMethode() {
        mySingleton.doSomeMagic();
    }

    public static void main(String... args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        EntryService bean = annotationConfigApplicationContext.getBean(EntryService.class);

        Identifier identifier = new Identifier();
        identifier.setNumber(100001);
        identifier.setSubNumber(1);

        Collection<Translation> translations = new ArrayList<Translation>();

        Translation translation = new Translation();
        translation.setCountryCode("de");
        translation.setText("Hallo");
        translations.add(translation);

        Collection<Entry> entries = new ArrayList<Entry>();
        Entry entry = new Entry();
        entry.setIdentifier(identifier);
        entry.setTexts(translations);
        entry.setMetaInformation("meta inf");
        entry.setMaxLength(10);

        entries.add(entry);

        Collection<Category> categories = new ArrayList<Category>();
        Category category = new Category();
        category.setName("Main");
        category.setStartNumber(100000);
        category.setEndNumber(110000);
        category.setEntries(entries);
        categories.add(category);

        Zone zone = new Zone();
        zone.setName("untis");
        zone.setStartNumber(100000L);
        zone.setEndNumber(200000L);
        zone.setCategories(categories);

        bean.saveIdentifier(identifier);
        bean.saveTranslations(translations);
        bean.saveEntries(entries);
        bean.saveCategories(categories);
        bean.saveZone(zone);

        Collection<Entry> main = bean.getAllEntries();

        System.out.println(main.size());
    }


    @Override
    public Collection<Entry> getAllEntries() {
        Collection<Entry> allEntries = new ArrayList<Entry>();
        for(Zone zone : repository.findAll()) {
            for(Category category : zone.getCategories()) {
                allEntries.addAll(category.getEntries());
            }
        }

        return allEntries;
    }

    @Override
    public Collection<Entry> getAllEntriesForCategory(String category) {
        Category byCategories_name = categoryRepository.findByName(category);

        return byCategories_name.getEntries();
    }

    @Override
    public void saveZone(Zone zone) {
        repository.save(zone);
    }

    @Override
    public void saveCategories(Collection<Category> categories) {
        categoryRepository.save(categories);
    }

    @Override
    public void saveTranslations(Collection<Translation> translations) {
        translationRepository.save(translations);
    }

    @Override
    public void saveEntries(Collection<Entry> entries) {
        entryRepository.save(entries);
    }

    @Override
    public void saveIdentifier(Identifier identifier) {
        identifierRepository.save(identifier);
    }

}
