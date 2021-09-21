/**
 * THE PARTY CLASS IS RESPONSIBLE FOR THE MAINTENCE
 * OF THE DIFFERENT PARTIES IN THE EUAPP
 * AND HANDLES THE PARTYNAME,PARTYLEADER,PARTYGENRE,NUMLOCALREPS
 *
 * @AUTHOR   JASON POWER (20076537)
 * @VESION   FINAL VERSION
 *
 */

public class Party {

    /**
     * Declaring private fields of type string called partyName,partyLeader,partyGenre
     * and one private int called numLocalReps
     */

    private String partyName,partyLeader,partyGenre;        //variables for party info
    private int numLocalReps;

    public Party(String partyName, String partyLeader, String partyGenre, int numLocalReps) {
        /**
         * Constructor that handles the validation for each field
         */
        this.partyName = Utilities.max30Chars(partyName);
        this.partyLeader = Utilities.max30Chars(partyLeader);     //constructor for party with validation
        this.partyGenre = Utilities.validGenre(partyGenre);

        if(Utilities.validIntNonNegative(numLocalReps)){
            this.numLocalReps = numLocalReps;
        }else{}

    }

    public String getPartyName() {
        /**
         * Getter for party name that passes the party name to the
         * utilities class to ensure the party name is of
         * maximum 30 letters.
         */
        return Utilities.max30Chars(partyName); //getter for party name
    }

    public void setPartyName(String partyName) {
        /**
         * setter for party name that passes the party name to the
         * utilities class to ensure the party name is of
         * maximum 30 letters.
         */
        this.partyName = Utilities.max30Chars(partyName);   //setter for party name
    }

    public String getPartyLeader() {
        /**
         * getter for party leader that passes the party leader to the
         * utilities class to ensure the party name is of
         * maximum 30 letters.
         */
        return Utilities.max30Chars(partyLeader);  //getter for party leader
    }

    public void setPartyLeader(String partyLeader) {
        /**
         * setter for party leader that passes the party leader to the
         * utilities class to ensure the party name is of
         * maximum 30 letters.
         */                                                                 //setter for party leader
        this.partyLeader = Utilities.max30Chars(partyLeader);
    }

    public String getPartyGenre() {
        /**
         * getter for party genre that passes it to the utilities class
         * that makes sure it is a valid genre
         *
         */
        return Utilities.validGenre(partyGenre);   //getter for party genre
    }

    public void setPartyGenre(String partyGenre) {
        /**
         * gstter for party genre that passes it to the utilities class
         * only if it is the proper case.
         */
        if(partyGenre.toUpperCase().equals(Utilities.validGenre(partyGenre))){
            this.partyGenre = Utilities.validGenre(partyGenre);     //setter for party genre
        }
    }

    public int getNumLocalReps() {
        /**
         * getter for number of local reps that passes it to the utilities class
         * to make sure the number is a non negative int i.e i > 0
         */
        if(Utilities.validIntNonNegative(numLocalReps)){
            return numLocalReps;                            //getter for number of local reps
        }else{
            return numLocalReps = 0;
        }
    }

    public void setNumLocalReps(int numLocalReps) {
        /**
         * setter for number of local reps that passes it to the utilities class
         * to make sure the number is a non negative int i.e i > 0
         */
        if(Utilities.validIntNonNegative(numLocalReps)){            //setter for number of local reps
            this.numLocalReps = numLocalReps;
        }
    }

    @Override
    public String toString() {
        /**
         * To string to return a human readable version of the parties information.
         */
        return "Party Name :" + partyName + "\n" +
                "Party Leader :" + partyLeader + "\n" +            //toString for parties
                "Party Genre :" + partyGenre + "\n" +
                "Number of local Reps :" + numLocalReps +"\n"+
                "--------------------------------------";
    }
}
