public class Main {
    public static void main(String[] args) {
        InvertedIndex ii = new InvertedIndex(FileReader.ReadBooks("..\\Books"));
        ii.PrintOutput();
    }
}