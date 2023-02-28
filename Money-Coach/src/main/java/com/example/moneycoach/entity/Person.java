package com.example.moneycoach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "person"/*,schema = "heroku_7f60c85dc94d362"*/)
@Data
@NoArgsConstructor
@RequiredArgsConstructor()
public class Person {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;
    //@Column
    @NotEmpty (message = "The User must have a name")
    @Getter @Setter @NonNull private String name;
    //@Column
    @NotEmpty(message = "The User must have a lastname")
    @Getter @Setter @NonNull  private String lastname;
    ///@Column
    @NotEmpty(message = "The User must have a username")
    //@UniqueElements (message = "The username has alredy in use")
    @Getter @Setter  @NonNull  private String username;
}
