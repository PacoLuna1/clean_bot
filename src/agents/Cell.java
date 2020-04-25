package agents;

public class Cell{
    Boolean clean;
    public Cell(){
        this.clean = true;
    }

    public void setCell(Boolean aux){
        this.clean = aux;
    }

    public Boolean getCell(){
        return this.clean;
    }
}
