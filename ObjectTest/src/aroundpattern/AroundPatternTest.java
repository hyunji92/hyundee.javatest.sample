package aroundpattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hyunji on 2016. 7. 3..
 */
public class AroundPatternTest {
    private String str;

    public static String processFile() throws IOException {
        try (BufferedReader br =  new BufferedReader(new InputStreamReader(System.in))){
            return br.readLine();
        }

    }

    public interface BufferReaderProcessor{

        public String process(BufferedReader br) throws IOException;


    }

    public static String inPut(BufferReaderProcessor bufferReaderProcessor) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            return bufferReaderProcessor.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        //String process_str = processFile();
        String testStr = "test";

        String result = inPut( br  ->"첫번째 줄 :"+ br.readLine()
                +"\n첫번째 줄 :"+ br.readLine()
                +"\n세번째줄 : "+ br.readLine());
        System.out.println(result);
    }
}
