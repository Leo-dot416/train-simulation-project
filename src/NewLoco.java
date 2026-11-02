import uk.ac.leedsbeckett.oop.*;

public class NewLoco extends Locomotive
{
    private int locoID;

    public NewLoco(GameWorld world, int x, int y, boolean slow)
    {
        super(world, x, y);

        if (slow)
        {
            setAnimationStepPixels(2);
        }
    }

    public void setLocoID(int id)
    {
        this.locoID = id;
    }

    @Override
    public String toString()
    {
        return "NewLoco  LocoID: " + locoID
                + " Position: " + getCellPosition();
    }
}
