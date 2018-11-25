package application;
/**
 * and enum class. For spliting tools.
 * @author theeruthborisuth
 *
 */
public enum ToolType {
    TRACEROUTE("TraceRoute"),
    DNSLOOKUP("DnsLookup");
    //variable
    private String name ;

    /**
     * constructor of ToolType class
     * @param name
     */
    private ToolType(String name) {
        this.name = name ;
    }

    /**
     * return the name of the Tool
     * @return name
     */
    public String getName(){
        return name ;
    }




}
