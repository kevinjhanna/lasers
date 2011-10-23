package frontend;

import java.io.File;

public interface ViewContainer {
	
	public abstract void setController(Controller c);

	public abstract void initialize();
	
	public abstract void setGame(int boardHeight, int boardWidth);

	public abstract View getView();
	
	public abstract void setVisible(boolean b);
	
	public abstract void setGameVisible(boolean b);
	
	public abstract void showWarning(String message);

	public abstract void showError(String message);
	
	public abstract ConfirmOption showConfirm(String message);
	
	public abstract File showSave();

	public abstract File showLoad(String extensionFilter);
	
}