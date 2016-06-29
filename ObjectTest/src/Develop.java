/**
 * Created by hyunji on 2016. 6. 29..
 */
public class Develop {

    private String skill;
    private int carri;

    public  Develop(String skill , int carri){

        this.skill = skill;
        this.carri = carri;
    }

    public String getSkill(){
        return skill;
    }

    public void setSkill(String skill){
        this.skill = skill;
    }

    public int getCarri(){
        return new Integer(carri);
    }

    public void setCarri(int carri){
        this.carri = carri;
    }

    @Override
    public String toString(){
        return getSkill() + " ;;  " +  getCarri();
    }


}

