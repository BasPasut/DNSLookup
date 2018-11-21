package application;

public class ToolFactory {

    public static ToolFactory instance = new ToolFactory();

    /**
     * For initialize
     */
    private ToolFactory(){}

    /**
     * Get the string of the unit
     * @return
     */
    public ToolType[] getToolTypes(){
        return ToolType.values();
    }

    /**
     * An instance method for singleton pattern
     * @return instance
     */
    public static ToolFactory getInstance() {
        return instance;
    }

}
