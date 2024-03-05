import java.io.*;
import java.util.*;

public class program {
    private PriorityQueue<Toy> toys;

    public program(String[] toyData) {
        toys = new PriorityQueue<>();
        for (String data : toyData) {
            String[] fields = data.split(",");
            if (fields.length == 3) {
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int frequency = Integer.parseInt(fields[2]);
                Toy toy = new Toy(id, name, frequency);
                toys.add(toy);
            }
        }
    }

    public void simulateAndGet() {
        try {
            FileWriter writer = new FileWriter("text.txt");
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt(100);
                Toy selectedToy = null;
                int cumulativeWeight = 0;

                for (Toy toy : toys) {
                    cumulativeWeight += toy.getFrequency();
                    if (randomNumber < cumulativeWeight) {
                        selectedToy = toy;
                        break;
                    }
                }

                String result;
                if (selectedToy != null) {
                    result = String.valueOf(selectedToy.getId());
                } else {
                    result = "1";
                }

                writer.write(result + "\n");
            }

            writer.close();
            System.out.println("Результаты записаны в файл text.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Toy implements Comparable<Toy> {
        private int id;
        private String name;
        private int frequency;

        public Toy(int id, String name, int frequency) {
            this.id = id;
            this.name = name;
            this.frequency = frequency;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getFrequency() {
            return frequency;
        }

        
        public int compareTo(Toy other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    public static void main(String[] args) {
        String[] toyData = {"1,Кукла,20", "2,Машинка,20", "3,Кубики,60"};

        program toyStore = new program(toyData);
        toyStore.simulateAndGet();
    }
}
