import java.time.LocalDate;

public class Plant implements Comparable<Plant>{
    private String name;
    private String notes;
    private LocalDate dateOfPlanting;
    private LocalDate dateOfWatering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate dateOfPlanting, LocalDate dateOfWatering, int frequencyOfWatering)throws PlantException{
        if(frequencyOfWatering<=0){
            throw new PlantException("Frequence of watering must be bigger than 0, you entered: " + frequencyOfWatering);
        }
        if(dateOfPlanting.isAfter(dateOfWatering)){
            throw new PlantException("Date of watering cant be before date of planting.");
        }
        this.name = name;
        this.notes = notes;
        this.dateOfPlanting = dateOfPlanting;
        this.dateOfWatering = dateOfWatering;
        this.frequencyOfWatering = frequencyOfWatering;
    }
    public Plant(String name, int frequencyOfWatering) throws PlantException{
        this(name,"",LocalDate.now(),LocalDate.now(),frequencyOfWatering);
    }
    public Plant(String name) throws PlantException{
        this(name,"",LocalDate.now(),LocalDate.now(),7);
    }

    public String getWateringInfo(){
        return name + ", date of last watering: " + dateOfWatering + ", date of next watering: " + dateOfWatering.plusDays(frequencyOfWatering)+ ".";
    }

    public void doWateringNow(){
        this.dateOfWatering = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getDateOfPlanting() {
        return dateOfPlanting;
    }

    public LocalDate getDateOfWatering() {
        return dateOfWatering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }
    @Override
    public String toString(){
        return this.name +"\t" + this.notes + "\t" + this.frequencyOfWatering + "\t" + this.dateOfWatering + "\t" + this.dateOfPlanting;
    }
    @Override
    public int compareTo(Plant otherPlant){
        return name.compareTo(otherPlant.name);
    }
}
