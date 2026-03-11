package Project_7;

public class Route {
    private String id;
    private String name;
    private String routeType;

    public Route(String id, String name,String routeType){
        this.id=id;
        this.name = name;
        this. routeType= routeType;
    }

    public String getId(){
        return id;
    }

    //TODO: Fixa bättre hashCode och equals
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Route r){
            return r.id.equals(id);
        }
        return false;
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
            return 0;
        }
    }
}
