package it.polimi.ingsw.view;

import java.util.Scanner;

public class ViewSelector {

    public String askTypeofView() {

        Scanner input = new Scanner(System.in);

        String selectedView;

        System.out.println("What kind of interface would you like to play with? CLI or GUI");

        while (true) {

            selectedView = input.nextLine();

            if (!(selectedView.equals("CLI") || selectedView.equals("GUI")))
                System.out.println("Invalid interface. Type CLI or GUI");

            else
                break;
        }

        return selectedView;

    }

}