import java.util.*;

public class Main {
    public static void main(String[] args) {
        Liga_de_futbol liga = new Liga_de_futbol();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        //Ingreso inicial de los 16 equipos
        System.out.println("Ingrese el nombre de los 16 equipos:");
        for(int i = 1;i <= 16; i++){
            System.out.print("Equipo " + i + ": ");
            String nombreEquipo = scanner.nextLine();
            liga.Agregar_Equipos(new Equipo(nombreEquipo));
        }
        while (!salir) {
            System.out.println("\n=====LIGA PRO====");
            System.out.println("1. Ver equipos registrados");
            System.out.println("2. Jugar una etapa del torneo");
            System.out.println("3. Iniciar el torneo completo");
            System.out.println("4. Mostrar registro de resultados del torneo");
            System.out.println("5. Salir");

            System.out.println("Selecciones una opcion: ");
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    liga.Ver_Equipos();
                    break;
                case 2:
                System.out.println("\nSelecciona una etapa: ");
                System.out.println("1. Octavos de final: ");
                System.out.println("2. Cuartos de final");
                System.out.println("3. Seminifales");
                System.out.println("4. Final");
                System.out.println("Ingresa el numero de la etapa que quieres: ");
                int etapa = scanner.nextInt();
                scanner.nextLine();
                try {
                    switch (etapa) {
                        case 1:
                            liga.Jugar_Etapa_Especifica("Octavos de final");
                            break;
                        case 2:
                            liga.Jugar_Etapa_Especifica("Cuatos de final");
                            break;
                        case 3:
                            liga.Jugar_Etapa_Especifica("Semifinales");
                            break;
                        case 4:
                            liga.Jugar_Etapa_Especifica("Final");
                            break;
                        default:
                            System.out.println("Opcion no valida. Intente de nuevo");               
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Error: " + e.getMessage());
                }
                break;

                case 3:
                //implementacion de excepcion "bloque try catch"
                try {
                    liga.Play_Torneo();
                } catch (Cantidad_Equipos_Invalido_Exception e) {
                    // TODO: handle exception
                    System.out.println("Error: " + e.getMessage());
                }
                break;

                case 4:
                liga.most_Registro_Partidos();
                break;


                case 5:
                salir = true;
                System.out.println("Saliendo del programa..... Gracias por usarlo :)");
                break;

                default:
                System.out.println("Opcion no valida.....");
                    //break;
            }
        }
    }
}
