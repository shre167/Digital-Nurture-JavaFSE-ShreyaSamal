public class BuilderPatternExample {
    static class Computer {
        private final String cpu;
        private final int ram;
        private final int storage;
        private final String gpu;
        private final boolean hasWifi;

        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
            this.gpu = builder.gpu;
            this.hasWifi = builder.hasWifi;
        }

        @Override
        public String toString() {
            return String.format("Computer{cpu=%s, ram=%dGB, storage=%dGB, gpu=%s, wifi=%b}",
                    cpu, ram, storage, gpu, hasWifi);
        }

        static class Builder {
            private String cpu;
            private int ram;
            private int storage;
            private String gpu;
            private boolean hasWifi;

            public Builder setCpu(String cpu) {
                this.cpu = cpu;
                return this;
            }

            public Builder setRam(int ram) {
                this.ram = ram;
                return this;
            }

            public Builder setStorage(int storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGpu(String gpu) {
                this.gpu = gpu;
                return this;
            }

            public Builder setWifi(boolean hasWifi) {
                this.hasWifi = hasWifi;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static void main(String[] args) {
        Computer officeComputer = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam(16)
                .setStorage(512)
                .setWifi(true)
                .build();

        Computer gamingComputer = new Computer.Builder()
                .setCpu("AMD Ryzen 7")
                .setRam(32)
                .setStorage(1024)
                .setGpu("NVIDIA RTX 4070")
                .setWifi(true)
                .build();

        System.out.println("Office configuration: " + officeComputer);
        System.out.println("Gaming configuration: " + gamingComputer);
    }
}
