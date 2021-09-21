import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;                //imports all Necessary librarys for the EUapp
import com.thoughtworks.xstream.XStream;            //party is the class that is fully javadoc
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import java.util.Scanner;

public class EUDriver {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Country> euCountries;              //declaring all fields for EuDriver including scanner, creating a array of countries and partyList
    private PartyList partyList;

    public EUDriver() {
        euCountries = new ArrayList<Country>();
        partyList = new PartyList();                //instanceating eucountries and partylist
        runMenu();                                  //tests will run without run menu
    }

    public EUDriver(int i) {
        euCountries = new ArrayList<Country>();
        partyList = new PartyList();            //for tests
    }

    public static void main(String args[]) {
        new EUDriver();                        //main for running the app
    }

    public PartyList getPartyList() {
        return partyList;                   //getter for partylist
    }

    public void setPartyList(PartyList partyList) {     //setter for partylist
        this.partyList = partyList;
    }

    public void setEuCountries(ArrayList<Country> euCountries) {
        this.euCountries = euCountries;    //setter for eucountries
    }

    public ArrayList<Country> getEuCountries() {
        return euCountries;                     //getter for EuCountries
    }

    private int mainMenu() {
        System.out.println("EU Menu");
        System.out.println("------------------");
        System.out.println("1) Add a country to the EU");
        System.out.println("2) Remove a country from the EU");
        System.out.println("3) Update an EU country's information");
        System.out.println("4) List all the EU Countries");
        System.out.println("------------------");
        System.out.println("Country Menu");
        System.out.println("5) Add an MEP");
        System.out.println("6) Remove an MEP");
        System.out.println("7) Update the information on an MEP");
        System.out.println("8) List all MEPs in this country");
        System.out.println("------------------");
        System.out.println("Party Menu");
        System.out.println("9) Add a New Party");
        System.out.println("10) Remove a Party");                           //creating the menu
        System.out.println("11) Update the Party Information");
        System.out.println("12) List all Parties");
        System.out.println("------------------");
        System.out.println("Report Menu");
        System.out.println("13) Calculate and print the party with the most local Representatives");
        System.out.println("14) Calculate and print the party with the most MEPs");
        System.out.println("15) List all parties of a given Genre");
        System.out.println("16) List all MEPs of a given party");
        System.out.println("------------------");
        System.out.println("17) Save to XML ");
        System.out.println("18) Load from XML");
        System.out.println("------------------");
        System.out.println("0) Exit");
        System.out.println("==>>");
        int choice = input.nextInt();
        return choice;
    }

