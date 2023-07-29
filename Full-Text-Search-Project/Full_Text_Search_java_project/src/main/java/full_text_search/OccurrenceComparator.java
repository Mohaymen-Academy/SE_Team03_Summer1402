package full_text_search;

import java.util.Comparator;

public class OccurrenceComparator implements Comparator<Occurrence> {

    public int compare(Occurrence o1, Occurrence o2)
    {
        return Double.compare(o2.getScore(), o1.getScore());
    }

}
