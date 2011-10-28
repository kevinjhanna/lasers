package frontend;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Implementation of FileFilter to filter files in a JFileChooser based in
 * extension.
 */
public class ExtensionFileFilter extends FileFilter {

	private String extension;

	public ExtensionFileFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean accept(File f) {
		return f.getName().endsWith("." + extension) || f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "." + extension;
	}

}
