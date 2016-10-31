package com.pv.dota3.cli.persistence;

import com.pv.dota3.cli.profile.Profile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by pv on 15/10/16.
 */
public class ProfileFilePersistenceImpl implements ProfilePersistence {
    private static final Path profilesPath = Paths.get(PersistenceUtil.getProfilesLoc());

    protected ProfileFilePersistenceImpl() {
        try{
            Files.createDirectory(Paths.get(PersistenceUtil.getProfilesLoc()));
        }catch (FileAlreadyExistsException ex){
            System.err.println("Profile already exists");
        }catch (IOException ex){
            System.err.println("Error while creating/accessing profiles dir... exiting");
            System.exit(-1);
        }
    }

    @Override
    public Profile save(Profile character) {
        FileOutputStream fos  = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(getCharacterFile(character.getName()));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(character);
            oos.close();
        }catch (Exception ex){
            System.err.println("Error while saving profile.");
        }

        return character;
    }

    @Override
    public List<Profile> getAll()  {
        List<Profile> characters = new ArrayList<>();
        try {
            Stream<Path> filePaths = Files.list(profilesPath);
            filePaths.forEach(e -> {
                FileInputStream fis = null;
                ObjectInputStream in = null;
                try {
                    fis = new FileInputStream(e.toFile());
                    in = new ObjectInputStream(fis);
                    Profile ch = (Profile) in.readObject();
                    in.close();
                    characters.add(ch);
                } catch (Exception ex) {
                    System.err.println("Error while retrieving character profile - "+e.toFile().getAbsolutePath());
                    ex.printStackTrace();
                }
            });
        }catch (IOException ex){
            //do nothing.. ignore and return empty list.
        }

        return characters;
    }

    @Override
    public Profile findByName(String name) {
        Path filePath = Paths.get(getCharacterFile(name));
        File file = filePath.toFile();
        Profile ch = null;
        if(file.exists()){
            FileInputStream fis = null;
            ObjectInputStream in = null;
            try {
                fis = new FileInputStream(file);
                in = new ObjectInputStream(fis);
                ch = (Profile) in.readObject();
                in.close();
            } catch (Exception ex) {
                System.err.println("Error while retrieving character profile - "+file.getAbsolutePath());
            }
        }
        return ch;
    }

    @Override
    public void delete(String name) {

    }


    private String getCharacterFile(String cName){
        StringBuilder builder = new StringBuilder();
        builder.append(PersistenceUtil.getProfilesLoc()).append(cName).append(".pro");
        return builder.toString();
    }
}
