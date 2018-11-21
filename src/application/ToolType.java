package application;

public enum ToolType {
    TRACEROUTE("TraceRoute"),
    DNSLOOKUP("DnsLookup");
    //variable
    private String name ;

    /**
     * constructor of UnitType class
     * @param name
     */
    private ToolType(String name) {
        this.name = name ;
    }

    /**
     * return the name of the unit
     * @return name
     */
    public String getName(){
        return name ;
    }




}
