import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;

public class MongoHandler {
	private static final String CONNECTION_STRING = "mongodb://localhost:27017";

    private static final String DB_NAME = "studentDB";
    private static final String COLLECTION_NAME = "students";

    private static MongoCollection<Document> getCollection() {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        MongoDatabase database = client.getDatabase(DB_NAME);
        return database.getCollection(COLLECTION_NAME);
    }

    public static void saveToMongo(ArrayList<Student> studentList) {
        try {
            MongoCollection<Document> collection = getCollection();

            for (Student student : studentList) {
                Document doc = new Document("id", student.getId())
                                   .append("name", student.getName())
                                   .append("email", student.getEmail())
                                   .append("course", student.getCourse());
                collection.insertOne(doc);
            }

            System.out.println("Students saved to MongoDB.");
        } catch (Exception e) {
            System.out.println("Failed to save students to MongoDB.");
            e.printStackTrace();
        }
    }


    public static ArrayList<Student> loadFromMongo() {
        MongoCollection<Document> collection = getCollection();
        ArrayList<Student> studentList = new ArrayList<>();

        for (Document doc : collection.find()) {
            String id = doc.getString("id");
            String name = doc.getString("name");
            String email = doc.getString("email");
            String course = doc.getString("course");
            studentList.add(new Student(id, name, email, course));
        }

        System.out.println(" Students loaded from MongoDB.");
        return studentList;
    }
    
    

}
