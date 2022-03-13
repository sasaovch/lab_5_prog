package lab5.client.utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lab5.client.data.SpaceMarine;
import lab5.client.exceptions.IncorrectData;

/**
 * Class operates the collection itself.
 */
public class SpaceMarineCollection {
    private HashSet<SpaceMarine> spaceMarineSet;
    private LocalDateTime initializationTime;
    private TreeSet<Long> usedID;

    /**
     * @param set
     */
    public SpaceMarineCollection(HashSet<SpaceMarine> set){
        spaceMarineSet = set;
        initializationTime = LocalDateTime.now();
        usedID = new TreeSet<>();
    }

    /**
     */
    public SpaceMarineCollection(){
        spaceMarineSet = new HashSet<>();
        initializationTime = LocalDateTime.now();
        usedID = new TreeSet<>();
    }

    /**
     * Adds a new marine to collection.
     * @param element A Marine to add.
     * @throws IncorrectData
     */
    public boolean addElement(SpaceMarine element) {
        if (Objects.equals(element.getID(), null)) {
            if (usedID.isEmpty()) {
                try {
                    element.setID(1L);
                    usedID.add(1L);
                } catch (IncorrectData e) {
                    e.printStackTrace(); // never throw
                }
            } else {
                try {
                    element.setID(usedID.last() + 1);
                    usedID.add(usedID.last() + 1);
                } catch (IncorrectData e) {
                    e.printStackTrace(); // never throw
                }
            }
        } else if (usedID.contains(element.getID())) {
            try {
                element.setID(usedID.last() + 1);
                usedID.add(usedID.last() + 1);
            } catch (IncorrectData e) {
                e.printStackTrace(); // never throw
            }
        } else {
            usedID.add(element.getID());
        }
        return spaceMarineSet.add(element);
    }

    /**
     * Removes a Marine to collection.
     * @param element A Marine to remove.
     */
    public boolean removeElement(SpaceMarine element){
        usedID.remove(element.getID());
        return spaceMarineSet.remove(element);
    }

    /**
     * @return Initialization time.
     */
    public LocalDateTime getTime(){
        return initializationTime;
    }

    /**
     * @return Size of Collection.
     */
    public int getSize(){
        return spaceMarineSet.size();
    }

    /**
     * Clears the collection.
     */
    public void clearCollection(){
        usedID.clear();
        spaceMarineSet.clear();
    }

    /**
     * @return Marine Collection.
     */
    public HashSet<SpaceMarine> getCollection(){
        return spaceMarineSet;
    }

    /**
     * @return sorted list of Marine Collection.
     */
    public List<SpaceMarine> sortCollection(){
        List<SpaceMarine> list = new ArrayList<SpaceMarine>(getCollection());
        Collections.sort(list);
        return list;
    }

    public Long getLastId(){
        return usedID.last();
    }

    public boolean addIfMin(SpaceMarine addSpaceMarine) {
        SpaceMarine minSpaceMarine = null;
        if (spaceMarineSet.size() == 0) {
            addElement(addSpaceMarine);
            return true;
        }
        for (SpaceMarine thatSpaceMarine : spaceMarineSet) {
            if (minSpaceMarine == null) {
                minSpaceMarine = thatSpaceMarine;
                continue;
            }
            if (thatSpaceMarine.compareTo(minSpaceMarine) < 0) {
                minSpaceMarine = thatSpaceMarine;
            }
        }
        if (addSpaceMarine.compareTo(minSpaceMarine) < 0) {
            addElement(addSpaceMarine);
            return true;
        } else {
            return false;
        }
    }

    public <R> int countBySomeThing(Function<SpaceMarine, R> getter, R value) {
        int counter = 0;
        for (SpaceMarine thatSpaceMarine : spaceMarineSet) {
            if (Objects.equals(getter.apply(thatSpaceMarine), value)) {
                counter += 1;
            }
        }
        return counter;
    }

    public <R> HashMap<String, Integer> groupCountingByField(Function<SpaceMarine, R> getter) {
        HashMap<String, Integer> outputMap = new HashMap<>();
        for (SpaceMarine spaceMarine : spaceMarineSet) {
            outputMap.compute(getter.apply(spaceMarine).toString(), (key, val) -> (Objects.equals(val, null) ? 1: val + 1));
        }
        return outputMap;
    }

    public boolean removeIf(Predicate<SpaceMarine> condition) {
        Set<SpaceMarine> removeSet = spaceMarineSet.stream().filter(condition).collect(Collectors.toSet());
        if (removeSet.isEmpty()) {
            return false;
        }
        spaceMarineSet.removeAll(removeSet);
        usedID.clear();
        usedID.addAll(removeSet.stream().map(SpaceMarine::getID).collect(Collectors.toSet()));
        return true;
    }

    public SpaceMarine findByID(Long id) {
        for (SpaceMarine spaceMarine : spaceMarineSet) {
            if (id.equals(spaceMarine.getID())) {
                return spaceMarine;
            }
        }
        return null;
    }

    public boolean updateSpaceMarine(SpaceMarine changeMarine, SpaceMarine newMarine) {
        return removeElement(changeMarine) && addElement(newMarine); 
    }
}