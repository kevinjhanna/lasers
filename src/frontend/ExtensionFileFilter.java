package frontend;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter {
	
	private String extension;
	
	public ExtensionFileFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean accept(File f) {
		return f.getName().toLowerCase().endsWith("." + extension);
	}

	@Override
	public String getDescription() {
		return "." + extension;
	}

}
