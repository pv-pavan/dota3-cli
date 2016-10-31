package com.pv.dota3.cli.manager;

import com.pv.dota3.cli.game.ExplorableGame;
import com.pv.dota3.cli.game.Game;
import com.pv.dota3.cli.game.enums.FightResultCode;
import com.pv.dota3.cli.game.enums.GameType;
import com.pv.dota3.cli.game.factory.GameFactory;
import com.pv.dota3.cli.profile.Profile;
import com.pv.dota3.cli.game.factory.GameGenerator;
import com.pv.dota3.cli.menu.beans.Menu;
import com.pv.dota3.cli.menu.enums.MenuOptionType;
import com.pv.dota3.cli.manager.enums.GameState;
import com.pv.dota3.cli.menu.beans.MenuOption;
import com.pv.dota3.cli.persistence.PersistenceType;
import com.pv.dota3.cli.profile.services.ProfileService;
import com.pv.dota3.cli.profile.services.ProfileServiceFactory;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by pv on 15/10/16.
 */
public class Dota3Manager {
    private static Dota3Manager ourInstance = new Dota3Manager();

    public static Dota3Manager getInstance() {
        return ourInstance;
    }


    private Stack<GameState> gameStack;
    private Menu masterMenu;
    private Profile currentProfile;
    private Game currentGame;
    private ProfileService profileService;

    private Dota3Manager() {
    }

    /**
     * Initialize game manager
     */
    public void initialize(){
        this.profileService = ProfileServiceFactory.getProfileService(PersistenceType.FILE);
        this.masterMenu = new Menu();
        this.gameStack = new Stack<>();
        this.gameStack.push(GameState.INITIAL);
        printGameMenu();
    }

    /**
     * process chosen menu option
     * menu option is contextual to the game state, see @GameState
     * @param optionIndex
     */
    public void processGameOption(int optionIndex){
        MenuOption selectedOption = masterMenu.getOptions().get(optionIndex);
        if(selectedOption == null){
            System.err.println("Invalid selection. Choose a valid option...");
        }else {
            Scanner scanner = new Scanner(System.in);
            String cName = null;
            Profile profile = null;


            switch (selectedOption.getType()) {

                case CREATE_PROFILE:
                    System.out.print("Enter your character name: ");
                    cName = scanner.nextLine();
                    profile = this.profileService.create(cName);
                    currentProfile = profile;
                    this.gameStack.push(GameState.PROFILE);
                    break;

                case PROFILE:
                    cName = selectedOption.getLabel();
                    profile = this.profileService.getByName(cName);
                    if(profile == null){
                        System.err.println("Oops. Something wrong with the profile you want to use. Try creating a new one...");
                        return;
                    }
                    currentProfile = profile;
                    this.gameStack.push(GameState.PROFILE);
                    break;

                case CREATE_GAME:
                    Game g = GameFactory.generateGame(GameType.MAP_EXPORING);
                    currentProfile.getGames().add(g);
                    currentGame = g;

                    this.profileService.save(currentProfile);
                    this.gameStack.push(GameState.GAME);
                    break;

                case GAME:
                    String gName = selectedOption.getLabel();
                    for(Game ga: currentProfile.getGames()){
                        if(ga.getName().equals(gName)){
                            currentGame = ga;
                            break;
                        }
                    }
                    this.gameStack.push(GameState.GAME);
                    break;

                case GAME_EXPLORE:
                    currentGame.explore();
                    if(!this.gameStack.peek().equals(GameState.GAME_FIGHT))
                        this.gameStack.push(GameState.GAME_FIGHT);
                    break;

                case GAME_FIGHT:
                    FightResultCode resultCode = currentGame.fight();
                    if(resultCode == FightResultCode.LOST || (resultCode == FightResultCode.WON && currentGame.isFinalCheckPoint())){
                        currentProfile.getGames().remove(currentGame);

                        //take game stack to profile level -- you have to choose another game or create one, as the game you were playing is over.
                        while(this.gameStack.peek() != GameState.PROFILE){
                            this.gameStack.pop();
                        }
                        this.currentGame = null;
                    }else{
                        //you won a fight, go back one level, to explore again
                        currentProfile.getGames().add(currentGame);
                        this.gameStack.pop();
                    }

                    this.profileService.save(currentProfile);
                    break;

                case SAVE_EXIT:
                    currentProfile.getGames().add(currentGame);
                    this.profileService.save(currentProfile);

                    while(this.gameStack.peek() != GameState.PROFILE){
                        this.gameStack.pop();
                    }
                    break;
                case DOTA_EXIT:
                    System.out.println("Exiting Dota3. See you back soon.");
                    System.exit(0);

            }
        }
        printGameMenu();
    }


    private GameState getGameState(){
        return this.gameStack.peek();
    }

    /**
     * Add menu state based on game state
     */
    private void refreshState(){

        switch (getGameState()){
            case INITIAL:
                masterMenu = new Menu();
                List<Profile> availableProfiles = profileService.listProfiles();
                if(availableProfiles.size()>0) System.out.println("Choose a existing profile to resume playing or create a new game...\n");
                for(Profile c:availableProfiles){
                    this.masterMenu.addOption(new MenuOption(c.getName(), MenuOptionType.PROFILE));
                }
                break;/*
            case CHARACTER_CREATED:
                masterMenu = new Menu();
                break;*/
            case PROFILE:
                masterMenu = new Menu();
                if(!currentProfile.getGames().isEmpty()) System.out.println("Choose a existing game to continue playing or create a new game...");
                for(Game g: currentProfile.getGames()){
                    masterMenu.addOption(new MenuOption(g.getName(), MenuOptionType.GAME));
                }
                break;
            case GAME:
                masterMenu = new Menu();
                System.out.println(currentGame.getPlayer().toString());
                break;
         /*   case GAME:
                masterMenu = new Menu();
*/
            case GAME_FIGHT:
                masterMenu = new Menu();
                System.out.println(currentGame.getPlayer().toString());
                break;


            default:
                //do nothing
        }
    }

    /**
     * Print game menu
     */
    private void printGameMenu(){
        refreshState();
        this.masterMenu.addOptions(getGameState().getMasterMenu());
        this.masterMenu.addOption(new MenuOption("Exit dota3", MenuOptionType.DOTA_EXIT));

        for(int i: this.masterMenu.getOptions().keySet()){
            System.out.println(i+" - "+this.masterMenu.getOptions().get(i).getLabel());
        }

        System.out.println("Your choice: ");

    }
}
