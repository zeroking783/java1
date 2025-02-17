package data;
import utility.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class City extends Element implements Validate{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float area; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    private float metersAboveSeaLevel;
    private LocalDate establishmentDate;
    private Government government; //Поле не может быть null
    private StandardOfLiving standardOfLiving; //Поле не может быть null
    private Human governor; //Поле может быть null


    public City(long id, String name, Coordinates coordinates, LocalDate creationDate, Float area, Integer population, float metersAboveSeaLevel, LocalDate establishmentDate, Government government, StandardOfLiving standardOfLiving, Human governor){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.establishmentDate = establishmentDate;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }
    public City(long id, String name, Coordinates coordinates, Float area, Integer population, float metersAboveSeaLevel, LocalDate establishmentDate, Government government, StandardOfLiving standardOfLiving, Human governor){
        this(id, name, coordinates, LocalDate.now(), area, population, metersAboveSeaLevel, establishmentDate, government, standardOfLiving, governor);
    }
    @Override
    public boolean validate() {
        if (id <= 0) return false;
        if (area <= 0 || area == null) return false;
        if (population <= 0 || population == null) return false;
        if (government == null) return false;
        if (standardOfLiving == null) return false;
        if (name == null || name.isEmpty()) return false;
        if (creationDate == null) return false;
        if (coordinates == null || !coordinates.validate()) return false;
        return true;
    }
    @Override
    public String toString() {
        return "city{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +  "\"coordinates\": \"" + coordinates + "\", " +
                "\"creationDate\": \"" + creationDate.format(DateTimeFormatter.ISO_DATE) + "\", " +
                "\"area\": \"" + area + "\", " +  "\"population\": \"" + population + "\", " +
                "\"metersAboveSeaLevel\": \"" + metersAboveSeaLevel + "\", " +
                "\"establishmentDate\": \"" + establishmentDate.format(DateTimeFormatter.ISO_DATE) + "\", " +
                "\"government\": \"" + government + "\", " +
                "\"standardOfLiving\": \"" + standardOfLiving + "\", " +
                "\"governor\": " + (governor == null ? "null" : "\""+governor+"\"") + "}";
    }

    @Override
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Float getArea() {
        return area;
    }

    public Integer getPopulation() {
        return population;
    }

    public float getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public Government getGovernment() {
        return government;
    }
    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }
    public Human getGovernor() {
        return governor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City that = (City) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, establishmentDate, government, standardOfLiving, governor);
    }

    @Override
    public int compareTo(Element element) {
        return (int)(this.id - element.getId());
    }
}
