package mapper;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    private String userName;
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    public User() {
        super();
    }

    public User(String userName, String firstName) {
        this.userName = userName;
        this.firstName = firstName;
    }
}
