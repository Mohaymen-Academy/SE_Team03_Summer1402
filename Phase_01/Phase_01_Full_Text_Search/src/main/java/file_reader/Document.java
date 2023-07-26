package file_reader;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Document {

    @NonNull
    @Setter(AccessLevel.NONE)
    private String name;

    @NonNull
    @Setter(AccessLevel.NONE)
    private String context;

    private int wordCount;

}