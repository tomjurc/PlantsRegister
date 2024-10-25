
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

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

    public void addPlantsFromFile(String fileName, String delimiter){
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
            int lineNumber = 0;
            List<Plant> plants = new ArrayList<>();
            while(scanner.hasNextLine()){
                ++lineNumber;
                DateFormat dateFormat = DateFormat.YYYYMMDD;
                String row = scanner.nextLine();
                String[] parts = row.split(delimiter);
                if(!parts[3].matches(dateFormat.getDateFormat())|| !parts[4].matches(dateFormat.getDateFormat())){
                    plants.clear();
                    throw new PlantException("The date must be entered in format: yyyy-MM-dd, on line: " + lineNumber);
                }
                if(!parts[2].matches("[0-9]+")){
                    plants.clear();
                    throw new PlantException("Frequency of watering must be an integer you've entered: " + parts[2]
                                                + " on line: " + lineNumber);
                }
                plants.add(new Plant(parts[0], parts[1], LocalDate.parse(parts[4]), LocalDate.parse(parts[3]), Integer.parseInt(parts[2])));
            }
            this.plantRegister.addAll(plants);
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void writePlantsToFile(String filename, String delimiter) {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            plantRegister.forEach(plant -> writer.println(plant.toFileString(delimiter)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
