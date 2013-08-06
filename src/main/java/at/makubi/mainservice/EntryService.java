package at.makubi.mainservice;

import at.makubi.entities.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
interface EntryService {

    Collection<Entry> getAllEntries();

    Collection<Entry> getAllEntriesForCategory(String category);

    void saveZone(Zone zone);

    void saveCategories(Collection<Category> categories);

    void saveTranslations(Collection<Translation> translations);

    void saveEntries(Collection<Entry> entries);

    void saveIdentifier(Identifier identifier);
}
