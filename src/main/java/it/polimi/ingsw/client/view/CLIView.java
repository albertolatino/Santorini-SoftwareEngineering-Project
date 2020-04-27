package it.polimi.ingsw.client.view;

import it.polimi.ingsw.serializableObjects.CellClient;
import it.polimi.ingsw.server.model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CLIView {

    private Scanner input;
    private Scanner intInput;
    private final BoardClient board;// this will contain a copy of the Model's map and each cell will be update if there are any changes
    private String playerNickname;
    //to be assigned when setPlayer of ViewClient is deserialized

    /**
     * This is the CLIView constructor.
     */
    public CLIView() {

        board = new BoardClient();
        input = new Scanner(System.in);
        intInput = new Scanner(System.in);
    }

    //called by clientView
    public void setPlayer(String nickname) {
        this.playerNickname = nickname;
    }

    /**
     * This method displays to the user Initial Game Interface
     */
    public void beginningView() {

        String startString;

        System.out.println("WELCOME TO ");

        santoriniASCII();

        do {

            System.out.println("--type START to play--");
            startString = input.nextLine().toUpperCase();

        } while (!startString.equals("START"));

    }


    /**
     * @return The number of players.
     */
    public int askNumberOfPlayers() {

        int insertedNumberOfPlayers;


        System.out.println("Choose the number of players.");
        while (true) {
            try {
                insertedNumberOfPlayers = intInput.nextInt();

                if (insertedNumberOfPlayers != 2 && insertedNumberOfPlayers != 3)
                    System.out.println("You can only play with 2 or 3 players. Try again.");
                else
                    return insertedNumberOfPlayers;

            } catch (InputMismatchException ex) {
                System.out.println("Invalid input: type 2 or 3. Try again.");
                intInput.next();
            }
        }
    }


    private void santoriniASCII() {

        System.out.println("███████╗ █████╗ ███╗   ██╗████████╗ ██████╗ ██████╗ ██╗███╗   ██╗██╗");
        System.out.println("██╔════╝██╔══██╗████╗  ██║╚══██╔══╝██╔═══██╗██╔══██╗██║████╗  ██║██║");
        System.out.println("███████╗███████║██╔██╗ ██║   ██║   ██║   ██║██████╔╝██║██╔██╗ ██║██║");
        System.out.println("╚════██║██╔══██║██║╚██╗██║   ██║   ██║   ██║██╔══██╗██║██║╚██╗██║██║");
        System.out.println("███████║██║  ██║██║ ╚████║   ██║   ╚██████╔╝██║  ██║██║██║ ╚████║██║");
        System.out.println("╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝╚═╝");
        System.out.println();

    }


    /**
     * This method asks the player to set his worker initial position.
     *
     * @param workerSex This is the sex of the worker to be placed on the board.
     * @return Array with x,y coordinates of the chosen position.
     */
    public int[] askInitialWorkerPosition(String workerSex)
            throws InputMismatchException {

        while (true) {
            try {
                int[] initialWorkerPosition = new int[2];

                if (workerSex.equals("MALE")) {
                    System.out.println(playerNickname + ", set your male worker's position in coordinates.");

                    initialWorkerPosition[0] = intInput.nextInt();
                    initialWorkerPosition[1] = intInput.nextInt();
                    return initialWorkerPosition;
                } else if (workerSex.equals("FEMALE")) {
                    System.out.println(playerNickname +
                            ", set your female worker's position in coordinates.");
                    initialWorkerPosition[0] = intInput.nextInt();
                    initialWorkerPosition[1] = intInput.nextInt();
                    return initialWorkerPosition;
                } else
                    System.out.println("Invalid worker's sex"); //never executed

            } catch (InputMismatchException ex) {
                System.out.println("You must type the coordinates separated by a space.");
                intInput.next();
            }
        }
    }

    public void invalidInitialWorkerPosition() {
        System.out.println("Not valid or available position. Choose another place!");
        intInput.next();
        input.next();
    }


    public String askPlayerNickname() {

        System.out.println("Choose your nickname.");
        return input.nextLine();
    }

    public String askPlayerColor() {

        System.out.println(playerNickname + ", choose your color.");
        return input.nextLine().toUpperCase();
    }


    /**
     * @return The name of the chosen God.
     */
    public String askPlayerGod() {

        System.out.println(playerNickname + ", choose your god by typing his name.");
        return input.nextLine();
    }

    public void playerChoseInvalidGod() {
        System.out.println("Your god is not available or has already been chosen.");
    }

    private void printChallenger(int numOfPlayers) {
        System.out.println();
        System.out.println(playerNickname + ", you are the Challenger. Select "
                + numOfPlayers + " Gods for this game.");

    }

    public String getGodFromChallenger(int numOfPlayers, int alreadyChosenGods) {
        if (alreadyChosenGods == 0)
            printChallenger(numOfPlayers);
        else
            System.out.println(numOfPlayers - alreadyChosenGods + " Gods left to choose.");

        return input.nextLine();

    }

    public String challengerChooseStartPlayer() {

        System.out.println("\n" + playerNickname + ", choose the first player to start! Type his nickname:");

        return input.nextLine();

    }

    public void invalidStartPlayer() {
        System.out.println("Invalid nickname. It must be an existing nickname.");
    }

    public void notAvailableColor() {
        System.out.println("This color is not available!");
    }

    public void notAvailableNickname() {
        System.out.println("This nickname is not available!");
    }


    public String askChosenWorker() {

        String chosenWorkerSex;
        //Il fatto che la view per stampare il nickname del player debba andare chiamare
        // prima il controller che poi a sua volta chiama il model....boh?
        System.out.println(playerNickname + ", it's your turn!");

        while (true) {
            System.out.println("Type MALE or FEMALE to choose one of your workers.");

            chosenWorkerSex = input.nextLine().toUpperCase();

            if (chosenWorkerSex.equals("MALE") || chosenWorkerSex.equals("FEMALE"))
                return chosenWorkerSex;

            else
                System.out.println("Invalid input.");

        }

    }


    /**
     * Allows to print the ERROR to the screen
     */
    public void printErrorScreen() {
        System.out.println("An error has occurred. Retry.");
    }

    /**
     * Prints to screen that one of the player has won the game
     */
    public void winningView(String winnerNickname) {
        System.out.println("\n\n\n"
                + winnerNickname + " HAS WON THIS GAME!!!\n\nGAME ENDED\n\nSEE YOU!");
    }

    public void unableToMoveLose() {
        System.out.println(playerNickname + ", Game Over for you!");
    }

    public void unableToBuildLose() {
        System.out.println(playerNickname + ", both of your workers can't move anywhere.");
        System.out.println("You have lose the game.");
    }


    /**
     * This method prints an updated version of the Board, depending on the Class' parameter "mymap".
     */
    public void printMap() {

        final String LINE_SEPARATOR = CLIColor.ANSI_GREEN + "+-------+-------+-------+-------+-------+" + CLIColor.COLOR_RESET + "%n";
        final String SPACE_SEPARATOR = CLIColor.ANSI_GREEN + "+       +       +       +       +       +" + CLIColor.COLOR_RESET + "%n";

        for (int i = 0; i < Board.SIDE; i++) {

            System.out.printf(LINE_SEPARATOR);//Border
            printMapLine(i);//content of game
            System.out.printf(SPACE_SEPARATOR);//space
        }

        System.out.printf(LINE_SEPARATOR);

    }

    /**
     * Prints a line of the map, showing eventual buildings and workers of the line.
     *
     * @param lineNumber Represents the line which content will be displayed.
     */
    private void printMapLine(int lineNumber) {

        for (int i = 0; i < Board.SIDE; i++) {

            boolean additionalSpace = true;
            System.out.printf(CLIColor.ANSI_GREEN + "+" + CLIColor.COLOR_RESET);
            System.out.printf(" ");//1

            //Place where eventual buildings will be printed

            if (board.findCell(lineNumber, i).hasDome())//if cell has dome
                System.out.printf("D");

            else {
                //if cell has not dome

                if (board.findCell(lineNumber, i).getCellLevel() == 0)
                    System.out.printf(" ");//if there is no building, prints nothing

                else
                    System.out.printf("%d", board.findCell(lineNumber, i).getCellLevel());   // if there is a building, prints its level
            }

            //SPACE

            System.out.printf(" ");//3
            System.out.printf(" ");//3bis


            //PLACE of cell (4) that prints eventual presence of a worker with its parameter(SEX;COLOR)


            if (!board.findCell(lineNumber, i).hasWorker()) {
                System.out.printf(" ");
                System.out.printf(" ");//5
            } else {

                additionalSpace = false;
                String workerColor = board.findCell(lineNumber, i).getWorkerClient().getWorkerColor();
                String workerSex = board.findCell(lineNumber, i).getWorkerClient().getWorkerSex();

                if (workerColor.equals("BLUE") && workerSex.equals("MALE"))
                    System.out.printf(CLIColor.ANSI_BLUE + " M⃣ " + CLIColor.COLOR_RESET);
                else if (workerColor.equals("BLUE") && workerSex.equals("FEMALE"))
                    System.out.printf(CLIColor.ANSI_BLUE + " F⃣ " + CLIColor.COLOR_RESET);
                else if (workerColor.equals("WHITE") && workerSex.equals("MALE"))
                    System.out.printf(CLIColor.ANSI_WHITE + " M⃣ " + CLIColor.COLOR_RESET);
                else if (workerColor.equals("WHITE") && workerSex.equals("FEMALE"))
                    System.out.printf(CLIColor.ANSI_WHITE + " F⃣ " + CLIColor.COLOR_RESET);
                else if (workerColor.equals("BEIGE") && workerSex.equals("MALE"))
                    System.out.printf(CLIColor.ANSI_BEIGE + " M⃣ " + CLIColor.COLOR_RESET);
                else if (workerColor.equals("BEIGE") && workerSex.equals("FEMALE"))
                    System.out.printf(CLIColor.ANSI_BEIGE + " F⃣ " + CLIColor.COLOR_RESET);

            }

            if (additionalSpace)
                System.out.printf(" ");//6


        }

        System.out.printf(CLIColor.ANSI_GREEN + "+" + CLIColor.COLOR_RESET);
        System.out.printf("%n");

    }

    public void update(CellClient toUpdateCell) {
        board.update(toUpdateCell);
    }

    public void printAllGods(ArrayList<String> godsNameAndDescription) {
        System.out.println("\nThese are all the available gods:\n");

        for (String god : godsNameAndDescription)
            System.out.println(god);

    }

    public void challengerError() {
        System.out.println("This god doesn't exist.");
    }


    public void printChosenGods(ArrayList<String> chosenGods) {

        System.out.print("\nAvailable Gods: ");

        int index = 0;

        for (String god : chosenGods) {
            index++;
            System.out.print(god);
            if (index < chosenGods.size())
                System.out.print(", ");
            else
                System.out.println();
        }
        System.out.println("");
    }


    public void selectedWorkerCannotMove(String sex) {
        sex = sex.toLowerCase();
        String otherSex;

        if (sex.equals("male"))
            otherSex = "female";
        else
            otherSex = "male";

        System.out.println("Your " + sex + " worker cannot move anywhere. You must move with your "
                + otherSex + " worker.");
    }

    public void selectedWorkerCannotBuild(String sex) {
        sex = sex.toLowerCase();
        String otherSex;

        if (sex.equals("male"))
            otherSex = "female";
        else
            otherSex = "male";

        System.out.println("Your " + sex + " worker cannot build anywhere. You must build with your "
                + otherSex + " worker.");
    }

    public String askTypeofView() {

        String selectedView;

        System.out.println("What kind of interface would you like to play with? CLI or GUI");

        while (true) {

            selectedView = input.nextLine().toUpperCase();

            if (!(selectedView.equals("CLI") || selectedView.equals("GUI")))
                System.out.println("Invalid interface. Type CLI or GUI");

            else
                break;
        }

        return selectedView;

    }


    /**
     * This method asks the user to insert the position where he wants to build.
     *
     * @return The compass direction of the place where to build.
     */
    public String askBuildingDirection() {

        String selectedBuildingDirection;

        System.out.println("Where do you want to build? Insert a cardinal point!");

        while (true) {
            selectedBuildingDirection = input.nextLine().toUpperCase();

            if (selectedBuildingDirection.equals("N")
                    || selectedBuildingDirection.equals("NE")
                    || selectedBuildingDirection.equals("E")
                    || selectedBuildingDirection.equals("SE")
                    || selectedBuildingDirection.equals("S")
                    || selectedBuildingDirection.equals("SW")
                    || selectedBuildingDirection.equals("W")
                    || selectedBuildingDirection.equals("NW")
                    || selectedBuildingDirection.equals("U")) {

                return selectedBuildingDirection;

            } else
                System.out.println("Invalid Direction. You must use: N, NE, E, SE, S, SW, W, NW (or 'U' to build underneath");
        }
    }


    /**
     * This method asks to Atlas' owner to insert the position where he wants to build and what type of building.
     *
     * @return The compass direction of the place where to build.
     */
    public String[] askBuildingDirectionAtlas() {

        String[] selectedBuildingDirection = new String[2];

        System.out.println("Where do you want to build? Insert a cardinal point!");

        while (true) {
            selectedBuildingDirection[0] = input.nextLine().toUpperCase();

            if (selectedBuildingDirection[0].equals("N")
                    || selectedBuildingDirection[0].equals("NE")
                    || selectedBuildingDirection[0].equals("E")
                    || selectedBuildingDirection[0].equals("SE")
                    || selectedBuildingDirection[0].equals("S")
                    || selectedBuildingDirection[0].equals("SW")
                    || selectedBuildingDirection[0].equals("W")
                    || selectedBuildingDirection[0].equals("NW")) {

                while (true) {
                    System.out.println("Now tell me what do you want to build: type  <B> for Block or <D> for Dome: ");
                    selectedBuildingDirection[1] = input.nextLine().toUpperCase();

                    if (selectedBuildingDirection[1].equals("B") || selectedBuildingDirection[1].equals("D"))
                        return selectedBuildingDirection;

                    else
                        System.out.println("Invalid Building. You must type B or D.\n");
                }
            } else
                System.out.println("Invalid Direction. You must use: N, NE, E, SE, S, SW, W, NW.");

        }
    }


    /**
     * This method asks the user to insert the direction of his next movement.
     *
     * @return The compass direction of the movement.
     */
    public String askMovementDirection() {

        String selectedDirection;
        System.out.println("Where do you want to move your worker?");

        while (true) {
            selectedDirection = input.nextLine().toUpperCase();

            if (selectedDirection.equals("N") || selectedDirection.equals("NE") || selectedDirection.equals("E") || selectedDirection.equals("SE") || selectedDirection.equals("S") || selectedDirection.equals("SW") || selectedDirection.equals("W") || selectedDirection.equals("NW"))
                return selectedDirection;

            else
                System.out.println("Invalid Direction. You must type: N, NE, E, SE, S, SW, W, NW");
        }
    }


    /**
     * Allows to get the input of the player to move again.
     *
     * @return The will of the player on keeping going moving his worker on the board.
     */
    public String askMoveAgain() {

        System.out.println("Do you want to move again your Worker?");
        return playerAnswerYN();
    }


    /**
     * Allows to get the input of the player to jump to an higher level.
     *
     * @return The will of the player to reach an higher level.
     */
    public String askWantToMoveUp() {

        System.out.println("Do you want to move up?");
        return playerAnswerYN();
    }


    /**
     * Allows to get the input of the player to move an enemy's worker.
     *
     * @return The will of the player to move an enemy's worker
     */
    public String askWantToMoveEnemy() {

        System.out.println("Do you want to force your near enemy to move?");
        return playerAnswerYN();
    }


    /**
     * Allows to move one worker's enemy.
     *
     * @param enemyWorkers It's the list of the neighbour movable enemy workers.
     * @param myWorker     It's the chosen worker of the current player.
     * @return The Worker to move selected by the player.
     */
    public String askWorkerToMove(ArrayList<Worker> enemyWorkers, Worker myWorker) {

        ArrayList<String> presentPositions = printFoundEnemiesPosition(enemyWorkers, myWorker);

        if (presentPositions.size() == 0) {
            System.out.println("There are no enemy workers around...");
            return null;
        }

        System.out.println("These are the positions of the enemy workers nearby. Choose one:");

        while (true) {
            String chosenEnemyDirection = input.nextLine();

            if (presentPositions.contains(chosenEnemyDirection))
                return chosenEnemyDirection;

            System.out.println("Your choice must be between the ones above!\nOtherwise you can choose to let them stay where they are:\n'Y' to retry, 'N' to quit the forced move ");
            String playerAnswer = input.nextLine().toUpperCase();

            if (playerAnswer.equals("N"))
                return null;

            System.out.println("Type again your choice: ");
        }
    }


    /**
     * Helps to show the positions of the neighboring workers
     *
     * @param enemyWorkers It's the list of the neighbour movable enemy workers.
     * @param myWorker     It's the chosen worker of the current player.
     * @return The position of the selected worker to move.
     */
    private ArrayList<String> printFoundEnemiesPosition(ArrayList<Worker> enemyWorkers, Worker myWorker) {
        int myWorkerX = myWorker.getPosition().getX();
        int myWorkerY = myWorker.getPosition().getY();
        ArrayList<String> presentPositions = new ArrayList<>();

        for (Worker enemyWorker : enemyWorkers) {
            if (myWorkerX > enemyWorker.getPosition().getX()) {
                if (myWorkerY > enemyWorker.getPosition().getY()) {
                    System.out.println("NW");
                    presentPositions.add("NW");
                } else if (myWorkerY == enemyWorker.getPosition().getY()) {
                    System.out.println("N");
                    presentPositions.add("N");
                } else {
                    System.out.println("NE");
                    presentPositions.add("NE");
                }
            } else if (myWorkerX == enemyWorker.getPosition().getX()) {
                if (myWorkerY > enemyWorker.getPosition().getY()) {
                    System.out.println("W");
                    presentPositions.add("W");
                } else if (myWorkerY < enemyWorker.getPosition().getY()) {
                    System.out.println("E");
                    presentPositions.add("E");
                } else {
                    System.out.println("ERROR : IT'S THE SAME POSITION OF WHERE I AM!!!");
                    return null;
                }
            } else {
                if (myWorkerY > enemyWorker.getPosition().getY()) {
                    System.out.println("SW");
                    presentPositions.add("SW");
                } else if (myWorkerY == enemyWorker.getPosition().getY()) {
                    System.out.println("S");
                    presentPositions.add("S");
                } else {
                    System.out.println("SE");
                    presentPositions.add("SE");
                }
            }
        }
        return presentPositions;
    }


    /**
     * Says that the worker can build under himself/herself.
     * This is allowed only when playing with Zeus.
     */
    public void printBuildUnderneath() {
        System.out.println("Remember that you can build underneath!\n" +
                "To do that, the direction for the build is 'U'\n");
    }


    /**
     * The name of the method describes itself.
     *
     * @return The will of the player to build again.
     */
    public String askBuildAgainHephaestus() {

        System.out.println("You are allowed to build another time, " +
                "but ONLY in the same position you built before");
        return playerAnswerYN();
    }


    /**
     * The name of the method describes itself.
     *
     * @return The will of the player to build again.
     */
    public String askBuildAgainDemeter() {

        System.out.println("You are allowed to build another time, but NOT in the same position you built before");
        return playerAnswerYN();
    }


    /**
     * The name of the method describes itself.
     *
     * @return The will of the player to build again.
     */
    public String askBuildAgainHestia() {

        System.out.println("You are allowed to build another time, but NOT in the border position");
        return playerAnswerYN();
    }

    /**
     * The name of the method describes itself.
     *
     * @return The will of the player to build before moving.
     */
    public String askBuildPrometheus() {

        System.out.println("You are allowed to build before you move");
        return playerAnswerYN();
    }


    /**
     * Allows to get the answer of a player to a question.
     *
     * @return Y for a positive answer, N for a negative one.
     */
    private String playerAnswerYN() {
        String answer;
        System.out.println("Type <Y> for Yes,  <N> for No :");

        while (true) {
            answer = input.nextLine().toUpperCase();
            if (answer.equals("Y") || answer.equals("N"))
                return answer;
            System.out.println("Incorrect answer: You need to type 'Y' or 'N'");
        }
    }


    /**
     * Points out the player cannot move in a certain position.
     */
    public void printMoveErrorScreen() {
        System.out.println("You're not allowed to move there.");
    }


    /**
     * Asks the player if he still wants to move during this turn.
     *
     * @return Y for a positive answer, N for a negative one.
     */
    public String printMoveDecisionError() {
        printMoveErrorScreen();
        System.out.println("Do you still want to move?");
        return playerAnswerYN();
    }


    /**
     * Asks the player if he still wants to build during this turn.
     *
     * @return Y for a positive answer, N for a negative one.
     */
    public String printBuildDecisionError() {
        System.out.println("Do you still want to build?");
        return playerAnswerYN();
    }


    /**
     * Points out a player is not allowed to build.
     */
    public void printBuildGeneralErrorScreen() {
        System.out.println("You're not allowed to build!");

    }


    /**
     * Points out a player is not allowed to build a block in a certain position.
     */
    public void printBuildBlockErrorScreen() {
        System.out.println("You're not allowed to build a BLOCK there.");
    }


    /**
     * Points out that a player is not allowed to build again in a certain position.
     */
    public void printBuildInSamePositionScreen() {
        System.out.println("You're not allowed to build again there.");
    }


}