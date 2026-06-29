interface Product {
    String getDescription();
}

class ConcreteProductA implements Product {
    @Override
    public String getDescription() {
        return "ConcreteProductA: Basic product implementation.";
    }
}

class ConcreteProductB implements Product {
    @Override
    public String getDescription() {
        return "ConcreteProductB: Alternative product implementation.";
    }
}

abstract class Creator {
    public abstract Product factoryMethod();

    public void describeProduct() {
        Product product = factoryMethod();
        System.out.println("Creator produced: " + product.getDescription());
    }
}

class ConcreteCreatorA extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}

class ConcreteCreatorB extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}

public class FactoryMethodExample {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        Creator creatorB = new ConcreteCreatorB();

        creatorA.describeProduct();
        creatorB.describeProduct();
    }
}
