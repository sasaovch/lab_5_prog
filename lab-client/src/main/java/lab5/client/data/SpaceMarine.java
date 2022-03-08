package lab5.client.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import lab5.client.exceptions.IncorrectData;

/**
 * Main character. Is stored in the collection by health.
 */
public class SpaceMarine implements Comparable<SpaceMarine> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDateTime; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer health; //Поле не может быть null, Значение поля должно быть больше 0
    private Integer heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3
    private Boolean loyal; //Поле может быть null
    private AstartesCategory category; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
    private static Long count = (long) 0;

    public SpaceMarine(String name, Long id, Coordinates coordinates, LocalDateTime time, Integer health, Integer heartCount, Boolean loyal, AstartesCategory category, Chapter chapter) throws IncorrectData {
        setID(id);
        setName(name);
        setCoordinates(coordinates);
        setTime(time);
        setHealth(health);
        setHeartCount(heartCount);
        setLoyal(loyal);
        setCategory(category);
        setChapter(chapter);
    }

    public SpaceMarine() {}

    /**
     * @return Category of the Marine.
     */
    public AstartesCategory getCategory() {
        return category;
    }

    /**
     * @return Chapter of the Marine.
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * @return Coordinates of the Marine.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return creation time of the Marine.
     */
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * @return Heart Count of the Marine.
     */
    public Integer getHeartCount() {
        return heartCount;
    }

    /**
     * @return Name of the Marine.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Health of the Marine.
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return ID of the Marine.
     */
    public Long getID() {
        return id;
    }

    /**
     * @return Loyal of the Marine.
     */
    public Boolean getLoyal() {
        return loyal;
    }

    /**
     * Set name of Marine.
     * @param name A name of Marine.
     * @throws IncorrectData
     */
    public void setName(String name) throws IncorrectData {
        if ((name == null) || (name.trim().equals(""))) {
            throw new IncorrectData();
        }
        this.name = name;
    }

    /**
     * Set id of Marine.
     * @param id ID of Marine.
     * @throws IncorrectData
     */
    public void setID(Long id) throws IncorrectData {
        if ((id == null) || (id <= 0)) {
            throw new IncorrectData();
        }
        if (id > count) {
            count = id;
        }
        this.id = id;
    }

    /**
     * Set Coordinates of Marine.
     * @param cor Coordinates of Marine.
     * @throws IncorrectData
     */
    public void setCoordinates(Coordinates cor) throws IncorrectData {
        if (cor == null) {
            throw new IncorrectData();
        }
        coordinates = cor;
    }

    /**
     * Set creation time of Marine.
     * @param time Time of Marine.
     * @throws IncorrectData
     */
    public void setTime(LocalDateTime time) throws IncorrectData {
        if (time == null) {
            throw new IncorrectData();
        }
        creationDateTime = time;
    }

    /**
     * Set Health of Marine.
     * @param health Health of Marine.
     * @throws IncorrectData
     */
    public void setHealth(Integer health) throws IncorrectData{
        if ((health == null) || (health <= 0)) {
            throw new IncorrectData();
        }
        this.health = health;
    }

    /**
     * Set Heart Count of Marine.
     * @param heartCount Heart Count of Marine.
     * @throws IncorrectData
     */
    public void setHeartCount(Integer heartCount) throws IncorrectData {
        if (!((heartCount <= 3) && (1 <= heartCount))) {
            throw new IncorrectData();
        }
        this.heartCount = heartCount;
    }

    /**
     * Set loyal of Marine.
     * @param loyal Loyal of Marine.
     */
    public void setLoyal(Boolean loyal) {
        this.loyal = loyal;
    }

    /**
     * Set Category of Marine.
     * @param cat Category of Marine.
     * @throws IncorrectData
     */
    public void setCategory(AstartesCategory cat) throws IncorrectData {
        if (cat == null) {
            throw new IncorrectData();
        }
        category = cat;
    }

    /**
     * Set Chapter of Marine.
     * @param chapter Chapter of Marine.
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    /**
     * Generate ID of Marine.
     * @return ID of Marine.
     */
    public static Long generateNextId() {
        count += 1;
        return count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, health, heartCount, loyal, category, chapter);
    }

    @Override
    public String toString() {
        return "------------" + "\nName: " + name + "\nId: " + id + "\nHealth: " + health + "\nHeartCount: " +
                heartCount + "\nLoyal: "
    + loyal + "\nInitialization time: " + creationDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "\n" + "Chapter:\n" + chapter + "\n" + coordinates + "\nCategory: " + category;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        int different = health - o.getHealth();
        if (different == 0) {
            long dif = id - o.getID();
            if (dif < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        return different;
    }
}