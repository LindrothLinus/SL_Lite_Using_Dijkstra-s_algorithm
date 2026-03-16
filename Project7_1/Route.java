package Project7_1;

import java.util.Objects;

public class Route {
    private String name;
    private String type;

    public Route(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Route r){
            return r.name.equals(name) && r.type.equals(type);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,type);
    }

    @Override
    public String toString() {
        return "[Linje:" + name +" Typ:"+type+"]"; 
    }
}
