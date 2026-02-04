package org.example.yesodkimchijjimback.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String googleToken;

    @OneToMany (
                mappedBy = "User",
                fetch = FetchType.LAZY,
                cascade = {CascadeType.ALL}
    )

    private List<User> user = new ArrayList<>();
}
