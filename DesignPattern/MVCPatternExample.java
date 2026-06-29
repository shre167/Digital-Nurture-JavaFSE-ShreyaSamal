public class MVCPatternExample {
    static class Student {
        private String name;
        private int id;
        private double grade;

        public Student(String name, int id, double grade) {
            this.name = name;
            this.id = id;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }
    }

    static class StudentView {
        public void displayStudentDetails(String name, int id, double grade) {
            System.out.println("Student: " + name + ", ID: " + id + ", Grade: " + grade);
        }
    }

    static class StudentController {
        private final Student model;
        private final StudentView view;

        public StudentController(Student model, StudentView view) {
            this.model = model;
            this.view = view;
        }

        public void setStudentName(String name) {
            model.setName(name);
        }

        public void setStudentGrade(double grade) {
            model.setGrade(grade);
        }

        public void updateView() {
            view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Alice", 101, 89.5);
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        controller.updateView();
        controller.setStudentGrade(92.0);
        controller.updateView();
    }
}
