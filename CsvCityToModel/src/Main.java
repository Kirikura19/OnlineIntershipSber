import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    // Checking if line is not correct
    public static void validation(int id)
    {
        System.out.println("Line "
                + id + " is not correct.");
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
    // Convert CSV to city list
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
    // Output
    public static void output(List<City> cities)
    {
        for(int i = 0; i < cities.size(); i++)
        {
            System.out.println(cities.get(i).toString());
        }
    }
    // Sorting by city name
    public static List<City> sortByName(List<City> cities)
    {
        List<City> citiesSortedName = cities.stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        return citiesSortedName;
    }
    // Sorting by Districts and names inside
    public static List<City> sortByDistrictAndName(List<City> cities)
    {
        List<City> citiesSortedDistinctName = cities.stream()
                .sorted(Comparator.comparing(City::getDistrict))
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        return citiesSortedDistinctName;
    }
    // Finding index of the city with max population
    public static int[] findCityMaxCitizens(List<City> cities)
    {
        /*
        int maxPopelation = cities.stream()
                .max(Comparator.comparing(City::getPopulation))
                .get().getPopulation(); */
        // По ТЗ

        City[] citiesArr = cities.toArray(City[]::new);
        int max = 0;
        int index = 0;
        for(int i = 0; i < citiesArr.length; i++)
        {
            if(citiesArr[i].getPopulation() > max)
            {
                max = citiesArr[i].getPopulation();
                index = i;
            }
        }
        return new int[]{max, index};
    }
    public static Map<String,Integer> calcCityInDistricts(List<City> cities)
    {
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for(int i = 0; i < cities.size(); i++)
        {
            if(!dictionary.containsKey(cities.get(i).getDistrict()))
                dictionary.put(cities.get(i).getDistrict(), 0);
            dictionary.put(cities.get(i).getDistrict(), dictionary.get(cities.get(i).getDistrict()) + 1);
        }
        return dictionary;

    }
    public static void main(String[] args)
    {
        //output(csvCityToModel(getFile()));
        //sortByName(csvCityToModel(getFile()));
        //sortByDistrictAndName(csvCityToModel(getFile()));
        //findCityMaxCitizens(csvCityToModel(getFile()));
        //calcCityInDistricts(csvCityToModel(getFile()));
        System.out.println(calcCityInDistricts(csvCityToModel(getFile())).toString());
    }
}