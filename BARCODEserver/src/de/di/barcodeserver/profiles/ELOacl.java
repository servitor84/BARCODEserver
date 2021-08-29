/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.di.barcodeserver.profiles;

import de.elo.ix.client.ClientInfo;
import de.elo.ix.client.EditInfo;
import de.elo.ix.client.IXClient;
import de.elo.ix.client.Sord;

/**
 *
 * @author Rahman
 */
public class ELOacl {

    public static ClientInfo clientInfo;
    public static IXClient conn;
    
    public static String run(EditInfo editInfo, String newAcl) {
        Sord sord = editInfo.getSord();
        sord.setAcl(newAcl);
        return sord.getAcl();
        
    }
}
