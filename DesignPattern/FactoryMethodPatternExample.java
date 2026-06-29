public class FactoryMethodPatternExample {
    interface Document {
        String getType();
    }

    static class WordDocument implements Document {
        @Override
        public String getType() {
            return "Word Document";
        }
    }

    static class PdfDocument implements Document {
        @Override
        public String getType() {
            return "PDF Document";
        }
    }

    static class ExcelDocument implements Document {
        @Override
        public String getType() {
            return "Excel Document";
        }
    }

    abstract static class DocumentFactory {
        public abstract Document createDocument();
    }

    static class WordDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new WordDocument();
        }
    }

    static class PdfDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new PdfDocument();
        }
    }

    static class ExcelDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new ExcelDocument();
        }
    }

    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        Document word = wordFactory.createDocument();
        Document pdf = pdfFactory.createDocument();
        Document excel = excelFactory.createDocument();

        System.out.println("Created: " + word.getType());
        System.out.println("Created: " + pdf.getType());
        System.out.println("Created: " + excel.getType());
    }
}
