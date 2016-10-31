import com.pv.dota3.cli.game.Game;
import com.pv.dota3.cli.game.GameMap;
import com.pv.dota3.cli.game.Player;
import com.pv.dota3.cli.game.beans.GameCheckPoint;
import com.pv.dota3.cli.game.consumables.ArmourPack;
import com.pv.dota3.cli.game.consumables.Consumable;
import com.pv.dota3.cli.game.consumables.ConsumableStatus;
import com.pv.dota3.cli.game.consumables.HealthPack;
import com.pv.dota3.cli.game.enums.GameType;
import com.pv.dota3.cli.game.enums.Hero;
import com.pv.dota3.cli.game.factory.GameFactory;
import com.pv.dota3.cli.game.factory.GameMapGenerator;
import com.pv.dota3.cli.persistence.PersistenceType;
import com.pv.dota3.cli.persistence.PersistenceUtil;
import com.pv.dota3.cli.profile.Profile;
import com.pv.dota3.cli.profile.services.ProfileService;
import com.pv.dota3.cli.profile.services.ProfileServiceFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Dota3TestCli {

    static ProfileService profileService;
    static String profilesPath = "./test-profiles/";

    @BeforeClass
    public static void setup(){
        System.out.println("Running tests...");
        PersistenceUtil.setProfilesLoc(profilesPath);
        profileService = ProfileServiceFactory.getProfileService(PersistenceType.FILE);
    }

    @Test
    public void testProfileCreation(){
        String name = "testProfile";
        Profile pro =  profileService.create(name);

        String profileLoc = PersistenceUtil.getProfilesLoc() + name +".pro";
        File file = new File(profileLoc);
        assertTrue(file.exists());
    }

    @Test
    public void testConsumableHealth(){
        Player p = new Player(Hero.BountyHunter);
        HealthPack c = new HealthPack(5);
        c.apply(p);

        assertTrue(c.getStatus() == ConsumableStatus.USED);
        assertEquals("Player health consumable is not right", 70, p.getLife());
    }

    @Test
    public void testConsumableArmour(){
        Player p = new Player(Hero.BountyHunter);
        ArmourPack c = new ArmourPack(5);
        c.apply(p);

        assertTrue(c.getStatus() == ConsumableStatus.USED);
        assertEquals("Player health consumable is not right", 8, p.getArmour());
    }


    @Test
    public void testGameMap(){
        GameMap gameMap = GameMapGenerator.generate(Hero.BountyHunter);
        boolean rightMap = true;

        Iterator<GameCheckPoint> iter = gameMap.getGameCheckPointList().listIterator();
        while (iter.hasNext()){
            if(iter.next().getResidentHero().getHero() == Hero.BountyHunter){
                rightMap = false;
            }
        }

        assertTrue(rightMap);
    }

    @AfterClass
    public static void cleanUp() throws Exception{
        File file = new File(profilesPath);
        deleteFile(file);
    }

    private static void deleteFile(File file){
        if(file.exists()){
            if(file.isDirectory()){
                for(File f: file.listFiles()){
                    deleteFile(f);
                }
                if(file.listFiles().length == 0){
                    file.delete();
                }
            }else{
                file.delete();
            }
        }
    }
}
