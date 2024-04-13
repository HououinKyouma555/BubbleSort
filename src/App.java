import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int option = 0;

        System.out.println();
        System.out.println("Hi! Welcome to the best bubble sort program ever! Please choose the option you'd prefer to begin with");
        System.out.println("Enter 1 to generate a random array of your desired size");
        System.out.println("Enter 2 to sort an existing file of numbers");

        option = scanner.nextInt();
        
        if (option == 1){
            System.out.println("Input an array size between 1 - 100");
            int arraySize = 0;
            
            while (isValid == false) {
                System.out.print("Enter a number: ");
                if (scanner.hasNextInt()) {
                    arraySize = scanner.nextInt();
                    if (arraySize > 0 && arraySize <= 100){
                        isValid = true;
                    }
                } else {
                    System.out.println("Please enter a number between 1-100.");
                    scanner.next(); 
                }
            }

            int array[] = createRandomArray(arraySize);
            System.out.println("Before sorting");
            printArray(array);
            bubbleSort(array);
            System.out.println("After sorting");
            printArray(array);
            writeArrayToFile(array, "output.txt");
        }
        else if (option == 2){
            System.out.println("Please enter filepath of text file:");
            String filePath = scanner.next();

            int arrayFromFile[] = readFileToArray(filePath);
            System.out.println("Before sorting");
            printArray(arrayFromFile);
            bubbleSort(arrayFromFile);
            System.out.println("After sorting");
            printArray(arrayFromFile);
            writeArrayToFile(arrayFromFile, "output.txt");
        }
        else {
            System.out.println("You didn't enter 1 or 2 dingaling");
        }
        scanner.close();
    }

    public static int[] createRandomArray(int arraySize){
        Random random = new Random();
        int[] array = new int[arraySize];
        

        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt(100);
        }

        return array;
    }

    public static void bubbleSort(int[] array){

        for (int i = 0; i < array.length; i++){
            for (int j = 1; j < (array.length - i); j++){
                if (array[j-1] > array[j]){
                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static int[] readFileToArray(String filename){
        int[] array = new int[100];
        int i = 0;

        File file = new File(filename);
        
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                array[i] = Integer.parseInt(line);
                i++;
            }
            
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }

    public static void writeArrayToFile(int[] array, String filename){
        String filePath = filename;
        
        try {
            FileWriter writer = new FileWriter(filePath);
            
            for (int i = 0; i < array.length; i++){
                if (array[i] > 0){
                    writer.write(Integer.toString(array[i]));
                    writer.write("\n");
                }
            }
            
            writer.close();
            
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++){
            if (array[i] > 0){
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }




}
