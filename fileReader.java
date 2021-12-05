import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class fileReader{

  public static String readTextFile(String destination, int line){
    String result = "";
    line -= 1;

    try (Stream<String> lines = Files.lines(Paths.get(destination))){
    result = lines.skip(line).findFirst().get();
    }catch (IOException e) {
      System.err.println("Caught IOException: " +  e.getMessage());
    }

    return result;
  }

    public static long getFileLines(String destination) {

      Path path = Paths.get(destination);

      long lines = 0;
      try {

          lines = Files.lines(path).count();

      } catch (IOException e) {
          e.printStackTrace();
      }

      return lines;

  }

  public static String getRandomLine(String destination){
    String result = "";
    int temp = (int) getFileLines(destination);
    int temp2 = generator.getRandomNumber(1, temp);
    result += readTextFile(destination, temp2);

    return result;
  }

}