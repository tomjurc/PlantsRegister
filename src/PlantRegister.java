import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.nio.file.Paths;

public class PlantRegister {
    private List<Plant> plantRegister;

    public PlantRegister(){
        plantRegister = new ArrayList<>();
    }

    public void addPlant(Plant plant){
        plantRegister.add(plant);
    }

    public Plant plantAtIndex(int i){
        return plantRegister.get(i);
    }

    public void remove(Plant plant){
        plantRegister.remove(plant);
    }

    public List<Plant> getPlantRegister(){
        return plantRegister;
    }

    public List<Plant> toBeWatered(){
        return plantRegister.stream()
                .filter(plant -> plant.getDateOfWatering().isBefore(LocalDate.now().minusDays(plant.getFrequencyOfWatering())))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public void sortByDateOfWatering(){
        this.plantRegister.sort(Comparator.comparing(Plant::getDateOfWatering));
    }
    /*public String toString(){
        StringBuilder plants = new StringBuilder();
        plantRegister.stream().forEach(plant -> plants.append(plant+ " ,"));
        return plants.toString();
    }*/
    public void addPlantsFromFile(String fileName){
        try (Scanner scanner = new Scanner(Paths.get(fileName))){
            while(scanner.hasNextLine()){
                String dateFormat = "([20]{2}[0-9]{2})"+
                        "([-]{1})"+
                        "([0]{1}[1-9]{1}|[1]{1}[0-2]{1}|[1-9]{1})"+
                        "([-]{1})"+
                        "([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-9]{1}|[3]{1}[0-1]{1})";
                String row = scanner.nextLine();
                String[] parts = row.split("\t");
                if(!parts[3].matches(dateFormat)|| !parts[4].matches(dateFormat)){
                    throw new PlantException("The date must be entered in format: yyyy-MM-dd, youve entered: "+ parts[3]);
                }
                if(!parts[2].matches("[0-9]+")){
                    throw new PlantException("Frequence of watering must be an integer youve entered: "+ parts[2]);
                }
                this.plantRegister.add(new Plant(parts[0], parts[1], LocalDate.parse(parts[4]), LocalDate.parse(parts[3]), Integer.valueOf(parts[2])));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
    public void writePlantsToFile(String filename) {
        try(PrintWriter writer = new PrintWriter(filename)) {
            plantRegister.stream().forEach(plant -> writer.println(plant));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
