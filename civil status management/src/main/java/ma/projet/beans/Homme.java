package ma.projet.beans;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Homme")
public class Homme extends Personne {
}
