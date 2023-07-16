public class Main {
    public static void main(String[] args) {
        String[][] books = {{"Ali", "The", "Hossein", "Amir"},
                {"ali", "The", "Sun", "Moon"},
                {"The", "Hossein", "Car"}};
        InvertedIndex ii = new InvertedIndex(books);
        FileReader.ReadBooks("G:\\CodeStar\\SE_Team03_Summer1402\\Phase_01\\Books");
    }
}