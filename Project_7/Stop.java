package Project_7;

public class Stop {
    String id;
    String name;
    double lat,lon;

    public Stop(String id,String name, double lat, double lon){
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }



    //TODO: Fixa bättre hashCode och equals
    @Override
    public boolean equals(Object other){
        if(other instanceof Stop s){
            return s.id.equals(id);
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        try{
            int code = 0;
            for(char c : id.toCharArray()){
                code += c*7;
            }
            return code;
        }catch(NumberFormatException e){
            System.out.println(id);
            return 0;
        }
    }
}