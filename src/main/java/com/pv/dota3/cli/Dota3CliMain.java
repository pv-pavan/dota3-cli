package com.pv.dota3.cli;

import com.pv.dota3.cli.graphics.GraphicAssets;
import com.pv.dota3.cli.graphics.GraphicsManager;
import com.pv.dota3.cli.graphics.TextUtil;
import com.pv.dota3.cli.manager.Dota3Manager;

import java.util.Scanner;

public class Dota3CliMain {

    public static void main(String[] args) {

        GraphicsManager.getInstance().drawFile(GraphicAssets.dota3_header);
        //Initialize game manager and draw start menu
        Dota3Manager.getInstance().initialize();

        while(true){
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            try {
                int op = Integer.parseInt(option);
                //process game command
                Dota3Manager.getInstance().processGameOption(op);
            }catch (NumberFormatException e){
                System.err.println("Choose a valid option...");
            }
        }
    }
}
