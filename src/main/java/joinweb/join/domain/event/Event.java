package joinweb.join.domain.event;

import joinweb.join.domain.Category;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public class Event {

    @Id @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    private String name;

    private int peopleNumber;

    private String address;

    @ManyToMany(mappedBy="events")
    private List<Category> categories = new ArrayList<Category>();


}
