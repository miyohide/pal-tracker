package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> repo = new HashMap<>();
    Long autoID = 0L;

    @Override
    public TimeEntry create(TimeEntry inTimeEntry) {
        Long id = ++autoID;
        TimeEntry newEntry = new TimeEntry(id, inTimeEntry.getProjectId(), inTimeEntry.getUserId(), inTimeEntry.getDate(), inTimeEntry.getHours());
        repo.put(id, newEntry);
        return newEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return repo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        Collection<TimeEntry> values = repo.values();
        return new ArrayList<>(values);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry existTimeEntry = repo.get(id);
        if (existTimeEntry == null) { return null; }
        existTimeEntry.setProjectId(timeEntry.getProjectId());
        existTimeEntry.setUserId(timeEntry.getUserId());
        existTimeEntry.setDate(timeEntry.getDate());
        existTimeEntry.setHours(timeEntry.getHours());
        return existTimeEntry;
    }

    @Override
    public void delete(long id) {
        repo.remove(id);
    }
}
