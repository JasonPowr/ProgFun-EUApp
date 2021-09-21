import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileReader;
import java.io.FileWriter;                      //imports all Necessary librarys for the partylist
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PartyList {
    private Party party;                //fields for partylist, creating a arraylist of parties
    private ArrayList<Party> parties;

    public PartyList() {
        parties = new ArrayList<Party>();  //constructor for partylist
    }

    public ArrayList<Party> getPartyList() {  //getter for parytlist
        return parties;
    }

    public void setParties(ArrayList<Party> parties) {
        this.parties = parties;                         //setter for parties
    }

    public Party getParty(int index){
        if((!parties.isEmpty()) && Utilities.validIntNonNegative(index) && (index < parties.size())){
            return parties.get(index) ;
        }                                              //getter for parties by index
        else{
            return null;
        }
    }


    public void addParty(Party party) {
        parties.add(party);                      //adds a party to the parties arraylist
    }


    public boolean removeParty(int index){
        if((!parties.isEmpty()) && Utilities.validIntNonNegative(index) && (index < parties.size())){
            parties.remove(index);
            return  true;         //remove a party by index
        }else{
            return false;
        }
    }

    public int numberOfParties() {
        return parties.size();          //returns number of parties in arraylist
    }

    public String listOfParties() {
        if (parties.size() == 0) {
            return "no parties in the list";
        } else {
            String listOfParties = "";
            for (int i = 0; i < parties.size(); i++) {              //lists all parties
                listOfParties = listOfParties + i + ": " + parties.get(i);
            }
            return listOfParties;
        }
    }

    public String listPartiesBySpecificGenre(String suppliedGenre) {
        suppliedGenre = suppliedGenre.toUpperCase();
        String listPartiesBySpecificGenre = "";
        if(parties.size() !=0) {
            for (int i = 0; i < parties.size(); i++) {
                if (parties.get(i).getPartyGenre().equals(suppliedGenre)) {
                    listPartiesBySpecificGenre = listPartiesBySpecificGenre + getParty(i).toString();
                }
            }                                                   //lists parties by genre
            if(listPartiesBySpecificGenre.length()>1)
                return listPartiesBySpecificGenre;
            else return "no parties with the genre";
        }
        else{
            return  "no parties";
        }
    }


  public Party largestParty() {
    if(parties.size() == 0){
        return null;
    }else{
        Party largestParty = parties.get(0);
        for(int i = 1; i < parties.size(); i++){  //gets the largest party
            if(parties.get(i).getNumLocalReps() > parties.get(i-1).getNumLocalReps()){
                largestParty = getParty(i);
            }
        }
        return largestParty;
    }
  }


  public Party mostMEPs(ArrayList<Country> meps){
      if(meps.size() == 0){
          return null;
      }else{
          Party mostMEPs = parties.get(0);
          int biggestMep = 0;
          for(int i = 0; i < parties.size(); i++){          //gets the party with most meps
              int mepNum =0;
              for(int c = 0; c < meps.size();c++){
                  mepNum = mepNum + meps.get(c).noOfMEPsByParty(parties.get(i));
              }
              if(mepNum > biggestMep){
                  mostMEPs = parties.get(i);
                  biggestMep = mepNum;
              }
          }
          return mostMEPs;
      }
  }

    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("partyList.xml")); //loads partylist
        parties = (ArrayList<Party>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("partyList.xml")); //saves partylist
        out.writeObject(parties);
        out.close();
    }

    @Override
    public String toString() {
        return "Parties : " + parties +"\n"+
                "--------------------------------------";

    }
}
