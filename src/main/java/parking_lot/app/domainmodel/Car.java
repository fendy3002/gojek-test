package parking_lot.app.domainmodel;

public class Car{
    public Car(String registrationNo, String colour){
        this.registrationNo = registrationNo;
        this.colour = colour;
    }

    private String registrationNo = "";
    private String colour = "";
    
    public String getRegistrationNo(){
        return this.registrationNo;
    };
    public String getColour(){
        return this.colour;
    };
}