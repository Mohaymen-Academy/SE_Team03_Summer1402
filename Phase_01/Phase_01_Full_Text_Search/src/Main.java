public class Main {
    public static void main(String[] args) {
        String[][] books = {{"Ali", "The", "Hossein", "Amir"},
                {"ali", "The", "Sun", "Moon"},
                {"The", "Hossein", "Car"}};
        InvertedIndex ii = new InvertedIndex(books);
        ii.PrintOutput();
    }
}