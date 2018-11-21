package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TraceRoute {

    private String name ;
    private double value ;

    /**
     * Constructor of Temperature initialize with value of unit.
     * @param value
     */
    private TraceRoute(String name, double value){
        this.name = name ;
        this.value = value ;
    }

    public String trackOS(){
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            return "tracert";
        }
        else if (os.contains("osx")){
            return "trace";
        }
        return null;
    }

    public List<String> traceRoute(String host) {
        List<String> output = new ArrayList<>();
        BufferedReader in;

        try {
            Runtime r = Runtime.getRuntime();
            String os = trackOS();
            Process p = r.exec(os+" "+host);

            in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;

            if (p == null)
                output.add("Cannot connect");

            while ((line = in.readLine()) != null) {

                output.add(line);
            }

        } catch (IOException e) {

            System.out.println(e.toString());

        }
        return output;
    }
}
