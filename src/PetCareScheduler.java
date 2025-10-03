import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.time.LocalDate;

public class PetCareScheduler {
    private static Scanner scanner = new Scanner(System.in);

    private static Map<String,Pet> pets = new HashMap<>();

    public static void main(String[] args) {
        loadPetsFromFile();
        boolean running = true;
        while (running) {
            System.out.println("\n=== Pet-Care Scheduler ===");
            System.out.println("1. Register Pet");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Display Registered Pets");
            System.out.println("4. Generate Reports");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            // You will handle the choice entered here using switch(case)
            switch (choice) {
                case "1":
                    registerPet();
                    break;
                case "2":
                    schedulePetAppointment();
                    break;
                case "3":
                    displayPets();
                    break;
                case "4":
                    generateReports();
                    break;
                case "5":
                    savePetsToFile();
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }
    }

    private static void registerPet() {
        System.out.println("Enter Pet ID : ");
        String petId = scanner.nextLine().trim();
        if(!pets.containsKey(petId)){

            System.out.println("Enter Pet Name : ");
            String petName = scanner.nextLine().trim();

            System.out.println("Enter Pet Species : ");
            String petSpecies = scanner.nextLine().trim();

            System.out.println("Enter Pet Age : ");
            long petAge = Long.parseLong(scanner.nextLine().trim());

            System.out.println("Enter Pet Owner Name : ");
            String petOwnerName = scanner.nextLine().trim();

            System.out.println("Enter Contact number : ");
            String contactInfo = scanner.nextLine().trim();

            pets.put(petId,new Pet(petId,petName,petSpecies,petAge,petOwnerName,contactInfo));
            System.out.printf("%s added with ID - %s%n", petName, petId);
        }else {
            System.out.println("Pet is already register with id "+petId+" !!");
            return;
        }
    }

    private static void schedulePetAppointment() {
        System.out.println("Enter Pet ID for scheduling appointment : ");
        String petId = scanner.nextLine();

        if(pets.containsKey(petId)){
            while(true) {
                System.out.println("Enter Appointment type(Visit, Vaccination, Grooming) : ");
                String appType = scanner.nextLine().trim();
                if (appType.equalsIgnoreCase("Visit") || appType.equalsIgnoreCase("Vaccination") || appType.equalsIgnoreCase("Grooming")) {
                    System.out.println("Enter Date for appointment in DD/MM/YYYY format: ");
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a",Locale.ENGLISH);
                    LocalDate appDate = LocalDate.parse(scanner.nextLine().trim(),dateFormat);

                    if(appDate.isAfter(LocalDate.now()) || appDate.isEqual(LocalDate.now())){
                        System.out.println("Enter Time for appointment in 12:00 PM format: ");
                        LocalTime appTime = LocalTime.parse(scanner.nextLine().trim(),timeFormat);
                        Pet pet = pets.get(petId);
                            pet.addAppointment(new Appointment(appType,appDate,appTime));
                            System.out.println("Appointment scheduled for "+pet.getPetName()+" on : "+appDate+","+appTime);
                            break;
                    }else {
                        System.out.println("Scheduled Date should be today or later");
                        continue;
                    }
                } else {
                    System.out.println("Invalid Appointment Type!!!");
                    System.out.println("Try Again..");
                    continue;
                }
            }

        }
    }

    private static void displayPets() {
        if(pets.isEmpty()){
            System.out.println("No Pets registered.!");
            return;
        }

        System.out.println("1. All Registered Pet");
        System.out.println("2. Appointments for specific pet");
        System.out.println("3. Upcoming Appointments for all Registered Pets");
        System.out.println("4. Past Scheduled Appointments");
        String userOption = scanner.nextLine();

        switch (userOption){
            case "1":
                System.out.println("=== All Registered Pet ===\n");
                for (Pet p: pets.values()){
                    System.out.println(p.toString()+"\n");
                }
                break;
            case "2":
                System.out.println("Enter Pet ID : ");
                String petId = scanner.nextLine().trim();
                displayPetsAppointment(petId);
                break;
            case "3":
                System.out.println("=== Upcoming Appointments for all Registered Pets ===");
                for (Pet p: pets.values()){
                    System.out.println("Appointments for "+p.getPetName() + " :");
                    displayPetsAppointment(p.getPetId(),false);
                    System.out.println();
                }
                break;
            case "4":
                System.out.println("=== Past Appointments for all Registered Pets ===");
                for (Pet p: pets.values()){
//                    System.out.println("Appointments for "+p.getPetName() + " :");
                    displayPetsAppointment(p.getPetId(),true);
                }
                break;
            default:
                System.out.println("Enter correct value!!!");
        }


    }

    private static void displayPetsAppointment(String petId,boolean past) {

        Pet pet = pets.get(petId);
        if(past){
            if(pet.getAppointmentList().isEmpty()){
                System.out.println("No appointments are there for "+pet.getPetName());
                return;
            }
            boolean flag = false;
            for(Appointment app: pet.getAppointmentList()){
                if((app.getAppDate().isBefore(LocalDate.now()) || app.getAppDate().isEqual(LocalDate.now())) && app.getAppTime().isBefore(LocalTime.now())){
                    System.out.println(app.toString()+"\n");
                    flag=true;
                }
            }
            if (!flag){
                System.out.println("No past appointments are there for "+pet.getPetName());
            }
        } else  {
            if(pet.getAppointmentList().isEmpty()){
                System.out.println("No appointments are there for "+pet.getPetName());
                return;
            }
            boolean flag = false;
            for(Appointment app: pet.getAppointmentList()){

                if((app.getAppDate().isAfter(LocalDate.now()) || app.getAppDate().isEqual(LocalDate.now())) && app.getAppTime().isAfter(LocalTime.now())){
                    System.out.println(app.toString()+"\n");
                    flag=true;
                }
            }
            if (!flag){
                System.out.println("No upcoming appointments are there for "+pet.getPetName());
            }
        }

    }

    private static void displayPetsAppointment(String petId) {
        if(pets.containsKey(petId)){
            if(pets.get(petId).getAppointmentList().isEmpty()){
                System.out.println("No appointments are there for "+pets.get(petId).getPetName());
                return;
            }
            System.out.println("Appointments for "+pets.get(petId).getPetName() + " :");
            for(Appointment app: pets.get(petId).getAppointmentList()){
                System.out.println(app.toString()+"\n");
            }
        }else{
            System.out.println("Invalid Pet ID!!");
        }
    }

    private static void generateReports() {
        System.out.println("Pets with upcoming appointments in next week.");

        for (Pet p: pets.values()){
            if(!p.getAppointmentList().isEmpty()){
                System.out.println("Appointment for "+p.getPetName() + " :\n");
                for(Appointment app: p.getAppointmentList()){
                    if(app.getAppDate().isAfter(LocalDate.now().plusDays(7))){
                        System.out.println(app.toString()+"\n");
                    }
                }
            }

        }

        System.out.println("Pets overdue for a vet visit.");
        for (Pet p: pets.values()){
            if(!p.getAppointmentList().isEmpty()){

                ArrayList<Appointment> sortedList = p.getAppointmentList();
                sortedList.sort(Comparator.comparing(Appointment::getAppDate).reversed());
                for(Appointment app: sortedList){
                    if (app.getAppointmentType().equalsIgnoreCase("Visit") && app.getAppDate().isBefore(LocalDate.now().minusMonths(6))){
                        System.out.println("Vet visit due for "+p.getPetName() + " :\n");
                        break;
                    }
                }
            }

        }
    }

    private static void savePetsToFile() {
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PetCareScheduler.ser"));
            out.writeObject(pets);

        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("Error : "+e.getMessage());
        }

    }
    @SuppressWarnings("unchecked")
    private static void loadPetsFromFile() {

        try (
                // Open an ObjectInputStream to read from the file "PetCareScheduler.ser"
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("PetCareScheduler.ser"));
        ) {
            // Read the object from the file and cast it back to the correct type
            pets = (Map<String, Pet>) in.readObject();

            // Confirmation message to let the user know data was loaded
            System.out.println("Pets data loaded.");
        } catch (FileNotFoundException e) {
            // Task 8
            // If the file doesn't exist yet, that's okay — start with empty data
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            // Handle other errors, like if the file is corrupted or unreadable
            System.out.println("Error loading data: " + e.getMessage());
        }

    }
}
