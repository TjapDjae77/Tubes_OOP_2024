package source.Characters.Plants;
import source.Sun.*;

public class SunflowerDemo {
        public static void main(String[] args) {
            Sunflower sunflower1 = new Sunflower(1, 1);
            Sunflower sunflower2 = new Sunflower(2, 2);
    
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0L;
    
            // Keep the program running for 15 seconds
            while (elapsedTime < 16000) { // 15 seconds
                try {
                    Thread.sleep(3000); // Sleep for 1 second intervals to check the elapsed time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                elapsedTime = (System.currentTimeMillis() - startTime);
            }
    
            System.out.println("Program has run for 15 seconds. Final sun count: " + Sun.getSun());
        }
    }
    

