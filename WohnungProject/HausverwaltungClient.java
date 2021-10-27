import java.util.Scanner;

public class HausverwaltungClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] command = sc.nextLine().split("\\s+");
        String dateiName = command[0];
        Hausverwaltung controller = new Hausverwaltung(dateiName);

        switch(command[1]){
            case "list":
                if(command.length == 2){
                    controller.getAllFlatData().stream()
                            .forEach(e -> System.out.println(e.toString() + System.lineSeparator()));
                }
                else{
                    int id = Integer.parseInt(command[2]);
                    System.out.println(controller.getWohnung(id).toString());
                }
                break;
            case "add":
                if(command[2].equals("EW")){
                    EigentumsWohnung ew = readEW(command);
                    controller.addNewWohnung(ew);
                    System.out.println("Info: Wohnung " + ew.getId() + " added.");
                }
                else if(command[2].equals("MW")){
                    MietWohnung mw = readMW(command);
                    controller.addNewWohnung(mw);
                    System.out.println("Info: Wohnung " + mw.getId() + " added.");
                }
                break;
            case "delete":
                int id = Integer.parseInt(command[2]);
                controller.deleteWohnung(id);
                System.out.println("Info: Wohnung " + id + "deleted.");
                break;
            case "count":
                if(command.length == 2){
                    System.out.println(controller.allWohnungenCount());
                }
                else if(command[2].equals("EW")){
                    System.out.println(controller.EWCount());
                }
                else if(command[2].equals("MW")){
                    System.out.println(controller.MWCount());
                }
                break;
            case "meancosts":
                System.out.printf("%.2f%n", controller.averageMonthCostsAllWohnungen());
                break;
            case "oldest":
                controller.getOldestWohnungen().stream().forEach(e -> System.out.println("id: " + e));
                break;
        }


    }

    private static MietWohnung readMW(String[] command) {
        return new MietWohnung(Integer.parseInt(command[3]), Double.parseDouble(command[4]),
                Integer.parseInt(command[5]), Integer.parseInt(command[6]), command[7], new Address(command[8],
                command[9], Integer.parseInt(command[10]), Integer.parseInt(command[11])),
                Double.parseDouble(command[12]), Integer.parseInt(command[13]));
    }

    private static EigentumsWohnung readEW(String[] command) {
        EigentumsWohnung ew = new EigentumsWohnung(Integer.parseInt(command[3]), Double.parseDouble(command[4]),
                Integer.parseInt(command[5]), Integer.parseInt(command[6]), command[7], new Address(command[8],
                command[9], Integer.parseInt(command[10]), Integer.parseInt(command[11])),
                Double.parseDouble(command[12]), Double.parseDouble(command[13]));
        return ew;
    }
}
