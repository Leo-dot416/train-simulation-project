import uk.ac.leedsbeckett.oop.*;

public class MyRailway extends OOPrailwaySim
{
    private NewLoco loco1, loco2;
    private GameWorld world;

    private boolean gameStarted = false;
    private int tickCounter = 0;

    private int loco1Increase = 2;
    private int loco2Increase = 3;

    private void setupInitialState()
    {
        loco1 = new NewLoco(world, 4,3, false);

        int locoId = addLocomotive(loco1);

        loco1.setLocoID(locoId);

        addCarriageToLocomotive(locoId, new Carriage(world));

        about("Leandro Di Trapani");
    }

    public MyRailway()
    {
        super();
        world = getWorld();
        setVisible(true);

        System.out.println("starting");

        setupInitialState();
        captureVehiclesState();
    }

    private boolean checkDerailments()
    {
        for (int i = 1; i <= getLocomotives().size(); i++)
        {
            Locomotive loco = getLocomotives().get(i - 1);
            if (loco.isDerailed())
            {
                return true;
            }
        }
        return false;
    }

    public void onTick()
    {
        super.onTick();

        if(gameStarted)
        {
            tickCounter++;

            if(tickCounter % 5 == 0)
            {
                increaseLocoSpeeds();
            }
        }

        if (checkDerailments())
        {
            stopSimulation();

            displayMessage(
                    "Derailment/Crash detected!\nYou should reset the simulation using 'reset'",
                    ERROR
            );
        }

        String output = "";

        for (Locomotive loco : getLocomotives())
        {
            output += loco.toString() + "\n";
        }

        displayOutput(output);
    }

    public void locoGame()
    {
        loco2 = new NewLoco(world, 4,11, false);
        int locoId = addLocomotive(loco2);
        loco2.setLocoID(locoId);
        addCarriageToLocomotive(locoId, new Carriage(world));

        setLocomotiveSpeed(1, 5);
        setLocomotiveSpeed(2, 2);

        captureVehiclesState();
        gameStarted = true;
        tickCounter = 0;
        startSimulation();
    }

    private void increaseLocoSpeeds()
    {
        int loco1Speed = loco1.getAnimationStepPixels();
        int loco2Speed = loco2.getAnimationStepPixels();

        loco1Speed += loco1Increase;
        loco2Speed += loco2Increase;

        if(loco1Speed > 15)
        {
            loco1Speed = 15;
        }

        if(loco2Speed > 15)
        {
            loco2Speed = 15;
        }

        setLocomotiveSpeed(1, loco1Speed);
        setLocomotiveSpeed(2, loco2Speed);
    }

