import java.util.*;

public class Liga_de_futbol {
    //lista de equipos
    private ArrayList<Equipo> equipos = new ArrayList<>();
    private ArrayList<Partido> registro_Result = new ArrayList<>();
    private Equipo Campeon;
    //controlara la entapa jugada
    private int etapa_Actual = 0;

    //metodo para ingresar los equipos
    public void Agregar_Equipos(Equipo equipo){
        equipos.add(equipo);
        System.out.println("Equipo Agregado: " + equipo.getNom_equipo());
        
    }
    //metodo para ver los equipos registrados
    public void Ver_Equipos(){
        System.out.println("\nEquipos Registrados: ");
        for(Equipo equipo : equipos){
            System.out.println("- " + equipo.getNom_equipo());
        }
    }

   //Metodo iniciar torneo
   public void Play_Torneo() throws Cantidad_Equipos_Invalido_Exception{
    if (equipos.size() != 16) {
        throw new Cantidad_Equipos_Invalido_Exception("Debe haber 16 equipos, para inicar el torneo");
    }
    System.out.println("\nEl torneo esta por iniciar");

    ArrayList<Equipo> equipos_Actuales = new ArrayList<>(equipos);

    //Octavos de final
    equipos_Actuales = realizar_etapa("Octavos de final", equipos_Actuales);
    //Cuartos de final
    equipos_Actuales = realizar_etapa("Cuartos de final", equipos_Actuales);
    //Seminidal 
    equipos_Actuales = realizar_etapa("Semifinales", equipos_Actuales);
    //Final
    equipos_Actuales = realizar_etapa("Final", equipos_Actuales);
    
    System.out.println("\n¡El torneo ha terminado!");
    //Anadimos linea para dar un reconocimiento al ganador
    System.out.println("¡El campeon del torneo es: " + Campeon.getNom_equipo() + "! FELICIDADES CRACKS");
   }

   //Metodo para iniciar una etapa del torneo
   private ArrayList<Equipo> realizar_etapa(String etapa, ArrayList<Equipo> equipos_Actuales){
    System.out.println("\n" + etapa + ":");

    ArrayList<Equipo> ganadores = new ArrayList<>();
    ArrayList<Partido> partidos = sorteoRecursivo(equipos_Actuales);

    Random random = new Random();

    for(Partido partido : partidos){
        int marcador1 = random.nextInt(5);
        int marcador2 = random.nextInt(5);

        String result = marcador1 + "-" + marcador2;
        partido.registrar_Resultado(result);

        System.out.println(partido);
        registro_Result.add(partido);

        if (marcador1 > marcador2) {
            ganadores.add(partido.getEquipo1());
        }else{
            ganadores.add(partido.getEquipo2());
        }
    }
    return ganadores;
   }

 //mettodo para determinar el campeon, se reutiliza el codigo casi completo del meodo realizar_etapa
 private void realizar_Etapa_final(String etapa, ArrayList<Equipo> equipos_Actuales){
    System.out.println("\n" + etapa + ":");
    
    if (equipos_Actuales.size() != 2) {
        throw new IllegalArgumentException("Para jugar la etapa final debe exisitir dos equipos");
    }

    Partido final_Partido = new Partido(equipos_Actuales.get(0), equipos_Actuales.get(1));
    Random random = new Random();

    int marcador1 = random.nextInt(5);
    int marcador2 = random.nextInt(5);

    String resultado_final = marcador1 + "-" + marcador2;
    final_Partido.registrar_Resultado(resultado_final);

    System.out.println(final_Partido);
    registro_Result.add(final_Partido);

    //determinar al campeon
    Campeon = marcador1 > marcador2 ? equipos_Actuales.get(0) : equipos_Actuales.get(1);
}
   //metodo recursivo para generar enfrentamientos
    private ArrayList<Partido> sorteoRecursivo(ArrayList<Equipo>listaEquipos){
        if (listaEquipos.size() <= 2) {
            ArrayList<Partido>partidos = new ArrayList<>();
            partidos.add(new Partido(listaEquipos.get(0), listaEquipos.get(1)));
            return partidos;
        }else{
            Collections.shuffle(listaEquipos);
            ArrayList<Equipo> subLista1 = new ArrayList<>(listaEquipos.subList(0,listaEquipos.size()/ 2));
            ArrayList<Equipo> subLista2 = new ArrayList<>(listaEquipos.subList(listaEquipos.size()/2, listaEquipos.size()));

            ArrayList<Partido> partidos1= sorteoRecursivo(subLista1);
            ArrayList<Partido> partidos2= sorteoRecursivo(subLista2);

            partidos1.addAll(partidos2);
            return partidos1;
        }
    }
   
    //Metodo para mostrar el registro completo del torneo
    public void most_Registro_Partidos(){
        if (registro_Result.isEmpty()) {
            System.out.println("No hay partidos aun jugados");
        }else{
            System.out.println("\nAqui tienes el registro de todos los partidos");
            for(Partido partido : registro_Result){
                System.out.println(partido);
            }
        }
    }

    //metodo para jugar una etapa especifica
    public void Jugar_Etapa_Especifica(String etapa) throws Cantidad_Equipos_Invalido_Exception{
        int equipos_Esperados;
        switch (etapa) {
            case "Octavos de final":
                equipos_Esperados = 16;
                if (etapa_Actual != 0) {
                    throw new IllegalStateException("Ya se jugo esta etapa de octavos");
                }
                break;
                case "Cuarto de final":
                equipos_Esperados = 8;
                if (etapa_Actual != 1) {
                    throw new IllegalStateException("Tienes que jugar la etapa de octavos para pasar a cuartos");
                }
                break;

                case "Semifinales":
                equipos_Esperados = 4;
                if (etapa_Actual != 2) {
                    throw new IllegalStateException("Tienes que jugar la etapa de Cuartos para pasar a Semifinales");
                }
                break;


                case "Final":
                equipos_Esperados = 2;
                if (etapa_Actual != 3) {
                    throw new IllegalStateException("Tienes que jugar la etapa de Semifianles para pasar a la Final");
                }
                break;
            default:
                throw new IllegalArgumentException("Etapa no valida");
        }
        if (equipos.size() != equipos_Esperados) {
            throw new Cantidad_Equipos_Invalido_Exception("Debe haber" + equipos_Esperados + " Equipos para jugar esta etapa.");
        }
        System.out.println("\n Se juega" + etapa );
        equipos = realizar_etapa(etapa,equipos);
        
        //condicion para actualizar la etapa
        if (etapa.equals("Final")) {
                Campeon = equipos.get(0);
                System.out.println("¡El campeon del torneo es: " + Campeon.getNom_equipo() + "! Felicidades Cracks");
        }else{
            etapa_Actual++;
        }
    }
}
