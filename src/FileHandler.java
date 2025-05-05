import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileHandler {
    private static final String FILE_NAME = "students.json";
    private static final Gson gson = new Gson();

    public static void saveToFile(ArrayList<Student> students) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(students, writer);
            System.out.println("Data saved to students.json");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static ArrayList<Student> loadFromFile() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, new TypeToken<ArrayList<Student>>(){}.getType());
        } catch (IOException e) {
            System.out.println("No data loaded (file may not exist yet).");
            return new ArrayList<>();
        }
    }
}
