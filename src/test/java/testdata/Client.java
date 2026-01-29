package testdata;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Builder(toBuilder=true)
@ToString
public class Client {
    private final String firstName;
    private final String familyName;
    private final String address;
    private final String phoneNumber;
}
