import java.util.*;

public class Liga_de_futbol {
    //lista de equipos
    private ArrayList<Equipo> equipos = new ArrayList<>();
    private ArrayList<Partido> registro_Result = new ArrayList<>();

    //Estadistica de partidos jugados
    private Map<String, Integer> estadistic = new HashMap<>();

    //metodo para ingresar los equipos
    public void Agregar_Equipos(Equipo equipo){
        equipos.add(equipo);
        //eSTADISTICAS DEL EQUIPO
        estadistic.put(equipo.getNom_equipo(),0);
        System.out.println("Equipo Agregado: " + equipo.getNom_equipo());
        
    }
    //metodo para quitar un equipo
    public void Quitar_Equipo(String nom_equip0){
        equipos.removeIf(equipo -> equipo.getNom_equipo().equalsIgnoreCase(nom_equip0));
        estadistic.remove(nom_equip0);
        System.out.println(nom_equip0);

    }

    //metodo para ver las estadisiticas
    public void ver_Estadistics(){
        System.out.println("\nEsadisticas del equipo:");
        estadistic.forEach((equipo, partidos_jugados) ->
            System.out.println("Equipo:" + equipo + ", Partidos jugados: " + partidos_jugados));
    }

    //mostrar el registro de los partidos
    public void most_Registro_Partidos(){
        if(registro_Result.isEmpty()){
            System.out.println("Aun no existen resultados");
        }else{
            System.out.println("\nRegisttro de Resultados:");
            for(Partido partido : registro_Result){
                System.out.println(partido);
            }
        }
    }


    public void realizar_Sorteo(String etapa) throws Cantidad_Equipos_Invalido_Exception{
        if (etapa.equalsIgnoreCase("final")&& equipos.size() != 2) {
            throw new Cantidad_Equipos_Invalido_Exception("Para la final, solo deben estar 2 equipos");
    
        }else if (!etapa.equalsIgnoreCase("Final")&& equipos.size() % 2 !=0) {
            throw new Cantidad_Equipos_Invalido_Exception("La cantidad de equipos debe ser par para iniciar el partido");

        }

        ArrayList<Partido> partidos;
        if (etapa.equalsIgnoreCase("final")) {
            partidos = new ArrayList<>();
            partidos.add(new Partido(equipos.get(0), equipos.get(1)));
        }else{
            partidos = sorteoRecursivo(new ArrayList<>(equipos));
        }
        System.out.println("\n Enfrentamientos para " + etapa + ":");
        for (Partido partido : partidos){
            System.out.println(partido);
            registro_Result.add(partido);
            actualizar_Estadistics(partido);
        }
        //registro_Result(partidos);
        registrar_Resultados(partidos);
    }

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

    private void actualizar_Estadistics(Partido partido){
        estadistic.put(partido.getEquipo1().getNom_equipo(), estadistic.get(partido.getEquipo1().getNom_equipo()) + 1);
        estadistic.put(partido.getEquipo2().getNom_equipo(), estadistic.get(partido.getEquipo2().getNom_equipo()) + 1);
    }

    private void registrar_Resultados(ArrayList<Partido> partidos){
        Scanner scanner = new Scanner(System.in);
        for(Partido partido : partidos){
            System.out.println("Ingrese el resultado de " + partido + "(Ejemplos 2-1): ");
            String result = scanner.nextLine();
            partido.registrar_Resultado(result);
        }
    }

}
