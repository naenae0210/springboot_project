package joinweb.join.domain.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("H")
@Getter
@Setter
public class Hobby extends Event {
}

