package ma.projet.beans;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Femme")
public class Femme extends Personne {
}
