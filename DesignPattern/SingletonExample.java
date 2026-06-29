public class SingletonExample {
    private static SingletonExample instance;
    private String value;

    private SingletonExample() {
        this.value = "Default singleton value";
    }

    public static synchronized SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        SingletonExample firstReference = SingletonExample.getInstance();
        SingletonExample secondReference = SingletonExample.getInstance();

        firstReference.setValue("Shared singleton state");

        System.out.println("First reference value: " + firstReference.getValue());
        System.out.println("Second reference value: " + secondReference.getValue());
        System.out.println("Same instance? " + (firstReference == secondReference));
    }
}
