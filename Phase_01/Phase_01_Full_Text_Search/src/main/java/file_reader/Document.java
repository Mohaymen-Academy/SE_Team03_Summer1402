package file_reader;

import lombok.*;

@RequiredArgsConstructor
@Getter
public class Document {

    @NonNull
    private String name;

    @NonNull
    private String context;

    @Setter
    private int wordCount;

}