import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main
{
    // Checking if lien is not correct
    public static void validation(int id)
    {
        System.out.println("Line " + id + " is not correct.");
    }
    // Getting file from src
    public static Scanner getFile()
    {
        try {
            String localDir = System.getProperty("user.dir");
            File file = new File(localDir + "/src/resources/Задача ВС Java Сбер.csv");
            Scanner scanner = new Scanner(file);
            return scanner;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static List<City> csvCityToModel(Scanner scanner)
    {
        City city;
        List<City> cities = new ArrayList<City>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tempCity = line.split(";");

            if(tempCity.length == 6)
            {
                city = new City(Integer.parseInt(tempCity[0]), tempCity[1],
                        tempCity[2], tempCity[3], Integer.parseInt(tempCity[4]), tempCity[5]);
                cities.add(city);
            }
            else
                validation(Integer.parseInt(tempCity[0]));
        }
        scanner.close();
        return cities;
    }
    public static void output(List<City> cities)
    {
        for(int i = 0; i < cities.size(); i++)
        {
            System.out.println(cities.get(i).toString());
        }
    }

    public static void main(String[] args)
    {
        output(csvCityToModel(getFile()));
    }
}