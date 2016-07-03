import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hyunji on 2016. 7. 3..
 */
public class AroundPatternTest {
    private String str;

    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {

            return br.readLine() + br.readLine();
        }

    }

    public interface BufferReaderProcessor{

        public String process(BufferedReader br) throws IOException;


    }
    private String str2;

    public static String processFile(BufferReaderProcessor bp) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return bp.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        String process_str = processFile();

        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine() + br.readLine());
        System.out.println(result);
    }
}
