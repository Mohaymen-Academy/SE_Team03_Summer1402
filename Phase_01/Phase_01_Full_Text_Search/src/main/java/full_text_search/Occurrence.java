package full_text_search;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Occurrence {

    @NonNull
    @Setter(AccessLevel.NONE)
    private int documentIndex;

    @Setter(AccessLevel.NONE)
    private double score;

    private Boolean isInTitle;

    private int wordCount;

    public void calculateScore(int documentWordCount){
        score = (double) wordCount / documentWordCount;
        if(isInTitle){
            score = score * 1.1 + 0.05;
        }
    }

}
