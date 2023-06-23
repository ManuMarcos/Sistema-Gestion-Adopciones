package vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controladores.AlarmaController;
import controladores.AnimalController;
import modelo.Alarma;
import modelo.Animal;
import modelo.dto.AlarmaDto;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.TipoDeAlarma;
import vistas.AdopcionView.OptionNuevaAdopcion;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliOption;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class AlarmaView implements ICliView{

    private AlarmaController controlador;
    private AnimalController controladorAnimal;
    private static Scanner sc;
    private static final String[] nombresOpciones = { "Crear alarma", "Actualizar alarma", "Ver Alarmas", "Atras"};
    private List<ICliOption> opciones;

    public void setController(AlarmaController controlador) {
        this.controlador = controlador;
    }
    private AnimalDto obtenerAnimalDto(List<AnimalDto> animales) {
        AnimalDto animal = null;
        if(animales.isEmpty()){
            System.out.println("No cuenta con ningun animal registrado");
        }
        else{
            boolean animalFound=false;
            while(!animalFound){
                System.out.println("Ingrese el ID del animal: ");
                String input = sc.next();
                sc.nextLine();
                try {
                    int id= Integer.parseInt(input);
                    for(AnimalDto currentAnimal: animales) {
                        if(id==currentAnimal.getId()) {
                            System.out.println("Animal encontrado");
                            animal=currentAnimal;
                            animalFound=true;
                            break;
                        }
                    }
                    if(!animalFound){
                        System.out.println("Animal no encontrado, ingrese el id correcto");
                    }
                }
                catch(Exception e){
                    System.out.println("Error: Se ha ingresado un valor que no es valido");
                }
            }
        }
        return animal;
    }
    class OptionCrearAlarma implements ICliOption {
        String[] opcionesTipoAlarma = {"Control", "Tratamiento"};



        private TipoDeAlarma obtenerTipoAlarma(int tipo) {
            System.out.println("Tipo: "+TipoDeAlarma.values()[tipo-1]);
            return TipoDeAlarma.values()[tipo-1];
        }
        private int establecerPeriodicidad() {
            int periodicidad=0;
            System.out.println("Ingrese la periodicidad de la alarma");
            periodicidad=sc.nextInt();
            return periodicidad;
        }
        private List<String> establecerAcciones(){
            List<String> acciones = new ArrayList<String>();
            boolean fin = false;
            while(!fin){
                String inputAcciones = IngresoCli
                        .solicitarStringNoNulo("Ingrese las acciones a realizar ('.' para terminar ingreso): ");
                if (inputAcciones.equals("."))
                {
                    fin = true;
                    break;
                }
                else {
                    acciones.add(inputAcciones);
                }
            }
            return acciones;
        }
        private void crearAlarma(){
            FormatoCli.printCabecera("Crear Alarma");
            FormatoCli.printOpciones(opcionesTipoAlarma);
            int opcionTipoAlarma = IngresoCli.solicitarOpcion(opcionesTipoAlarma.length);
            TipoDeAlarma tipoAlarma= obtenerTipoAlarma(opcionTipoAlarma);
            int periodicidad = establecerPeriodicidad();
            List<String> acciones = establecerAcciones();
            AnimalDto animalDto = obtenerAnimalDto(controladorAnimal.listarAnimales());
            if(animalDto!=null){
                controlador.crearAlarma(animalDto,tipoAlarma,periodicidad,acciones);
                FormatoCli.printCabecera("Alarma creada exitosamente");
            }else {
                FormatoCli.printCabecera("Error al crear la alarma, intente de nuevo");
            }
        }
        @Override
        public CliViewNames doAction() {
            crearAlarma();
            return CliViewNames.STAY;
        }


    }
    class OptionActualizarAlarma implements ICliOption {

        private void actualizarAlarma(){

        }
        @Override
        public CliViewNames doAction() {
            FormatoCli.printCabecera("Actualizar Alarma");
            actualizarAlarma();
            return CliViewNames.STAY;
        }

    }
    class OptionVerAlarmas implements ICliOption {
        List<AnimalDto> animales = controladorAnimal.listarAnimales();

        private void verAlarmas(){
            for(AnimalDto currentAnimal: animales) {
                List<AlarmaDto> alarmas = controlador.listarAlarmas(currentAnimal);
                int nro=0;
                System.out.println("Buscando Alarmas...");
                if(alarmas.isEmpty()){
                    System.out.println("No se encontro ninguna alarma");
                }
                else{
                    for(AlarmaDto alarma:alarmas){

                        System.out.println("Animal ID: "+currentAnimal.getId());
                        System.out.println("Alarma nro: "+nro);
                        System.out.println("Tipo: "+alarma.getTipo());
                        System.out.println("Acciones: "+alarma.getAcciones());
                        System.out.println("Periodicidad"+alarma.getPeriodicidad());
                    }
                }
            }
        }
        @Override
        public CliViewNames doAction() {
            FormatoCli.printCabecera("Lista de Alarmas");
            verAlarmas();
            return CliViewNames.STAY;
        }

    }

    public AlarmaView(AnimalController controladorAnimal) {
        this.controladorAnimal=controladorAnimal;
        sc=new Scanner(System.in);
        opciones = new ArrayList<>();
        opciones.add(new OptionCrearAlarma());
        opciones.add(new OptionActualizarAlarma());
        opciones.add(new OptionVerAlarmas());
        opciones.add(new ICliOption.OptionBack());
    }
    @Override
    public CliViewNames procesar() {
        // TODO Auto-generated method stub
        FormatoCli.printCabecera("Menú Alarmas");
        FormatoCli.printOpciones(nombresOpciones);
        int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
        return opciones.get(opcion - 1).doAction();
    }
}