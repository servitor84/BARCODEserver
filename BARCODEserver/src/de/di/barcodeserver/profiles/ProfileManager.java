package de.di.barcodeserver.profiles;

import de.di.barcodeserver.elo.ELOClient;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author A. Sopicki
 */
public class ProfileManager {

    private Logger logger;
    private java.util.Hashtable<String, Profile> profiles = null;
    private java.util.HashMap<String, Properties> propProfiles = null;
    private ELOClient client;

    public ProfileManager(ELOClient client) {
        logger = Logger.getLogger(getClass());
        this.client = client;
        profiles = new java.util.Hashtable<String, Profile>();
    }

    public void loadProfiles(File profilesDir) throws ProfileException {
        File[] profileFiles = profilesDir.listFiles(new ProfileFilenameFilter(profilesDir));
        propProfiles = new HashMap<String, Properties>();
        for (File profileFile : profileFiles) {
            Properties props = new Properties();
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(profileFile);
                props.load(inputStream);
            } catch (java.io.IOException ioex) {
                logger.debug("An exception occured while loading profile " + profileFile.getName(), ioex);
                throw new ProfileException("Unable to load profile " + profileFile.getName());
            } finally {
                try {
                    inputStream.close();
                } catch (Exception ex) {
                }
            }
            String profileName = props.getProperty("ProfileName", "");
            String action = props.getProperty("Action", "");

            propProfiles.put(profileName, props);
            
            if (profileName.length() == 0) {
                throw new ProfileException("Missing profile name in profile file " + profileFile.getName());
            }

            if (action.length() == 0) {
                throw new ProfileException("Missing action for profile file " + profileFile.getName());
            }

            if (profiles.containsKey(profileName)) {
                throw new ProfileException("Profile " + profileName + " already exists! Duplicate profile found"
                        + " in file " + profileFile.getName());
            }
            Profile profile = null;
            try {
                Class c = Class.forName("de.di.barcodeserver.profiles." + action);
                profile = (Profile) c.newInstance();
            } catch (ClassNotFoundException cnfe) {
                logger.debug("Unable to load class for action " + action, cnfe);
                throw new ProfileException("Action " + action + " not supported in profile file "
                        + profileFile.getName());
            } catch (IllegalAccessException iae) {
                logger.debug("Unable to instantiate profile for action " + action, iae);
                throw new ProfileException("Action " + action + " not supported in profile file"
                        + profileFile.getName());
            } catch (InstantiationException ie) {
                logger.debug("Unable to instantiate profile for action " + action, ie);
                throw new ProfileException("Action " + action + " not supported in profile file"
                        + profileFile.getName());
            } catch (ClassCastException cce) {
                logger.debug("Implementing class for action " + action + " must implement the profile interface",
                        cce);
                throw new ProfileException("Action " + action + " not supported in profile file"
                        + profileFile.getName());
            } catch (Exception ex) {
                logger.debug("An exception occured while creating the profile for action " + action, ex);
                throw new ProfileException("Unable to load profile from file" + profileFile.getName());
            }

            profile.setProfileName(profileName);
            profile.setClient(client);
            try {
                profile.init(props);
            } catch (ProfileException pfe) {
                logger.warn("Unable to initialize profile from file" + profileFile.getName());
                throw pfe;
            }
            profiles.put(profileName, profile);
        }
    }

    public Map<String,Properties> getAllProfilesAsProperties(){
        return propProfiles;
    }
    
    public Profile getProfile(String name) {
        return profiles.get(name);
    }

    public int getProfileCount() {
        if (profiles == null) {
            return 0;
        }

        return profiles.size();
    }
}
