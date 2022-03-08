package lab5.client.utility;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import lab5.client.data.SpaceMarine;

/**
 * Class operates the collection itself.
 */
public class SpaceMarineCollection {
    private HashSet<SpaceMarine> spaceMarineSet;
    private LocalDateTime initializationTime;

    /**
     * @param set
     */
    public SpaceMarineCollection(HashSet<SpaceMarine> set){
        spaceMarineSet = set;
        initializationTime = LocalDateTime.now();
    }

    /**
     */
    public SpaceMarineCollection(){
        spaceMarineSet = new HashSet<>();
        initializationTime = LocalDateTime.now();
    }

    /**
     * Adds a new marine to collection.
     * @param element A Marine to add.
     */
    public boolean addElement(SpaceMarine element){
            return spaceMarineSet.add(element);
    }

    /**
     * Removes a Marine to collection.
     * @param element A Marine to remove.
     */
    public boolean removeElement(SpaceMarine element){
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
}
