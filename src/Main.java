import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlantRegister register = new PlantRegister();
        PlantRegister register1 = new PlantRegister();
        register.addPlantsFromFile("kvetiny.txt");
        try {
            register.addPlant(new Plant("Rose", "is prickly", LocalDate.of(2024, 10, 10), LocalDate.now(), 7));
        }catch (PlantException e){
            System.err.println(e.getMessage());
        }
        addTenTulips(register);
        register.remove(register.plantAtIndex(2));
        //System.out.println(register.getPlantRegister());
        register.writePlantsToFile("kvetiny.Write.txt");
        register1.addPlantsFromFile("kvetiny.Write.txt");
        register1.getPlantRegister().forEach(Plant-> System.out.println(Plant));
        System.out.println();
        register.getPlantRegister().sort(Comparator.comparing(Plant::getDateOfPlanting));
        register.getPlantRegister().forEach(Plant-> System.out.println(Plant));
        System.out.println();
        Collections.sort(register1.getPlantRegister());
        register1.getPlantRegister().forEach(Plant-> System.out.println(Plant));


    }
    public static void addTenTulips(PlantRegister register){
        for(int i = 1; i<= 10; ++i ){
            try{
                register.addPlant(new Plant("Tulip for sale " + i,"", LocalDate.now(), LocalDate.now(), 14));
            } catch (PlantException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}