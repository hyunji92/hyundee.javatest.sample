import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hyunji on 2016. 6. 29..
 */
public class Test {

    public static void main(String arg[]){


    }

    public static String processFiles(FunctionProcess F) throws IOException {
        //Function F  파라미터로 추가
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){

            //String one = processFiles((BufferedReader br) -> br.readLine());
            //return br.readLine(); 예제 (1)
            return F.process(br);
        }


    }
}
