package miccyEEJava.javaMickProject.db;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "c_items")
public class CorzItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;
    @Column(name = "photo", columnDefinition ="TEXT")
    private String photo;
    @Column(name = "desciption")
    private String description;

}
