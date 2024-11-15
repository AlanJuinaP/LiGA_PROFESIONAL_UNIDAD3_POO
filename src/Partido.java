public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private String resultado;

    //Generamos el constructor para generar un partido
    //entre dos equipos
    public Partido(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    //registrar resultado
    public void registrar_Resultado(String resultado){
        this.resultado = resultado;
    }

    //Generamos getters y setters de equipo1 y equipo2
    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    //metodo para representar el partido como una cadena de texto
    @Override
    public String toString() {
       // return "Partido [equipo1=" + equipo1 + ", equipo2=" + equipo2 + "]";
       return equipo1 + " vs " + equipo2 + (resultado != null ? " | Resultado" + resultado : "");
    }  
    
}
