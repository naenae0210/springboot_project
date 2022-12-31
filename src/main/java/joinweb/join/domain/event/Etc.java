package joinweb.join.domain.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
@Getter
@Setter
public class Etc extends Event {
}