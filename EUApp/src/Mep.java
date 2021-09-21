import java.util.ArrayList;

public class Mep {
    private ArrayList <Country> euCountries;
    private String MEPName,MEPEmail,MEPPhone;
    private Party MEPParty;                     //declaring all variables for meps info
    private PartyList partyLists;

    public Mep(String MEPName, String MEPEmail, String MEPPhone, Party MEPParty, PartyList partyList){
        partyLists = new PartyList();
        euCountries = new ArrayList<Country>();

        this.MEPName = Utilities.max30Chars(MEPName);

        if(Utilities.validEmail(MEPEmail)){
            this.MEPEmail = MEPEmail;                       //contstructor with validation
        }else {
            this.MEPEmail = "invalid format email. Needs to contain . and @";
        }

        if(Utilities.onlyContainsNumbers(MEPPhone)){
            this.MEPPhone = MEPPhone;
        }else {
            this.MEPPhone = "unknown";
        }

        this.MEPParty = Utilities.validParty(MEPParty.getPartyName(), partyList);
    }

    public String getMEPName() {
        return Utilities.max30Chars(MEPName);           //getter for mep name
    }

    public void setMEPName(String MEPName) {
        this.MEPName = Utilities.max30Chars(MEPName);         //setter for mep name
    }

    public String getMEPEmail() {
        if(Utilities.validEmail(MEPEmail)){
            return MEPEmail;
        }else {                                //getting the mep email
            return "invalid format email. Needs to contain . and @";
        }
    }

    public void setMEPEmail(String MEPEmail) {
        if(Utilities.validEmail(MEPEmail)){     //setter for mep email
            this.MEPEmail = MEPEmail;
        }
    }

    public String getMEPPhone() {
        if(Utilities.onlyContainsNumbers(MEPPhone)){        //getter for mepphone
            return  MEPPhone;
        }
        return  "unknown";
    }

    public void setMEPPhone(String MEPPhone) {
        if(Utilities.onlyContainsNumbers(MEPPhone)){    //setter for mep phone
            this.MEPPhone = MEPPhone;
        }
    }

    public Party getMEPParty() {
        return MEPParty;
    } //getter for mep party

    public void setMEPParty(String pName, PartyList partyListPopulated) {
        Party tempP = Utilities.validParty(pName, partyListPopulated);
        if(tempP != null)       //setter for mep party
            this.MEPParty = tempP;
    }

    public String toString() {
        return " Name of MEP: " + MEPName + "\n" +
                " Email of MEP: " + MEPEmail + "\n" +       //toString for meps
                " Phone Number :'" + MEPPhone + "\n" +
                " Party : " + MEPParty + "\n" +
               "-------------------------------------";
    }
}