    public void processCommand(String commandLine)
    {
        String command;
        String[] sot = commandLine.split(" ");
        command = sot[0].toLowerCase();
        System.out.println("Command typed into text field was :" + command);

        switch(command)
        {
            case "about":
            {
                if (sot.length != 1)
                {
                    displayMessage("Usage: about\n No Parameters needed", ERROR);
                    break;
                }
                about();
                break;
            }
            case "start":
            {
                if (sot.length != 1)
                {
                    displayMessage("Usage: start\n No Parameters needed", ERROR);
                    break;
                }
                startSimulation();
                break;
            }

            case "stop":
            {
                if (sot.length != 1)
                {
                    displayMessage("Usage: stop\n No Parameters needed", ERROR);
                    break;
                }
                stopSimulation();
                break;
            }

            case "addloco":
            {
                if (sot.length != 3)
                {
                    displayMessage("Usage: addloco <x> <y>", ERROR);
                    break;
                }

                try
                {
                    int xValue = Integer.parseInt(sot[1]);
                    int yValue = Integer.parseInt(sot[2]);

                    if (xValue < 0 || yValue < 0)
                    {
                        displayMessage("Coordinates cannot be negative", ERROR);
                        break;
                    }

                    NewLoco loco = new NewLoco(world, xValue, yValue, false);

                    int locoId = addLocomotive(loco);
                    loco.setLocoID(locoId);
                    captureVehiclesState();
                    break;
                }
                catch (NumberFormatException e)
                {
                    displayMessage("Error: Insert integers!", ERROR);
                    break;
                }
                catch (GameWorldException e)
                {
                    displayMessage("Error: Cannot place locomotive: not on rails!", ERROR);
                    break;
                }
            }

            case "attachcarriage":
            {
                if (sot.length != 2)
                {
                    displayMessage("Usage: attachcarriage <loco>", ERROR);
                    break;
                }
                try
                {
                    int locoId = Integer.parseInt(sot[1]);

                    if (locoId < 1)
                    {
                        displayMessage("LocoID must be greater than 0", ERROR);
                        break;
                    }

                    if (locoId > getLocomotives().size())
                    {
                        if (getLocomotives().size() == 1)
                        {
                            displayMessage(
                                    "Locomotive ID is not correct\n There is only "
                                            + getLocomotives().size()
                                            + " locomotive.", ERROR);
                            break;
                        }
                        else
                        {
                            displayMessage(
                                    "Locomotive ID is not correct\n There are only "
                                            + getLocomotives().size()
                                            + " locomotives.", ERROR);
                            break;
                        }
                    }
                    addCarriageToLocomotive(locoId, new Carriage(world));
                    captureVehiclesState();
                    break;
                }
                catch (NumberFormatException e)
                {
                    displayMessage("Error: Insert integers!", ERROR);
                    break;
                }
            }

            case "detachcarriage":
            {
                if (sot.length != 2)
                {
                    displayMessage("Usage: detachcarriage <loco>", ERROR);
                    break;
                }

                try
                {
                    int locoId = Integer.parseInt(sot[1]);

                    if (locoId < 1)
                    {
                        displayMessage("LocoID must be greater than 0", ERROR);
                        break;
                    }

                    if (locoId > getLocomotives().size())
                    {
                        if (getLocomotives().size() == 1)
                        {
                            displayMessage(
                                    "Locomotive ID is not correct\n There is only "
                                            + getLocomotives().size()
                                            + " locomotive.", ERROR);
                            break;
                        }
                        else
                        {
                            displayMessage(
                                    "Locomotive ID is not correct\n There are only "
                                            + getLocomotives().size()
                                            + " locomotives.", ERROR);
                            break;
                        }
                    }


                    deleteCarriage(locoId);
                    break;
                }
                catch (NumberFormatException e)
                {
                    displayMessage("Error: Insert integers!", ERROR);
                    break;
                }
            }

            case "speed":
            {
                if (sot.length != 3)
                {
                    displayMessage("Usage: speed <loco> <speed>", ERROR);
                    break;
                }

                try
                {
                    int locoId = Integer.parseInt(sot[1]);
                    int speed = Integer.parseInt(sot[2]);

                    if (locoId > getLocomotives().size())
                    {
                        if (getLocomotives().size() == 1)
                        {
                            displayMessage(
                                    "Locomotive ID is not correct\n There is only "
                                            + getLocomotives().size()
                                            + " locomotive.", ERROR);

                            break;
                        }
                        else
                        {
                            displayMessage(
                                    "Locomotive ID is not correct\n There are only "
                                            + getLocomotives().size()
                                            + " locomotives.", ERROR);

                            break;
                        }
                    }

                    if (locoId < 1)
                    {
                        displayMessage("LocoID must be greater than 0", ERROR);
                        break;
                    }

                    if (speed <= 0)
                    {
                        displayMessage("Speed cannot be less than 1", ERROR);
                        break;
                    }

                    if (speed > 40)
                    {
                        displayMessage("The locomotive will derail!", WARNING);
                    }

                    setLocomotiveSpeed(locoId, speed);

                    break;
                }
                catch (NumberFormatException e)
                {
                    displayMessage("Error: Insert integers!", ERROR);
                    break;
                }
            }

            case "crossing":
            {
                if (sot.length != 2)
                {
                    displayMessage("Usage: crossing <crossing>", ERROR);
                    break;
                }

                try
                {
                    int crossing = Integer.parseInt(sot[1]);

                    if (crossing > 1 || crossing < 0)
                    {
                        displayMessage(
                                "The crossing ID you inserted is not present", ERROR);
                        break;
                    }

                    toggleCrossing(crossing);

                    break;
                }
                catch (NumberFormatException e)
                {
                    displayMessage("Error: Insert integers!", ERROR);
                    break;
                }
            }

            case "reset":
            {
                if (sot.length != 1)
                {
                    displayMessage("Usage: reset\n No Parameters needed", ERROR);
                    break;
                }

                resetSimulation();

                loco1 = (NewLoco)getLocomotives().get(0);

                if(getLocomotives().size() > 1)
                {
                    loco2 = (NewLoco)getLocomotives().get(1);
                }

                gameStarted = false;

                tickCounter = 0;

                for (int i = 1; i <= getLocomotives().size(); i++)
                {
                    setLocomotiveSpeed(i, 8);
                }

                break;
            }

            case "addslowloco":
            {
                if (sot.length != 3)
                {
                    displayMessage("Usage: addslowloco <x> <y>", ERROR);
                    break;
                }

                try
                {
                    int xValue = Integer.parseInt(sot[1]);
                    int yValue = Integer.parseInt(sot[2]);

                    NewLoco slowLoco = new NewLoco(world, xValue, yValue, true);

                    int locoId = addLocomotive(slowLoco);

                    slowLoco.setLocoID(locoId);

                    captureVehiclesState();

                    break;
                }
                catch (NumberFormatException e)
                {
                    displayMessage("Error: Insert integers!", ERROR);
                    break;
                }
            }

            case "startgame":
            {
                locoGame();
                break;
            }

            default:
            {
                displayMessage("Unknown command: " + command, ERROR);
                break;
            }
        }
    }
}