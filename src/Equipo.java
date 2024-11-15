import java.util.*;

public class Equipo {
    
private String nom_equipo;

//Genereamos el constructor
public Equipo(String nom_equipo) {
    this.nom_equipo = nom_equipo;
}

//metodo para obtener el nombre del equipo
public String getNom_equipo() {
    return nom_equipo;
}

@Override
public String toString() {
    // TODO Auto-generated method stub
    return nom_equipo;
}

}
