import java.util.ArrayList;

public class Country {
    private String name;
    private ArrayList<Mep> meps;    //variables for country name and creating mep arraylist
    private int noMEPs;

    public Country(String name, int noMEPs) {
        meps = new ArrayList<Mep>();

        this.name = Utilities.max30Chars(name);   //constructor with validation and creating a mep arraylist

        if(Utilities.validIntNonNegative(noMEPs)){
            this.noMEPs = noMEPs;
        }else{
            System.out.println("Please enter a valid number");
        }
    }

    public String getName() {
        return Utilities.max30Chars(name);   //getter for country name
    }

    public void setName(String name) {
        if(name.length() < 30) {                        //setter for country name
            this.name = Utilities.max30Chars(name);
        }
    }

    public ArrayList<Mep> getMeps() {
        return meps;                        //getter for mep
    }

    public void setMeps(ArrayList<Mep> meps) {      //setter for mep
        this.meps = meps;
    }

    public Mep getMEP(int i){
         if(!meps.isEmpty()){
             return meps.get(i) ;       //getter for mep based on index
         }
         else{
             return null;
         }
        }

    public int getNoMEPs() {
        if(Utilities.validIntNonNegative(noMEPs)){
            return noMEPs;                                //getter for no meps
        }else{
            return noMEPs = 0;
        }
    }

    public void setNoMEPs(int noMEPs) {
        if(Utilities.validIntNonNegative(noMEPs)){      //setter for number of mep
            this.noMEPs= noMEPs;
        }
    }

    public void addMEP(Mep mep){
        if(meps.size() < noMEPs) {
            meps.add(mep);                        //adds mep to country
        }
    }

    public boolean removeMEP(int i){
        if((!meps.isEmpty()) && Utilities.validIntNonNegative(i) && (i < meps.size())){
            meps.remove(i);
            return  true;       //removes an mep
        }else{
            return false;
        }
    }

    public int numberOfMEPs(){
        return meps.size();
    }       //returns size of meps

    public String listOfMEPs(){
        String listOfMEPs = "";
        for (int i = 0; i < meps.size(); i++){
            listOfMEPs = listOfMEPs + i + ": " + meps.get(i);  //lists meps
        }
        return listOfMEPs;
    }


    public String listOfMEPsByParty(Party suppliedParty){
        String listOfMEPsByParty = "";
        for (int i = 0; i < meps.size(); i++){
           if(suppliedParty.equals(meps.get(i).getMEPParty())){     //lists meps by party
               listOfMEPsByParty = listOfMEPsByParty + getMEP(i).getMEPName();
           }
        }
        return listOfMEPsByParty;
    }

    public int noOfMEPsByParty(Party party){
       int noOfMEPsByParty = 0;
       for (int i = 0; i < meps.size(); i++) {
               if ((meps.get(i).getMEPParty() != null) && (party.getPartyName().equals(meps.get(i).getMEPParty().getPartyName()))) {
                   noOfMEPsByParty = noOfMEPsByParty + 1;       //returns number of mep by party
               }

       }
       return noOfMEPsByParty;
    }


    @Override
    public String toString() {
        return "Name of Country : " + name + "\n" +
                "   Number of Meps : " + meps.size() +  "\n" +
                "   Number of Seats: " + noMEPs + "\n" +        //to string for country
                "--------------------------------------";
    }
}