    private void runMenu() {
        int choice = mainMenu();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    deleteCountry();
                    break;
                case 3:
                    upDateCountry();
                    break;
                case 4:
                    System.out.println(listCountries());        //switchcase for menu
                    break;
                case 5:
                    addMEP();
                    break;
                case 6:
                    deleteMEP();
                    break;
                case 7:
                    updateMep();
                    break;
                case 8:
                    listMEPSOfCountry();
                    break;
                case 9:
                    addNewParty();
                    break;
                case 10:
                    removeParty();
                    break;
                case 11:
                    updateParty();
                    break;
                case 12:
                    System.out.println(listOfParties());
                    break;
                case 13:
                    System.out.println(partyList.largestParty());
                    break;
                case 14:
                    System.out.println(partyList.mostMEPs(euCountries));
                    break;
                case 15:
                    listPartyByGenre();
                    break;
                case 16:
                    listMEPsbyPartyName();
                    break;
                case 17:
                    try{
                        saveCountries();
                        savePartyList();
                    }
                    catch (Exception e){
                        System.err.println("Error writing to file: " + e);
                    }
                    break;
                case 18:
                    try{
                        loadCountries();
                        loadPartyList();
                    }
                    catch (Exception e){
                        System.err.println("Error loading from file: " + e);
                    }
                    break;
                default:
                    System.out.println("Please select a valid option");
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            choice = mainMenu();
        }
        System.out.println("Exiting........bye");
        System.exit(0);
    }

    private void listMEPSOfCountry() {
        System.out.println(listCountries());
        if (euCountries.size() > 0) {
            System.out.println("Enter the index of the Country you want to list the MEP's of ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < euCountries.size())) {
                if (euCountries.get(index).getMeps().size() == 0) {             //lists meps of a country
                    System.out.println("No Meps in Country");
                } else {
                    System.out.println(euCountries.get(index).listOfMEPs());
                }
            } else {
                System.out.println("There is no Country for this index");
            }
        }
    }


    private void listPartyByGenre() {
        System.out.println("Please enter a Genre");
        String partyGenre = input.nextLine();            //lists party by genre by sending it to listPartiesBySpecificGenre() in the partylist class
        System.out.println(partyList.listPartiesBySpecificGenre(partyGenre));
    }


    private void addMEP() {
        System.out.println(listCountries());
        if (euCountries.size() > 0) {
            System.out.println("Enter the index of the Country you want to add a MEP to ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < euCountries.size())) {
                input.nextLine();
                System.out.println("Please enter the name of the MEP");
                String MEPName = input.nextLine();
                System.out.println("Please enter the phone Number of the MEP");
                String MEPPhone = input.nextLine();                                             //adding a mep into a country
                System.out.println("Please enter the email address of the MEP");
                String MEPEmail = input.nextLine();
                System.out.println("Please enter the party the MEP is part of");
                String tempParty = input.nextLine();
                Party MEPParty = Utilities.validParty(tempParty, partyList);
                euCountries.get(index).addMEP(new Mep(MEPName, MEPPhone, MEPEmail, MEPParty, partyList));
            } else {
                System.out.println("There is no Country for this index");
            }
        }
    }

    private void addCountry() {
        input.nextLine();
        System.out.println("Please enter the name of the Country you wish to add");
        String name = input.nextLine();
        System.out.println("Number of Seats ");
        int numberOfSeats = input.nextInt();            //adding a country
        euCountries.add(new Country(name, numberOfSeats));
    }

    private void deleteCountry() {
        System.out.println(listCountries());
        if (euCountries.size() > 0) {
            System.out.println("Enter the index of the Country you want to delete ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < euCountries.size())) {        //deleting a country
                euCountries.remove(index);
                System.out.println("Country deleted");
            } else {
                System.out.println("There is no Country for this index");
            }
        }
    }

    private void upDateCountry() {
        System.out.println(listCountries());
        if (euCountries.size() > 0) {
            input.nextLine();
            System.out.println("Enter the index of the Country you want to update ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < euCountries.size())) {
                input.nextLine();                                   //updating a countries info
                System.out.print("Change the number of Meps for this Country  ");
                int noMEPs = input.nextInt();
                Country country = euCountries.get(index);
                country.setNoMEPs(noMEPs);
            } else {
                System.out.println("This country is not recognised");
            }
        }
    }

    public Country findCountry(String countryName) {
        if (euCountries.size() == 0) {
            return null;
        } else {
            Country tempC = null;
            for (int i = 0; i < euCountries.size(); i++) {
                if(countryName.equals(euCountries.get(i).getName())){  //searches through the Eucountries array and returns it
                    tempC = euCountries.get(i);
                }
            }
            return tempC;
        }
    }

    public String listCountries() {
        if (euCountries.size() == 0) {
            return "No Countries in list";
        } else {
            String listOfCountries = "";
            for (int i = 0; i < euCountries.size(); i++) {
                listOfCountries = listOfCountries + i + ": " + euCountries.get(i) + "\n"; //lists all countries
            }
            return listOfCountries;
        }
    }

    private void updateMep() {
        System.out.println(listCountries());
        if (euCountries.size() > 0) {
            input.nextLine();
            System.out.println("Enter the index of the Country of the Mep you want to update ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < euCountries.size())) {
                System.out.println(euCountries.get(index).listOfMEPs());
                System.out.println("Enter the index of the mep you want to update");
                int choice = input.nextInt();

                if ((choice >= 0) && (choice < euCountries.get(index).getMeps().size())) {
                    input.nextLine();
                    System.out.println("Please enter the name of the MEP");
                    String MEPName = input.nextLine();
                    System.out.println("Please enter the phone Number of the MEP");
                    String MEPPhone = input.nextLine();
                    System.out.println("Please enter the email address of the MEP");
                    String MEPEmail = input.nextLine();
                    System.out.println("Please enter the party the MEP is part of");        //updates a meps information
                    String tempParty = input.nextLine();

                    Mep mep = euCountries.get(choice).getMEP(choice);
                    mep.setMEPName(MEPName);
                    mep.setMEPPhone(MEPPhone);
                    mep.setMEPEmail(MEPEmail);
                    mep.setMEPParty(tempParty, getPartyList());
                } else {
                    System.out.println("There are no meps for this index number");
                }
            } else {
                System.out.println("This country is not recognised");
            }
        }

    }

    private void deleteMEP() {
        System.out.println(listCountries());
        if (euCountries.size() > 0) {
            input.nextLine();
            System.out.println("Enter the index of the Country of the Mep you want to delete ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < euCountries.size())) {
                System.out.println(euCountries.get(index).listOfMEPs());
                System.out.println("Enter the index of the mep you want to delete");
                int choice = input.nextInt();                                                              //deleting an mep

                if ((choice >= 0) && (choice < euCountries.get(index).getMeps().size())) {
                    input.nextLine();
                    euCountries.get(index).getMeps().remove(index);
                    System.out.println("Mep deleted");

                } else {
                    System.out.println("There are no meps for this index number");
                }
            } else {
                System.out.println("This country is not recognised");
            }
        }
    }

    private void addNewParty() {
        input.nextLine();
        System.out.println("Please enter the name of the party");
        String partyName = input.nextLine();
        System.out.println("Please enter the name of party Leader");
        String partyLeader = input.nextLine();
        System.out.println("Please enter the genre of the party");                          //adding a new party
        String partyGenre = input.nextLine();
        System.out.println("Please enter the number of local representatives");
        int numLocalReps = input.nextInt();
        partyList.addParty(new Party(partyName, partyLeader, partyGenre, numLocalReps));

    }

    private void removeParty() {
        System.out.println(listOfParties());
        if (partyList.getPartyList().size() > 0) {
            System.out.println("Enter the index of the Party you want to delete ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < partyList.getPartyList().size())) {
                partyList.getPartyList().remove(index);
                System.out.println("Party deleted");                                            //removes a party
            } else {
                System.out.println("There is no Party for this index");
            }
        }
    }

    public String listOfParties() {
        if (partyList.getPartyList().size() == 0) {
            return "No Parties in list";
        } else {
            String listOfParties = "";
            for (int i = 0; i < partyList.getPartyList().size(); i++) {
                listOfParties = listOfParties + i + ": " + partyList.getPartyList().get(i) + "\n";  //lists all parties
            }
            return listOfParties;
        }
    }

    private String listMEPsbyPartyName() {
            System.out.println("Please enter the name of the party");
            String partyName = input.nextLine();                        //calls the listMEPsbySpecificParty to list meps by specific party
            return listMEPsbySpecificParty(partyName);
        }



    public String listMEPsbySpecificParty(String partyName) {
        if(partyList.getPartyList().size() == 0){
            return "No Parties";
        }
        else{
            for(int cIndex = 0; cIndex< euCountries.size(); cIndex++)
                for(int i = 0; i < partyList.getPartyList().size(); i++){     //looks for all the meps of the party name
                    if(partyName.equals(partyList.getParty(i).getPartyName())) {
                        partyName = euCountries.get(i).getMeps().get(i).toString();
                    }else{
                        return "There are no parties for this genre";
                    }

                }
            return partyName;
        }
    }

    public int noMEPSBySpecificParty(String partyName) {
            int counter = 0;
        for(int cIndex = 0; cIndex< euCountries.size(); cIndex++){
            for(int i = 0; i < euCountries.get(cIndex).getMeps().size(); i++){
                Party p = euCountries.get(cIndex).getMEP(i).getMEPParty();         //finds the number of meps by that party
                if(p!=null)
                    if(partyName.equals(p.getPartyName())) {
                        counter++;
                    }
            }
        }
        return counter;
    }


    private void updateParty() {
        System.out.println(listOfParties());
        if (partyList.getPartyList().size() > 0) {
            input.nextLine();
            System.out.println("Enter the index of the party you want to update ==>");
            int index = input.nextInt();

            if ((index >= 0) && (index < partyList.getPartyList().size())) {
                input.nextLine();
                System.out.println("Please enter the name of the party");
                String partyName = input.nextLine();
                System.out.println("Please enter the name of party Leader");  //updates the info of a party
                String partyLeader = input.nextLine();
                System.out.println("Please enter the genre of the party");
                String partyGenre = input.nextLine();
                System.out.println("Please enter the number of local representatives");
                int numLocalReps = input.nextInt();

                Party party = partyList.getPartyList().get(index);
                party.setPartyName(partyName);
                party.setPartyLeader(partyLeader);
                party.setPartyGenre(partyGenre);
                party.setNumLocalReps(numLocalReps);

            } else {
                System.out.println("There is no party for this index number");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void savePartyList() throws Exception{
        partyList.save();                                   //saves partylist
    }
    public void loadPartyList() throws Exception{
        partyList.load();                                   //loads partylist
    }
    private void loadCountries() throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Countries.xml")); //loads countries
        euCountries = (ArrayList<Country>) is.readObject();
        is.close();

    }
    public void saveCountries() throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Countries.xml"));
        out.writeObject(euCountries);   //saves country
        out.close();

    }

}