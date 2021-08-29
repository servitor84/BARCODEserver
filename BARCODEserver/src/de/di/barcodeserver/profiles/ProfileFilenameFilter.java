
package de.di.barcodeserver.profiles;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author A. Sopicki
 */
public class ProfileFilenameFilter implements FilenameFilter {
  private File directory = null;
  private String extension = ".properties";

  public ProfileFilenameFilter(File dir) {
    directory = dir;
  }

  @Override
  public boolean accept(File dir, String name) {
    if (dir.compareTo(directory) == 0 &&
        name.endsWith(extension) && name.startsWith("profile_")) {
      return true;
    }

    return false;
  }
}
