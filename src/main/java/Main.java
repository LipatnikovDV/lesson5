import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Main {
    private static final String REGEX = ";";

    public static void main(String[] args) throws IOException {
        File file = new File("new.csv");
        if (file.exists()) {
            file.createNewFile();
        }
        List<String> stringList = new ArrayList<>();
        stringList.add("Длинна; Ширина; Высота;");
        stringList.add("12; 64; 20;");
        stringList.add("10; 15; 5;");
        addStringToFile(stringList, file);

        stringList = readFileToStrings(file);
        System.out.println(getAppData(stringList));
    }
    public static AppData getAppData (List<String> stringList) {
        String headersString = stringList.get(0);
        String[] headers = headersString.split(REGEX);
        int[][] data = new int[headers.length][stringList.size() - 1];
        List<String[]> listOfArrays = new ArrayList<>(stringList.size() - 1);
        for (int i = 1; i < stringList.size(); i++) {
            String s = stringList.get(i).trim();
            String[] values = s.split(REGEX);
            listOfArrays.add(values);
        }
        for (int i = 0; i < listOfArrays.size(); i++) {
            String[] values = listOfArrays.get(i);
            for (int j = 0; j < values.length; j++) {
                data[j][i] = Integer.parseInt(values[j].trim());
            }
        }
        return new AppData(headers, data);
    }
    public static void addStringToFile(List<String> stringList, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String s : stringList) {
                writer.write(s);
                writer.newLine();
            }
        }
    }
    public static List<String> readFileToStrings(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            List<String> stringList = new ArrayList<>();
            while ((s = reader.readLine()) != null) {
                stringList.add(s);
            }
            return stringList;
        }
    }
}
